using System.Collections.Generic;
using WebServer.Server.Handlers.Contracts;
using WebServer.Server.Routing.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server.Routing
{
    public class RoutingContext : IRoutingContext
    {
        public IRequestHandler Handler { get; private set; }

        public IEnumerable<string> Parameters { get; private set; }

        public RoutingContext(IRequestHandler handler, IEnumerable<string> parameters)
        {
            CoreValidator.ThrowIfNull(handler, nameof(handler));
            CoreValidator.ThrowIfNull(parameters, nameof(parameters));

            this.Handler = handler;
            this.Parameters = parameters;
        }
    }
}
