using System;
using System.Collections.Generic;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server.Http
{
    public class HttpSession : IHttpSession
    {
        public string Id { get; private set; }

        private IDictionary<string, string> parameters;

        public HttpSession(string id)
        {
            CoreValidator.ThrowIfNullOrEmpty(id, nameof(id));

            this.Id = id;
            this.parameters = new Dictionary<string, string>();
        }
        public void Add(string key, string value)
        {
            CoreValidator.ThrowIfNullOrEmpty(key, nameof(key));
            CoreValidator.ThrowIfNullOrEmpty(value, nameof(value));

            this.parameters[key] = value;
        }

        public void Clear() => this.parameters.Clear();

        public string GetParameter(string key)
        {
            CoreValidator.ThrowIfNullOrEmpty(key, nameof(key));
            if (!this.parameters.ContainsKey(key))
            {
                throw new InvalidOperationException($"The given key '{key}' does not present in the session parameters.");
            }
            throw new NotImplementedException();
        }
        public bool IsAuthenticated()
        {
            throw new NotImplementedException();
        }
    }
}
