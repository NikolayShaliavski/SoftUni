using WebServer.Server.Validation;

namespace WebServer.Server.Http
{
    public class HttpHeader
    {
        public string Key { get; private set; }

        public string Value { get; private set; }

        public HttpHeader(string key, string value)
        {
            CoreValidator.ThrowIfNullOrEmpty(key);
            this.Key = key;
            this.Value = value;
        }

        public override string ToString()
        {
            return $"{this.Key}: {this.Value}";
        }
    }
}
