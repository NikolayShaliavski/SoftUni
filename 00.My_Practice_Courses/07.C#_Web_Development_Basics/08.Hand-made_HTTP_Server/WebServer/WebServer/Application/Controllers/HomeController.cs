using WebServer.Application.Views;
using WebServer.Server.Enums;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Http.Response;

namespace WebServer.Application.Controllers
{
    public class HomeController
    {
        public IHttpResponse Index()
        {
            return new ViewResponse(HttpStatusCode.OK, new HomeIndexView());
        }
    }
}
