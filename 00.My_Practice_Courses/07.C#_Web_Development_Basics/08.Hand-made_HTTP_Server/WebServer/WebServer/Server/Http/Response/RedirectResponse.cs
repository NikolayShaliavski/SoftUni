using WebServer.Server.Enums;
using WebServer.Server.Validation;

namespace WebServer.Server.Http.Response
{
    public class RedirectResponse : HttpResponse
    {
        public RedirectResponse(string redirectUrl) 
            : base()
        {
            CoreValidator.ThrowIfNullOrEmpty(redirectUrl, nameof(redirectUrl));

            base.StatusCode = HttpStatusCode.Found;
            base.AddHeader(HttpHeader.HEADER_LOCATION, redirectUrl);
        }
    }
}
