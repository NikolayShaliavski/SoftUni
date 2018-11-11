using WebServer.Application;
using WebServer.Server;
using WebServer.Server.Contracts;
using WebServer.Server.Routing;
using WebServer.Server.Routing.Contracts;

namespace WebServer
{
    public class Launcher : IRunnable
    {
        public static void Main(string[] args)
        {
            new Launcher().Run();
        }

        public void Run()
        {
            IAppRouteConfig appRouteConfig = new AppRouteConfig();

            IApplication app = new MainApplication();
            app.Configure(appRouteConfig);

            MyCoolServer server = new MyCoolServer(1337, appRouteConfig);
            server.Run();
        }
    }
}
