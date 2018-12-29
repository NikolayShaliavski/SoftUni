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
            string sessionId = this.TrySetSession(httpContext);

            IHttpResponse httpResponse = this.handlingFunc(httpContext.Request);

            if (sessionId != null)
            {
                httpResponse.Headers.Add(
                    HttpHeader.HEADER_SET_COOKIE,
                    $"{SessionStore.SESSION_COOKIE_KEY}={sessionId}; HttpOnly; path=/");
            }

            this.SetCookies(httpContext, httpResponse);
            if (!httpResponse.Headers.ContainsKey(HttpHeader.HEADER_CONTENT_TYPE))
            {
                httpResponse.Headers.Add(HttpHeader.HEADER_CONTENT_TYPE, "texp/plain");
            }

            return httpResponse;
        }
        private string TrySetSession(IHttpContext context)
        {
            string sessionId = null;

            if (!context.Request.Cookies.ContainsKey(SessionStore.SESSION_COOKIE_KEY))
            {
                sessionId = Guid.NewGuid().ToString();

                context.Request.Session = SessionStore.Get(sessionId);
            }
            return sessionId;
        }
        private void SetCookies(IHttpContext httpContext, IHttpResponse httpResponse)
        {
            foreach (var cookie in httpContext.Request.Cookies)
            {
                if (cookie == null)
                {
                    continue;
                }
                if (cookie.IsNew)
                {
                    httpResponse.Headers.Add(HttpHeader.HEADER_SET_COOKIE, cookie.ToString());
                }
            }
        }
    }
}
