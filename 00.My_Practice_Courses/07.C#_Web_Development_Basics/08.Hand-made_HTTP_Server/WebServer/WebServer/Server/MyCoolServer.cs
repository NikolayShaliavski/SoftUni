using System;
using System.Net;
using System.Net.Sockets;
using System.Threading.Tasks;
using WebServer.Server.Contracts;
using WebServer.Server.Routing;
using WebServer.Server.Routing.Contracts;

namespace WebServer.Server
{
    public class MyCoolServer : IRunnable
    {
        private const string LOCALHOST = "127.0.0.1";

        private readonly int port;
        private readonly IServerRouteConfig serverRouteConfig;
        private readonly TcpListener listener;
        private bool isRunning;

        public MyCoolServer(int port, IAppRouteConfig appRouteConfig)
        {
            this.port = port;
            this.listener = new TcpListener(IPAddress.Parse(LOCALHOST), this.port);
            this.serverRouteConfig = new ServerRouteConfig(appRouteConfig);
        }
        public void Run()
        {
            this.listener.Start();
            this.isRunning = true;

            Console.WriteLine($"My cool Web server running on {LOCALHOST}:{this.port}");

            Task.Run(this.ListeLoop).Wait();
        }
        private async Task ListeLoop()
        {
            while (this.isRunning)
            {
                Socket client = await this.listener.AcceptSocketAsync();
                ConnectionHandler connectionHandler = new ConnectionHandler(client, this.serverRouteConfig);
                await connectionHandler.ProcessRequestAsync();
            }
        }
    }
}
