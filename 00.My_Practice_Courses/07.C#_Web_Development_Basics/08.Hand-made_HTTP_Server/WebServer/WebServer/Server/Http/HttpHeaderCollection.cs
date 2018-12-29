using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server.Http
{
    public class HttpHeaderCollection : IHttpHeaderCollection
    {
        private readonly Dictionary<string, ICollection<HttpHeader>> headers;

        public HttpHeaderCollection()
        {
            this.headers = new Dictionary<string, ICollection<HttpHeader>>();
        }
        public void Add(HttpHeader header)
        {
            CoreValidator.ThrowIfNull(header, nameof(header));
            if (!this.headers.ContainsKey(header.Key))
            {
                this.headers[header.Key] = new List<HttpHeader>();
            }
            this.headers[header.Key].Add(header);
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
        public ICollection<HttpHeader> GetHeader(string key)
        {
            if (!this.ContainsKey(key))
            {
                throw new InvalidOperationException($"The given key \"{key}\" does not present in the headers collection.");
            }

            return this.headers[key];
        }
        public override string ToString()
        {
            StringBuilder builder = new StringBuilder();
            foreach (var header in this.headers)
            {
                foreach (var headerValue in header.Value)
                {
                    builder.Append($"{header.Key}: {headerValue.Value}");
                }
                builder.AppendLine();
            }
            return builder.ToString();
        }
        public IEnumerator<ICollection<HttpHeader>> GetEnumerator() => this.headers.Values.GetEnumerator();

        IEnumerator IEnumerable.GetEnumerator() => this.headers.Values.GetEnumerator();
    }
}
