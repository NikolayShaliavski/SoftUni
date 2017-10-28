using CloudReplicator.Common;
using System;
using System.IO;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media.Animation;

namespace CloudReplicator
{
    partial class MainWindow
    {
        private int folders;
        private int files;

        private void Browse_Click(object sender, RoutedEventArgs e)
        {
            System.Windows.Forms.FolderBrowserDialog browse = new System.Windows.Forms.FolderBrowserDialog();
            System.Windows.Forms.DialogResult browseResult = browse.ShowDialog();

            if (browseResult == System.Windows.Forms.DialogResult.Cancel ||
                browseResult == System.Windows.Forms.DialogResult.OK)
            {
                this.BrowsedPath.Text = browse.SelectedPath;
            }
        }
        private void Upload_Click(object sender, RoutedEventArgs e)
        {
            bool hasErrors = false;
            ListViewItem volume = this.VolumesList.SelectedItem as ListViewItem;
            if (volume == null)
            {
                Utils.SetShadowEffect(this.VolumesList);
                hasErrors = true;
            }
            if (this.BrowsedPath.Text == null ||
                this.BrowsedPath.Text == "")
            {
                Utils.SetShadowEffect(this.BrowsedPath);
                hasErrors = true;
            }
            if (hasErrors)
            {
                return;
            }

            string volumeName = volume.Content as string;
            string path = this.BrowsedPath.Text;
            bool uploadContentOnly = this.UploadContent.IsChecked.Value;

            Task.Run(() => this.UploadObjects(volumeName, path, uploadContentOnly));
        }
        private async void UploadObjects(string volumeName, string path, bool uploadContentOnly)
        {
            Storyboard uploadingAnimation = null;
            this.Dispatcher.Invoke(() =>
            {
                this.folders = 0;
                this.files = 0;
                this.Upload.IsEnabled = false;
                this.Disconnect.IsEnabled = false;
                this.Browse.IsEnabled = false;
                this.UploadReport.Visibility = Visibility.Collapsed;
                this.UploadingAnimation.Visibility = Visibility.Visible;
                uploadingAnimation = this.TryFindResource("UploadingAnimation") as Storyboard;
                uploadingAnimation.Begin();
            });
            if (uploadContentOnly)
            {
                string[] content = Directory.GetFileSystemEntries(path, "*", SearchOption.TopDirectoryOnly);
                foreach (var subPath in content)
                {
                    await this.Replicate(volumeName, Constants.RootParentId, subPath);
                }
            }
            else
            {
                await this.Replicate(volumeName, Constants.RootParentId, path);
            }
            this.Dispatcher.Invoke(() =>
            {
                this.UploadReport.Text = string.Format("Total uploaded: Folders: {0}, Files: {1}", this.folders, this.files);
                uploadingAnimation.Stop();
                this.UploadingAnimation.Visibility = Visibility.Collapsed;
                this.UploadReport.Visibility = Visibility.Visible;
                this.Upload.IsEnabled = true;
                this.Disconnect.IsEnabled = true;
                this.Browse.IsEnabled = true;
            });
        }
        private async Task Replicate(string volumeName, string parentId, string path)
        {
            int index = path.LastIndexOf("\\");
            string name = path.Substring(index + 1); ;
            string id = Utils.GenerateGuid();

            bool success;
            string message;
            if (Directory.Exists(path))
            {
                success = await this.client.CreateFolder(volumeName, name, parentId, id);

                if (success)
                {
                    message = string.Format("Successfully uploaded folder: {0}.", name);
                    this.AppendToLogList(message);
                    this.folders++;
                }
                string[] content = Directory.GetFileSystemEntries(path, "*", SearchOption.TopDirectoryOnly);
                foreach (var subPath in content)
                {
                    await this.Replicate(volumeName, id, subPath);
                }
            }
            else if (File.Exists(path))
            {
                byte[] data = File.ReadAllBytes(path);
                success = await this.client.ReplicateFile(volumeName, name, parentId, id, data);

                if (success)
                {
                    message = string.Format("Successfully uploaded file: {0}.", name);
                    this.AppendToLogList(message);
                    this.files++;
                }
            }
        }
        private void Volumes_Selection_Changed(object sender, RoutedEventArgs e)
        {
            if (this.VolumesList.SelectedItem != null)
            {
                this.VolumesList.BorderBrush = this.defaultTextboxBorder;
                this.VolumesList.Effect = null;
            }
        }
        private void Browsed_Path_Changed(object sender, TextChangedEventArgs e)
        {
            if (this.BrowsedPath.Text.Length > 0)
            {
                this.BrowsedPath.BorderBrush = this.defaultTextboxBorder;
                this.BrowsedPath.Effect = null;
            }
        }
        private void Disconnect_Click(object sender, RoutedEventArgs e)
        {
            this.ClearValues();
            this.UploadPanel.Visibility = Visibility.Collapsed;
            this.ConnectPanel.Visibility = Visibility.Visible;
        }
        private void AppendToLogList(string message)
        {
            this.Dispatcher.Invoke(() =>
            {
                string time = DateTime.Now.ToString("yyyy-MM-dd THH:mm:ss");
                string activityMessage = string.Format("-- [{0}] {1}", time, message);
                ListViewItem item = new ListViewItem();
                item.Content = activityMessage;
                this.LogList.Items.Add(item);
                this.LogList.ScrollIntoView(this.LogList.Items[this.LogList.Items.Count - 1]);
            });
        }
        private void ClearValues()
        {
            this.folders = 0;
            this.files = 0;
            this.UploadingAnimation.Visibility = Visibility.Collapsed;
            this.UploadReport.Visibility = Visibility.Collapsed;
            this.UploadReport.Text = "";
            this.BrowsedPath.Text = "";
            this.BrowsedPath.BorderBrush = this.defaultTextboxBorder;
            this.BrowsedPath.Effect = null;
            this.VolumesList.BorderBrush = this.defaultTextboxBorder;
            this.VolumesList.Effect = null;
            this.VolumesList.Items.Clear();
            this.LogList.Items.Clear();
        }
    }
}
