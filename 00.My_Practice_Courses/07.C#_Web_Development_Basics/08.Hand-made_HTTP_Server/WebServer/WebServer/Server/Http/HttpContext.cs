using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Http
{
    public class HttpContext : IHttpContext
    {
        private readonly IHttpRequest request;

        public IHttpRequest Request => this.request;

        public HttpContext(string request)
        {
            this.request = new HttpRequest(request);
        }
    }
}
