using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using WebServer.Server.Enums;
using WebServer.Server.Handlers.Contracts;
using WebServer.Server.Routing.Contracts;

namespace WebServer.Server.Routing
{
    public class ServerRouteConfig : IServerRouteConfig
    {
        private readonly IDictionary<HttpRequestMethod, IDictionary<string, IRoutingContext>> routes;

        public IDictionary<HttpRequestMethod, IDictionary<string, IRoutingContext>> Routes => this.routes;

        public ServerRouteConfig(IAppRouteConfig appRouteConfig)
        {
            this.routes = new Dictionary<HttpRequestMethod, IDictionary<string, IRoutingContext>>();

            IEnumerable<HttpRequestMethod> availableMethods = Enum.GetValues(typeof(HttpRequestMethod)).Cast<HttpRequestMethod>();
            foreach (var method in availableMethods)
            {
                this.routes[method] = new Dictionary<string, IRoutingContext>();
            }

            this.InitializeServerConfig(appRouteConfig);
        }
        private void InitializeServerConfig(IAppRouteConfig appRouteConfig)
        {
            foreach (KeyValuePair<HttpRequestMethod, IDictionary<string, IRequestHandler>> kvp in appRouteConfig.Routes)
            {
                foreach (KeyValuePair<string, IRequestHandler> requestHandler in kvp.Value)
                {
                    List<string> parameters = new List<string>();

                    string parsedRegex = this.ParseRoute(requestHandler.Key, parameters);

                    IRoutingContext routingContext = new RoutingContext(requestHandler.Value, parameters);

                    this.Routes[kvp.Key].Add(parsedRegex, routingContext);
                }
            }
        }
        private string ParseRoute(string route, List<string> parameters)
        {
            if (route.Equals("/"))
            {
                return "^/$";
            }
            StringBuilder result = new StringBuilder();
            result.Append("^/");

            var tokens = route.Split(new[] { '/' }, StringSplitOptions.RemoveEmptyEntries);
            this.ParseTokens(tokens, parameters, result);

            return result.ToString();
        }
        private void ParseTokens(string[] tokens, List<string> parameters, StringBuilder result)
        {
            for (int i = 0; i < tokens.Length; i++)
            {
                string end = i == tokens.Length - 1 ? "?" : "/";
                string currentToken = tokens[i];

                if (!currentToken.StartsWith("{") && !currentToken.EndsWith("}"))
                {
                    result.Append($"{currentToken}{end}");
                    continue;
                }

                Regex parameterRegex = new Regex("<\\w+>");
                Match parameterMatch = parameterRegex.Match(currentToken);

                if (!parameterMatch.Success)
                {
                    throw new InvalidOperationException($"Route parameter in '{currentToken}' is not valid.");
                }

                // Maybe need to get first group
                string match = parameterMatch.Value;
                string parameter = match.Substring(1, match.Length - 2);

                parameters.Add(parameter);

                // Maybe same as parameter !!!!!!!!!!!!!!!!!!!!!!!
                string currentTokenWithoutCurlyBrackets = currentToken.Substring(1, currentToken.Length - 2);
                result.Append($"{currentTokenWithoutCurlyBrackets}{end}");
            }
        }
    }
}
