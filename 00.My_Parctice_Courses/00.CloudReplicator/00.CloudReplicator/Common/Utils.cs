using System;
using System.Text;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Media.Effects;

namespace CloudReplicator.Common
{
    public static class Utils
    {
        public static void TrySetFocus(Control credentialBox, ref bool focusMoved)
        {
            if (!focusMoved)
            {
                credentialBox.Focus();
                focusMoved = true;
            }
        }
        public static void SetShadowEffect(Control box)
        {
            DropShadowEffect shadow = new DropShadowEffect();
            shadow.Color = Colors.Red;
            shadow.ShadowDepth = 0;
            box.BorderBrush = Brushes.Red;
            box.Effect = shadow;
        }
        public static string GenerateGuid()
        {
            string guid = Guid.NewGuid().ToString();
            guid = guid.Replace("-", "");

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < guid.Length; i++)
            {
                char symbol = guid[i];
                if (symbol >= 97 && symbol <= 122)
                {
                    symbol = (char)(symbol - 32);
                }
                builder.Append(symbol);
            }
            return builder.ToString();
        }
    }
}
