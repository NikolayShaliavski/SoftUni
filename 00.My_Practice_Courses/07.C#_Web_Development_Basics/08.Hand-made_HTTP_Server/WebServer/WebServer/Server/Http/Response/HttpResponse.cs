using System;
using System.Text;
using WebServer.Server.Enums;
using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Http.Response
{
    public abstract class HttpResponse : IHttpResponse
    {
        private readonly IView view;
        private IHttpHeaderCollection headerCollection;
        private HttpStatusCode statusCode;
        private string statusMessage => this.statusCode.ToString();

        protected HttpResponse(string redirectUrl)
        {
            this.headerCollection = new HttpHeaderCollection();
            this.statusCode = HttpStatusCode.Found;
            this.AddHeader("Location", redirectUrl);
        }
        protected HttpResponse(HttpStatusCode responseCode, IView view)
        {
            this.headerCollection = new HttpHeaderCollection();
            this.statusCode = responseCode;
            this.view = view;
        }
        public string Response
        {
            get
            {
                StringBuilder response = new StringBuilder();
                response.AppendLine($"HTTP/1.1 {this.statusCode} {this.statusMessage}");
                response.AppendLine(this.headerCollection.ToString());
                response.AppendLine();

                if ((int)this.statusCode < 300 || (int)this.statusCode > 400)
                {
                    response.AppendLine(this.view.View());
                }
                return response.ToString();
            }
        }
        public void AddHeader(string key, string value)
        {
            this.headerCollection.Add(new HttpHeader(key, value));
        }
    }
}
