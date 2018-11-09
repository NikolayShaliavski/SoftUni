using System;
using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Handlers
{
    public class PostRequestHandler : RequestHandler
    {
        public PostRequestHandler(Func<IHttpContext, IHttpResponse> func) 
            : base(func)
        { }
    }
}
