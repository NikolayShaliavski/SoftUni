﻿using System;
using System.Collections.Generic;
using System.Net;
using WebServer.Server.Enums;
using WebServer.Server.Exceptions;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server.Http
{
    public class HttpRequest : IHttpRequest
    {
        public Dictionary<string, string> FormData { get; private set; }

        public IHttpHeaderCollection Headers { get; private set; }

        public IHttpCookieCollection Cookies { get; private set; }

        public IHttpSession Session { get; set; }

        public string Path { get; private set; }

        public Dictionary<string, string> QueryParameters { get; private set; }

        public HttpRequestMethod RequestMethod { get; private set; }

        public string Url { get; private set; }

        public Dictionary<string, string> UrlParameters { get; private set; }

        public HttpRequest(string requestString)
        {
            CoreValidator.ThrowIfNullOrEmpty(requestString, nameof(requestString));

            this.FormData = new Dictionary<string, string>();
            this.Headers = new HttpHeaderCollection();
            this.Cookies = new HttpCookieCollection();
            this.QueryParameters = new Dictionary<string, string>();
            this.UrlParameters = new Dictionary<string, string>();

            this.ParseRequest(requestString);
        }
        public void AddUrlParameter(string key, string value)
        {
            CoreValidator.ThrowIfNullOrEmpty(key, nameof(key));
            CoreValidator.ThrowIfNullOrEmpty(value, nameof(value));

            this.UrlParameters[key] = value;
        }
        private void ParseRequest(string requestString)
        {
            string[] requestLines = requestString.Split(Environment.NewLine);

            string[] requestLine = requestLines[0].Trim().Split(new[] { " " }, StringSplitOptions.RemoveEmptyEntries);

            if (requestLine.Length != 3 && requestLine[2].ToLower() != "http/1.1")
            {
                throw new BadRequestException("Invalid request.");
            }

            this.RequestMethod = this.ParseRequestmethod(requestLine[0].ToUpper());
            this.Url = requestLine[1];
            this.Path = this.Url.Split(new[] { "?", "#" }, StringSplitOptions.RemoveEmptyEntries)[0];
            this.ParseHeaders(requestLines);
            this.ParseCookies();
            this.ParseQueryParameters();

            if (this.RequestMethod == HttpRequestMethod.POST)
            {
                this.ParseQuery(requestLines[requestLines.Length - 1], this.FormData);
            }

            this.SetSession();
        }
        private void ParseQueryParameters()
        {
            if (!this.Url.Contains("?"))
            {
                return;
            }
            string query = this.Url.Split('?')[1];

            this.ParseQuery(query, this.QueryParameters);
        }
        // Parse request parameters
        // GET - extract them from url
        // POST - from request body
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
        // Parse all resuest headers
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
                this.Headers.Add(header);
            }
            if (this.Headers.ContainsKey("Host"))
            {
                throw new BadRequestException("Invalid request: requiered header \"Host\" is missing.");
            }
        }
        // Extract all values under 'Cookie' key in the headers collection
        private void ParseCookies()
        {
            if (this.Headers.ContainsKey(HttpHeader.HEADER_COOKIE))
            {
                ICollection<HttpHeader> allCookies = this.Headers.GetHeader(HttpHeader.HEADER_COOKIE);
                foreach (var cookieHeader in allCookies)
                {
                    string cookieValue = cookieHeader.Value;
                    if (string.IsNullOrWhiteSpace(cookieValue))
                    {
                        continue;
                    }
                    string[] cookiesParts = cookieValue.Split(new string[] { ";" }, StringSplitOptions.RemoveEmptyEntries);
                    foreach (var part in cookiesParts)
                    {
                        string[] cookieValuePair = part.Split(new string[] { "=" }, StringSplitOptions.RemoveEmptyEntries);
                        // Invalid cookie
                        if (cookieValuePair.Length != 2)
                        {
                            continue;
                        }
                        HttpCookie cookie = new HttpCookie(cookieValuePair[0], cookieValuePair[1], false);
                        this.Cookies.Add(cookie);
                    }
                }
            }
        }
        // Parse HTTP request method
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
        private void SetSession()
        {
            if (this.Cookies.ContainsKey(SessionStore.SESSION_COOKIE_KEY))
            {
                HttpCookie cookie = this.Cookies.Get(SessionStore.SESSION_COOKIE_KEY);
                string sessionId = cookie.Value;

                this.Session = SessionStore.Get(sessionId);
            }
        }
    }
}
