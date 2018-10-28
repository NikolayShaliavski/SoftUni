using System;
using System.Collections.Generic;
using System.Net;
using WebServer.Server.Enums;
using WebServer.Server.Exceptions;
using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Http
{
    public class HttpRequest : IHttpRequest
    {
        public Dictionary<string, string> FormData { get; private set; }

        public IHttpHeaderCollection HeaderCollection { get; private set; }

        public string Path { get; private set; }

        public Dictionary<string, string> QueryParameters { get; private set; }

        public HttpRequestMethod RequestMethod { get; private set; }

        public string Url { get; private set; }

        public Dictionary<string, string> UrlParameters { get; private set; }

        public HttpRequest(string requestString)
        {
            this.FormData = new Dictionary<string, string>();
            this.HeaderCollection = new HttpHeaderCollection();
            this.QueryParameters = new Dictionary<string, string>();
            this.UrlParameters = new Dictionary<string, string>();

            this.ParseRequest(requestString);
        }
        public void AddUrlParameter(string key, string value)
        {
            this.UrlParameters[key] = value;
        }
        private void ParseRequest(string requestString)
        {
            string[] requestLines = requestString.Split(new[] { Environment.NewLine }, StringSplitOptions.RemoveEmptyEntries);

            string[] requestLine = requestLines[0].Trim().Split(new[] { " " }, StringSplitOptions.RemoveEmptyEntries);

            if (requestLine.Length != 3 && requestLine[2].ToLower() != "http/1.1")
            {
                throw new BadRequestException("Invalid request.");
            }

            this.RequestMethod = this.ParseRequestmethod(requestLine[0].ToUpper());
            this.Url = requestLine[1];
            this.Path = this.Url.Split(new[] { "?", "#" }, StringSplitOptions.RemoveEmptyEntries)[0];
            this.ParseHeaders(requestLines);
            this.ParseQueryParameters();

            if (this.RequestMethod == HttpRequestMethod.POST)
            {
                this.ParseQuery(requestLines[requestLines.Length - 1], this.FormData);
            }
        }
        private void ParseQueryParameters()
        {
            if (!this.Url.Contains("?"))
            {
                return;
            }
            string query = this.Url.Split('?')[1];

            this.ParseQuery(query, QueryParameters);
        }
        private void ParseQuery(string query, Dictionary<string, string> dict)
        {
            if (!query.Contains("="))
            {
                return;
            }
            string[] queryParams = query.Split('&');
            foreach (var param in queryParams)
            {
                string[] paramTokens = param.Split('=');
                // Skip invalid query parameter
                if (paramTokens.Length != 2)
                {
                    continue;
                }
                string key = WebUtility.UrlDecode(paramTokens[0]);
                string value = WebUtility.UrlDecode(paramTokens[1]);
                dict[key] = value;
            }
        }
        private void ParseHeaders(string[] requestLines)
        {
            int endIndex = Array.IndexOf(requestLines, string.Empty);
            for (int i = 1; i < endIndex; i++)
            {
                string[] headerArgs = requestLines[i].Split(new[] { ":" }, StringSplitOptions.RemoveEmptyEntries);
                // Just skip invalid header
                if (headerArgs.Length != 2)
                {
                    continue;
                }
                HttpHeader header = new HttpHeader(headerArgs[0].Trim(), headerArgs[1].Trim());
                this.HeaderCollection.Add(header);
            }
            if (this.HeaderCollection.ContainsKey("Host"))
            {
                throw new BadRequestException("Invalid request: requeried header \"Host\" is missing.");
            }
        }
        private HttpRequestMethod ParseRequestmethod(string method)
        {
            HttpRequestMethod requestMethod;
            bool parsed = Enum.TryParse(method, true, out requestMethod);
            if (!parsed)
            {
                throw new BadRequestException("Invalid HTTP request method.");
            }
            return requestMethod;
        }
    }
}
