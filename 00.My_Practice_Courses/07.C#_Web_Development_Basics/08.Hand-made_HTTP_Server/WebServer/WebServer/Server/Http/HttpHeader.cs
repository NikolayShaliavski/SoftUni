using WebServer.Server.Validation;

namespace WebServer.Server.Http
{
    public class HttpHeader
    {
        public const string HEADER_CONTENT_TYPE = "Content-Type";
        public const string HEADER_HOST = "Host";
        public const string HEADER_LOCATION = "Location";
        public const string HEADER_COOKIE = "Cookie";
        public const string HEADER_SET_COOKIE = "Set-Cookie";

        public string Key { get; private set; }

        public string Value { get; private set; }

        public HttpHeader(string key, string value)
        {
            CoreValidator.ThrowIfNullOrEmpty(key, nameof(key));
            CoreValidator.ThrowIfNullOrEmpty(value, nameof(value));

            this.Key = key;
            this.Value = value;
        }

        public override string ToString()
        {
            return $"{this.Key}: {this.Value}";
        }
    }
}
