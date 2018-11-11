﻿using WebServer.Server.Contracts;

namespace WebServer.Server.Views
{
    public class NotFoundView : IView
    {
        public string View()
        {
            return $"<h1>404 This page does not exist</h1>";
        }
    }
}
