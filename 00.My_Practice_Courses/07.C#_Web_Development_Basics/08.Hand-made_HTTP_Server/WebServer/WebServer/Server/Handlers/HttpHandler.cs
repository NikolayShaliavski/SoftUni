using System;
using WebServer.Server.Handlers.Contracts;
using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Handlers
{
    public class HttpHandler : IRequestHandler
    {
        public IHttpResponse Handle(IHttpContext httpContext)
        {
            throw new NotImplementedException();
        }
    }
}
