using Microsoft.Owin.Hosting;
using System;
using System.ServiceProcess;

namespace OWINSelfHost
{
    partial class OwinService : ServiceBase
    {
        private const string BASE_ADDRESS = "http://*:8538/";
        private IDisposable server;
        public OwinService()
        {
            InitializeComponent();
        }
        protected override void OnStart(string[] args)
        {
            this.server = WebApp.Start<Startup>(url: BASE_ADDRESS);
            Console.WriteLine($"Nancy (Owin) self hosting service running ...");
        }
        protected override void OnStop()
        {
            if (this.server != null)
            {
                this.server.Dispose();
            }
        }
        internal void TestAsConsoleApp(string[] args)
        {
            this.OnStart(args);
            Console.ReadLine();
            this.OnStop();
        }
    }
}
