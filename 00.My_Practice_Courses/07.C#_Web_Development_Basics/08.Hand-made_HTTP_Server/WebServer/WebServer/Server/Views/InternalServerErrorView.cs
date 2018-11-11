using System;
using WebServer.Server.Contracts;

namespace WebServer.Server.Views
{
    public class InternalServerErrorView : IView
    {
        private readonly Exception error;

        public InternalServerErrorView(Exception error)
        {
            this.error = error;
        }

        public string View()
        {
            return $"<h1>{this.error.Message}</h1><h2>{this.error.StackTrace}</h2>";
        }
    }
}
