using System;
using WebServer.Server.Handlers.Contracts;
using WebServer.Server.Http;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server.Handlers
{
    public class RequestHandler : IRequestHandler
    {
        private readonly Func<IHttpRequest, IHttpResponse> handlingFunc;

        public RequestHandler(Func<IHttpRequest, IHttpResponse> func)
        {
            CoreValidator.ThrowIfNull(func, nameof(func));

            this.handlingFunc = func;
        }
        public IHttpResponse Handle(IHttpContext httpContext)
        {
            IHttpResponse httpResponse = this.handlingFunc.Invoke(httpContext.Request);
            if (httpResponse.Headers.ContainsKey(HttpHeader.HEADER_CONTENT_TYPE))
            {
                httpResponse.Headers.Add(HttpHeader.HEADER_CONTENT_TYPE, "texp/plain");
            }

            return httpResponse;
        }
    }
}
