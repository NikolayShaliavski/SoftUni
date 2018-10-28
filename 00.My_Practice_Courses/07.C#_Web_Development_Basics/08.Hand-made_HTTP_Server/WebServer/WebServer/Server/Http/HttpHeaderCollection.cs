using System;
using System.Collections.Generic;
using WebServer.Server.Http.Contracts;

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
            if (header == null)
            {
                throw new InvalidOperationException("Header is null.");
            }
            this.headers[header.Key] = header;
        }
        public bool ContainsKey(string key)
        {
            return this.headers.ContainsKey(key);
        }
        public HttpHeader GetHeader(string key)
        {
            return this.headers[key];
        }
        public override string ToString()
        {
            return string.Join(Environment.NewLine, this.headers);
        }
    }
}
