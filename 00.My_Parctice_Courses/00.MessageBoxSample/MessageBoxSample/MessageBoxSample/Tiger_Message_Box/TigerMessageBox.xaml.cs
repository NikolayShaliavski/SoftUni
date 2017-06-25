using System;
using System.Runtime.InteropServices;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media.Imaging;

namespace MessageBoxSample
{
    /// <summary>
    /// Interaction logic for TigerMessageBox.xaml
    /// </summary>
    public partial class TigerMessageBox : Window
    {
        internal static TigerMessageBox messageBox;
        internal static MessageBoxResult Result;

        public TigerMessageBox()
        {
            InitializeComponent();
        }

        public static void Show(MessageBoxType type, string message)
        {
            messageBox = new TigerMessageBox();
            messageBox.MessageBoxMessage.Text = message;
            Uri sourceUri;
            switch(type)
            {
                case MessageBoxType.Confirmation:
                    messageBox.Title = "Confirmation";
                    sourceUri = new Uri("Resources/confirm.png", UriKind.RelativeOrAbsolute);
                    messageBox.MessageBoxImage.Source = new BitmapImage(sourceUri);
                    messageBox.YesNoButtons.Visibility = Visibility.Visible;
                    break;
                case MessageBoxType.Information:
                    messageBox.Title = "Information";
                    sourceUri = new Uri("Resources/info.png", UriKind.RelativeOrAbsolute);
                    messageBox.MessageBoxImage.Source = new BitmapImage(sourceUri);
                    messageBox.OKButton.Visibility = Visibility.Visible;
                    break;
                case MessageBoxType.Warning:
                    messageBox.Title = "Warning";
                    sourceUri = new Uri("Resources/warning.png", UriKind.RelativeOrAbsolute);
                    messageBox.MessageBoxImage.Source = new BitmapImage(sourceUri);
                    messageBox.OKButton.Visibility = Visibility.Visible;
                    break;
                case MessageBoxType.Error:
                    messageBox.Title = "Error";
                    sourceUri = new Uri("Resources/error.png", UriKind.RelativeOrAbsolute);
                    messageBox.MessageBoxImage.Source = new BitmapImage(sourceUri);
                    messageBox.OKButton.Visibility = Visibility.Visible;
                    break;
            }

            messageBox.ShowDialog();
        }
        private void Button_Pressed(object sender, RoutedEventArgs e)
        {
            Button btn = sender as Button;
            if (btn.Name == "Yes")
            {
                Result = MessageBoxResult.Yes;
            }
            else if (btn.Name == "No")
            {
                Result = MessageBoxResult.No;
            }
            messageBox.Close();
        }
        [DllImport("user32.dll")]
        static extern uint GetWindowLong(IntPtr hWnd, int nIndex);

        [DllImport("user32.dll")]
        static extern int SetWindowLong(IntPtr hWnd, int nIndex, uint dwNewLong);

        private const int GWL_STYLE = -16;

        private const uint WS_SYSMENU = 0x80000;

        protected override void OnSourceInitialized(EventArgs e)
        {
            IntPtr hwnd = new System.Windows.Interop.WindowInteropHelper(this).Handle;
            SetWindowLong(hwnd, GWL_STYLE,
                GetWindowLong(hwnd, GWL_STYLE) & (0xFFFFFFFF ^ WS_SYSMENU));

            base.OnSourceInitialized(e);
        }
    }
}
