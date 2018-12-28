using WebServer.Application.Controllers;
using WebServer.Server.Contracts;
using WebServer.Server.Routing.Contracts;

namespace WebServer.Application
{
    public class MainApplication : IApplication
    {
        public void Configure(IAppRouteConfig appRouteConfig)
        {
            appRouteConfig.AddGet(
                "/",
                req => new HomeController().Index());

            appRouteConfig.AddGet(
                "/register",
                req => new UserController().RegisterGet());

            appRouteConfig.AddPost(
                "/register",
                req => new UserController().RegisterPost(req.FormData["name"]));

            // To match all characters use '.*' instead
            appRouteConfig.AddGet(
                "/user/{(?<name>[a-zA-Z0-9_]+)}",
                req => new UserController().Details(req.UrlParameters["name"]));
        }
    }
}
