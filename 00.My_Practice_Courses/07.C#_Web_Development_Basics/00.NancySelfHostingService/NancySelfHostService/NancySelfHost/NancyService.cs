using Nancy.Hosting.Self;
using System;
using System.ServiceProcess;

namespace NancySelfHost
{
    public partial class NancyService : ServiceBase
    {
        private const string BASE_ADDRESS ="http://localhost:8538/";
        private NancyHost host;
        public NancyService()
        {
            InitializeComponent();
        }
        protected override void OnStart(string[] args)
        {
            var config = new HostConfiguration { RewriteLocalhost = true };
            // Maybe wrap in using statement, but block execution using Console.Readline here
            this.host = new NancyHost(config, new Uri(BASE_ADDRESS));
            this.host.Start();
            Console.WriteLine($"Nancy self hosting service running ...");
        }
        protected override void OnStop()
        {
            if (this.host != null)
            {
                this.host.Stop();
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
