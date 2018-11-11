using WebServer.Server.Enums;
using WebServer.Server.Views;

namespace WebServer.Server.Http.Response
{
    public class NotFoundResponse : ViewResponse
    {
        public NotFoundResponse() 
            : base(HttpStatusCode.NotFound, new NotFoundView())
        { }
    }
}
