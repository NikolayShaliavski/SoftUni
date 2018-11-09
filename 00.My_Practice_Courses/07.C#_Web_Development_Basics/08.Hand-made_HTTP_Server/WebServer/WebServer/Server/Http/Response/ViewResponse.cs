using WebServer.Server.Enums;
using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Http.Response
{
    public class ViewResponse : HttpResponse
    {
        public ViewResponse(HttpStatusCode responseCode, IView view) 
            : base(responseCode, view)
        { }
    }
}
