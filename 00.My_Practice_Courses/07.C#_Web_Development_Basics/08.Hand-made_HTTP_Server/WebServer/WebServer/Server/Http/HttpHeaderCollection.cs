using System;
using System.Collections.Generic;
using System.Linq;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server.Http
{
    public class HttpHeaderCollection : IHttpHeaderCollection
    {
        private readonly Dictionary<string, HttpHeader> headers;

        public HttpHeaderCollection()
        {
            this.headers = new Dictionary<string, HttpHeader>();
        }
        public void Add(HttpHeader header)
        {
            CoreValidator.ThrowIfNull(header, nameof(header));
            this.headers[header.Key] = header;
        }
        public void Add(string key, string value)
        {
            this.Add(new HttpHeader(key, value));
        }
        public bool ContainsKey(string key)
        {
            CoreValidator.ThrowIfNullOrEmpty(key, nameof(key));

            return this.headers.ContainsKey(key);
        }
        public HttpHeader GetHeader(string key)
        {
            if (!this.ContainsKey(key))
            {
                throw new InvalidOperationException($"The given key \"{key}\" does not present in the headers collection.");
            }

            return this.headers[key];
        }
        public override string ToString()
        {
            return string.Join(Environment.NewLine, this.headers.Select(h => h.Value));
        }
    }
}
