using _00.CloudReplicator.CloudClients;
using CloudReplicator.CloudClients;
using CloudReplicator.Common;
using System;
using System.Collections.Generic;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Animation;

namespace CloudReplicator
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private Brush defaultTextboxBorder;

        private IClient client;
        private string cloudType;
        private string serviceUrl;
        private string accessKey;
        private string secretKey;
        private string accountName;
        private string accountKey;

        public MainWindow()
        {
            InitializeComponent();
            this.defaultTextboxBorder = (SolidColorBrush)(new BrushConverter().ConvertFrom("#FFABADB3"));
            this.CloudType.SelectedIndex = 0;
            this.ConnectPanel.Visibility = Visibility.Visible;
        }
        private void Connect_Click(object ender, RoutedEventArgs e)
        {
            bool hasErrors = false;
            switch (this.cloudType)
            {
                case Constants.CloudTypeS3:
                    this.serviceUrl = this.ServiceUrl.Text;
                    this.accessKey = this.AccessKey.Text;
                    this.secretKey = this.SecretKey.Text;
                    break;
                case Constants.CloudTypeAzure:
                    this.accountName = this.AccountName.Text;
                    this.accountKey = this.AccountKey.Text;
                    break;
            }
            bool focusMoved = false;
            switch (this.cloudType)
            {
                case Constants.CloudTypeS3:
                    if (this.serviceUrl == null || this.serviceUrl == "")
                    {
                        Utils.SetShadowEffect(this.ServiceUrl);
                        Utils.TrySetFocus(this.ServiceUrl, ref focusMoved);
                        hasErrors = true;
                    }
                    if (this.accessKey == null || this.accessKey == "")
                    {
                        Utils.SetShadowEffect(this.AccessKey);
                        Utils.TrySetFocus(this.AccessKey, ref focusMoved);
                        hasErrors = true;
                    }
                    if (this.secretKey == null || this.secretKey == "")
                    {
                        Utils.SetShadowEffect(this.SecretKey);
                        Utils.TrySetFocus(this.SecretKey, ref focusMoved);
                        hasErrors = true;
                    }
                    break;
                case Constants.CloudTypeAzure:
                    if (this.accountName == null || this.accountName == "")
                    {
                        Utils.SetShadowEffect(this.AccountName);
                        Utils.TrySetFocus(this.AccountName, ref focusMoved);
                        hasErrors = true;
                    }
                    if (this.accountKey == null || this.accountKey == "")
                    {
                        Utils.SetShadowEffect(this.AccountKey);
                        Utils.TrySetFocus(this.AccountKey, ref focusMoved);
                        hasErrors = true;
                    }
                    break;
            }
            if (!hasErrors)
            {
                this.CreateConnection();
            }
        }
        private async void CreateConnection()
        {
            Storyboard connectingAnimation = this.TryFindResource("ConnectingAnimation") as Storyboard;
            this.ConnectingAnimationText.Text = "Connecting to " + this.cloudType;
            connectingAnimation.Begin();
            this.ConnectPanel.Visibility = Visibility.Collapsed;
            this.ConnectingAnimation.Visibility = Visibility.Visible;

            try
            {
                switch (this.cloudType)
                {
                    case Constants.CloudTypeAzure:
                        this.client = new AzureClient(this.accountName, this.accountKey);
                        this.Volumes.Text = "Containers:";
                        this.ConnectedTo.Text = "Connected to Azure";
                        break;
                    case Constants.CloudTypeS3:
                        this.client = new AmazonClient(this.serviceUrl, this.accessKey, this.secretKey);
                        this.Volumes.Text = "Buckets:";
                        this.ConnectedTo.Text = "Connected to Amazon S3";
                        break;
                }
                List<string> volumes = await this.client.GetVolumes();
                if (volumes == null)
                {
                    throw new Exception();
                }
                foreach (var volume in volumes)
                {
                    ListViewItem item = new ListViewItem();
                    item.Content = volume;
                    this.VolumesList.Items.Add(item);
                }
                connectingAnimation.Stop();
                this.ConnectingAnimation.Visibility = Visibility.Collapsed;
                this.UploadPanel.Visibility = Visibility.Visible;
            }
            catch (Exception ex)
            {
                connectingAnimation.Stop();
                this.ConnectingAnimation.Visibility = Visibility.Collapsed;
                this.ConnectPanel.Visibility = Visibility.Visible;
            }
        }
        private void Cloud_Type_Selection_Changed(object sender, RoutedEventArgs e)
        {
            ComboBoxItem selected = this.CloudType.SelectedItem as ComboBoxItem;
            if (selected == null)
            {
                return;
            }
            this.cloudType = selected.Content.ToString();
            switch (this.cloudType)
            {
                case Constants.CloudTypeAzure:
                    this.ClearCredentialFields();
                    this.SetDefaultAzureCredentials();
                    this.S3Credentials.Visibility = Visibility.Collapsed;
                    this.AzureCredentials.Visibility = Visibility.Visible;
                    break;
                case Constants.CloudTypeS3:
                    this.ClearCredentialFields();
                    this.SetDefaultAmazonCredentials();
                    this.AzureCredentials.Visibility = Visibility.Collapsed;
                    this.S3Credentials.Visibility = Visibility.Visible;
                    break;
            }
        }
        private void Service_Url_Changed(object sender, TextChangedEventArgs e)
        {
            if (this.ServiceUrl.Text.Length > 0)
            {
                this.ServiceUrl.BorderBrush = this.defaultTextboxBorder;
                this.ServiceUrl.Effect = null;
            }
        }
        private void Access_Key_Changed(object sender, TextChangedEventArgs e)
        {
            if (this.AccessKey.Text.Length > 0)
            {
                this.AccessKey.BorderBrush = this.defaultTextboxBorder;
                this.AccessKey.Effect = null;
            }
        }
        private void Secret_Key_Changed(object sender, TextChangedEventArgs e)
        {
            if (this.SecretKey.Text.Length > 0)
            {
                this.SecretKey.BorderBrush = this.defaultTextboxBorder;
                this.SecretKey.Effect = null;
            }
        }
        private void Account_Name_Changed(object sender, TextChangedEventArgs e)
        {
            if (this.AccountName.Text.Length > 0)
            {
                this.AccountName.BorderBrush = this.defaultTextboxBorder;
                this.AccountName.Effect = null;
            }
        }
        private void Account_Key_Changed(object sender, TextChangedEventArgs e)
        {
            if (this.AccountKey.Text.Length > 0)
            {
                this.AccountKey.BorderBrush = this.defaultTextboxBorder;
                this.AccountKey.Effect = null;
            }
        }
        private void ClearCredentialFields()
        {
            this.AccountName.Text = "";
            this.AccountName.BorderBrush = this.defaultTextboxBorder;
            this.AccountName.Effect = null;
            this.AccountKey.Text = "";
            this.AccountKey.BorderBrush = this.defaultTextboxBorder;
            this.AccountKey.Effect = null;
            this.ServiceUrl.Text = "";
            this.ServiceUrl.BorderBrush = this.defaultTextboxBorder;
            this.ServiceUrl.Effect = null;
            this.AccessKey.Text = "";
            this.AccessKey.BorderBrush = this.defaultTextboxBorder;
            this.AccessKey.Effect = null;
            this.SecretKey.Text = "";
            this.SecretKey.BorderBrush = this.defaultTextboxBorder;
            this.SecretKey.Effect = null;
        }
        private void SetDefaultAzureCredentials()
        {
            this.AccountName.Text = "devstoreaccount1";
            this.AccountKey.Text = "Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==";
        }
        private void SetDefaultAmazonCredentials()
        {
            this.ServiceUrl.Text = "http://10.200.9.161";
            this.AccessKey.Text = "JhfajFKIctmkD630O8PK";
            this.SecretKey.Text = "BIbxjj6wLOspw3p0zmkHfBupBCd8kOWgIMaFe4nL";
        }
    }
}
