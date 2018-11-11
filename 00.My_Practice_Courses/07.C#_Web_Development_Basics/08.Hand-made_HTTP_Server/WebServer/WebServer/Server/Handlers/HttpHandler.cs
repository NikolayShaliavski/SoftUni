using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;
using WebServer.Server.Enums;
using WebServer.Server.Handlers.Contracts;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Http.Response;
using WebServer.Server.Routing.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server.Handlers
{
    public class HttpHandler : IRequestHandler
    {
        private readonly IServerRouteConfig serverRouteConfig;

        public HttpHandler(IServerRouteConfig serverRouteConfig)
        {
            CoreValidator.ThrowIfNull(serverRouteConfig, nameof(serverRouteConfig));

            this.serverRouteConfig = serverRouteConfig;
        }

        public IHttpResponse Handle(IHttpContext context)
        {
            try
            {
                HttpRequestMethod requestMethod = context.Request.RequestMethod;
                string requestPath = context.Request.Path;
                IDictionary<string, IRoutingContext> registeredRoutes = this.serverRouteConfig.Routes[requestMethod];

                foreach (KeyValuePair<string, IRoutingContext> kvp in registeredRoutes)
                {
                    string routePatern = kvp.Key;
                    IRoutingContext routingContext = kvp.Value;

                    Regex regex = new Regex(routePatern);
                    Match match = regex.Match(requestPath);
                    if (!match.Success)
                    {
                        continue;
                    }
                    foreach (var parameter in routingContext.Parameters)
                    {
                        context.Request.AddUrlParameter(parameter, match.Groups[parameter].Value);
                    }

                    return routingContext.Handler.Handle(context);
                }
            }
            catch (Exception ex)
            {
                return new InternalServerErrorResponse(ex);
            }
            return new NotFoundResponse();
        }
    }
}
