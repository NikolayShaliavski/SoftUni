using WebServer.Application.Controllers;
using WebServer.Server.Contracts;
using WebServer.Server.Routing.Contracts;

namespace WebServer.Application
{
    public class MainApplication : IApplication
    {
        public void Configure(IAppRouteConfig appRouteConfig)
        {
            appRouteConfig.AddGet("/", request => new HomeController().Index());
        }
    }
}
