using System;
using System.Collections.Concurrent;
using System.Collections.Generic;
using System.Text;
using WebServer.Server.Http.Contracts;

namespace WebServer.Server.Http
{
    public static class SessionStore
    {
        public const string SESSION_COOKIE_KEY = "MY_SESSION_ID";
        public const string CURRENT_USER_KEY = "^%Current_User_Session_Key%^";

        private static readonly ConcurrentDictionary<string, IHttpSession> sessions =
            new ConcurrentDictionary<string, IHttpSession>();

        public static IHttpSession Get(string id)
            => sessions.GetOrAdd(id, _ => new HttpSession(id));
    }
}
