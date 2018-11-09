using System;
using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Handlers
{
    public class GetRequestHandler : RequestHandler
    {
        public GetRequestHandler(Func<IHttpContext, IHttpResponse> func)
            : base(func)
        { }
    }
}
