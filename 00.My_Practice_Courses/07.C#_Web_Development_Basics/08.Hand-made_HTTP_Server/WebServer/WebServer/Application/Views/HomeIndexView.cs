using WebServer.Server.Contracts;

namespace WebServer.Application.Views
{
    public class HomeIndexView : IView
    {
        public string View()
        {
            return "<body><h1>Welcome</h1></body>";
        }
    }
}
