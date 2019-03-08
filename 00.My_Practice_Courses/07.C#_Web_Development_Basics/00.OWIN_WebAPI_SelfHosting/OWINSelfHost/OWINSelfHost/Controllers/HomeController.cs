using System.Web.Http;

namespace OWINSelfHost.Controllers
{
    public class HomeController : ApiController
    {
        public string Get()
        {
            return "<h1>Hello from OWIN self-hosting app!!!</h1>";
        }
    }
}
