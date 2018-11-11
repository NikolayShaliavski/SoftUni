using WebServer.Server.Enums;

namespace WebServer.Server.Http.Contracts
{
    public interface IHttpResponse
    {
        HttpStatusCode StatusCode { get; }

        string StatusMessage { get; }

        IHttpHeaderCollection Headers { get; }
    }
}