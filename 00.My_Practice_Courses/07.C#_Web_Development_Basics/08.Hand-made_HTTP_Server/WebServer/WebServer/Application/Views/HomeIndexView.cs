using WebServer.Server.Contracts;

namespace WebServer.Application.Views
{
    public class HomeIndexView : IView
    {
        public string View()
        {
            return "<body><h1>Welcome from 'My cool web server'</h1></body>";
        }
    }
}
