using WebServer.Server;
using WebServer.Server.Contracts;

namespace WebServer.Application.Views
{
    public class UserDetailsView : IView
    {
        private readonly Model model;

        public UserDetailsView(Model _model)
        {
            this.model = _model;
        }
        public string View()
        {
            return $"<body><h3>Hello, {this.model["name"]}!</h3></body>";
        }
    }
}
