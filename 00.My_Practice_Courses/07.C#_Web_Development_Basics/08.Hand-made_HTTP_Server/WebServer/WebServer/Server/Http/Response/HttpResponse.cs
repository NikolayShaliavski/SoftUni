using System.Text;
using WebServer.Server.Enums;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server.Http.Response
{
    public abstract class HttpResponse : IHttpResponse
    {
        public IHttpHeaderCollection Headers { get; }

        public HttpStatusCode StatusCode { get; protected set; }

        public string StatusMessage => this.StatusCode.ToString();

        protected HttpResponse()
        {
            this.Headers = new HttpHeaderCollection();
        }

        public void AddHeader(string key, string value)
        {
            CoreValidator.ThrowIfNullOrEmpty(key, nameof(key));

            this.Headers.Add(new HttpHeader(key, value));
        }
        public override string ToString()
        {
            StringBuilder response = new StringBuilder();
            response.AppendLine($"HTTP/1.1 {(int)this.StatusCode} {this.StatusMessage}");
            response.AppendLine(this.Headers.ToString());
            response.AppendLine();

            return response.ToString();
        }
    }
}
