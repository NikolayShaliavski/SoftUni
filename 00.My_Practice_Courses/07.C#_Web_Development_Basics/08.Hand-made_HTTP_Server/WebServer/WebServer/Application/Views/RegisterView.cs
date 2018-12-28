using System.Text;
using WebServer.Server.Contracts;

namespace WebServer.Application.Views
{
    public class RegisterView : IView
    {
        public string View()
        {
            StringBuilder builder = new StringBuilder();
            builder.Append("<body>");
            builder.Append("  <form method=\"POST\">");
            builder.Append("    Name<br/>");
            builder.Append("    <input type=\"text\" name=\"name\" /><br/>");
            builder.Append("    <input type=\"submit\" />");
            builder.Append("  </form>");
            builder.Append("</body>");
            return builder.ToString();
        }
    }
}
