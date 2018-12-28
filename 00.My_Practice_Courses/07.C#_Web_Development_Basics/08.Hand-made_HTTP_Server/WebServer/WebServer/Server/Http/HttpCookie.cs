using System;
using WebServer.Server.Validation;

namespace WebServer.Server.Http
{
    public class HttpCookie
    {
        public string Key { get; private set; }

        public string Value { get; private set; }

        public DateTime Expires { get; private set; }

        public bool IsNew { get; private set; } = true;

        // Cookie expiration is in days
        public HttpCookie(string key, string value, int expires = 3)
        {
            CoreValidator.ThrowIfNullOrEmpty(key, nameof(key));
            CoreValidator.ThrowIfNullOrEmpty(value, nameof(value));

            this.Key = key;
            this.Value = value;
            this.Expires = DateTime.UtcNow.AddDays(expires);
        }
        public HttpCookie(string key, string value, bool isNew, int expires = 3)
            : this(key, value, expires)
        {
            this.IsNew = isNew;
        }
        public override string ToString()
            => $"{this.Key}={this.Value}; Expires={this.Expires.ToLongDateString()}";

    }
}
