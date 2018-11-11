using WebServer.Server.Contracts;
using WebServer.Server.Enums;
using WebServer.Server.Exceptions;

namespace WebServer.Server.Http.Response
{
    public class ViewResponse : HttpResponse
    {
        private readonly IView view;

        public ViewResponse(HttpStatusCode statusCode, IView view) 
            : base()
        {
            this.ValidateStatusCode(statusCode);

            base.StatusCode = statusCode;
            this.view = view;
            base.AddHeader(HttpHeader.HEADER_CONTENT_TYPE, "text/html");
        }
        private void ValidateStatusCode(HttpStatusCode statusCode)
        {
            int code = (int)statusCode;
            // Status code is between 300 and 399 - should be RedirectResponse
            if (code >= 300 && code < 400)
            {
                throw new InvalidResponseException("View responses need a status code below 300 and above 400 (inclusive).");
            }
        }
        public override string ToString()
        {
            return $"{base.ToString()}{this.view.View()}";
        }
    }
}
