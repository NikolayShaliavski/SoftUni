using System;
using System.Collections.Generic;
using System.Linq;
using WebServer.Server.Enums;
using WebServer.Server.Handlers;
using WebServer.Server.Handlers.Contracts;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Routing.Contracts;

namespace WebServer.Server.Routing
{
    public class AppRouteConfig : IAppRouteConfig
    {
        private readonly Dictionary<HttpRequestMethod, IDictionary<string, IRequestHandler>> routes;

        public IReadOnlyDictionary<HttpRequestMethod, IDictionary<string, IRequestHandler>> Routes => this.routes;

        public AppRouteConfig()
        {
            this.routes = new Dictionary<HttpRequestMethod, IDictionary<string, IRequestHandler>>();

            IEnumerable<HttpRequestMethod> availableMethods = Enum.GetValues(typeof(HttpRequestMethod)).Cast<HttpRequestMethod>();
            foreach (var method in availableMethods)
            {
                this.routes[method] = new Dictionary<string, IRequestHandler>();
            }
        }
        public void AddGet(string route, Func<IHttpRequest, IHttpResponse> handlingFunc)
        {
            this.AddRoute(route, HttpRequestMethod.GET, new RequestHandler(handlingFunc));
        }
        public void AddPost(string route, Func<IHttpRequest, IHttpResponse> handlingFunc)
        {
            this.AddRoute(route, HttpRequestMethod.POST, new RequestHandler(handlingFunc));
        }
        public void AddRoute(string route, HttpRequestMethod method, IRequestHandler handler)
        {
            if (!this.routes.ContainsKey(method))
            {
                throw new NotSupportedException($"Method \"{method.ToString()}\" is not supported.");
            }
            if (this.routes[method].ContainsKey(route))
            {
                throw new InvalidOperationException($"Route \"{route}\" is already registered.");
            }
            this.routes[method].Add(route, handler);
        }
    }
}
