using System.Windows;

namespace MessageBoxSample
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Message_Box_Show(object sender, RoutedEventArgs e)
        {
            TigerMessageBox.Show(MessageBoxType.Confirmation, "Are you sure you want to delete selected items?");

            if (TigerMessageBox.Result == MessageBoxResult.Yes)
            {
                ResultMessage.Text = "Yes";
            }
            else
            {
                ResultMessage.Text = "No";
            }

            TigerMessageBox.Show(MessageBoxType.Warning, "You are not select items!");

            TigerMessageBox.Show(MessageBoxType.Error, "This is error message!");

            TigerMessageBox.Show(MessageBoxType.Information, "This is information message!");
        }
    }
}
