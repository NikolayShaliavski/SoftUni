using System;
using System.Collections.Generic;
using WebServer.Server.Enums;
using WebServer.Server.Handlers.Contracts;
using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Routing.Contracts
{
    public interface IAppRouteConfig
    {
        IReadOnlyDictionary<HttpRequestMethod, IDictionary<string, IRequestHandler>> Routes { get; }

        void AddGet(string route, Func<IHttpRequest, IHttpResponse> handlingFunc);

        void AddPost(string route, Func<IHttpRequest, IHttpResponse> handlingFunc);

        void AddRoute(string route, HttpRequestMethod method, IRequestHandler handler);
    }
}
