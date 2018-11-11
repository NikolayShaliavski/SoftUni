using System;
using WebServer.Server.Enums;
using WebServer.Server.Views;

namespace WebServer.Server.Http.Response
{
    public class InternalServerErrorResponse : ViewResponse
    {
        public InternalServerErrorResponse(Exception ex)
            : base(HttpStatusCode.InternalServerError, new InternalServerErrorView(ex))
        { }
    }
}
