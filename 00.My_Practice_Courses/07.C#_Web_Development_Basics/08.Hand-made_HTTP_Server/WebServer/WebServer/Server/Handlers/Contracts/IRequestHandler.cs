using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Handlers.Contracts
{
    public interface IRequestHandler
    {
        IHttpResponse Handle(IHttpContext httpContext);
    }
}
