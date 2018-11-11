using System.Collections.Generic;
using WebServer.Server.Handlers.Contracts;

namespace WebServer.Server.Routing.Contracts
{
    public interface IRoutingContext
    {
        IEnumerable<string> Parameters { get; }

        IRequestHandler Handler { get; }

    }
}
