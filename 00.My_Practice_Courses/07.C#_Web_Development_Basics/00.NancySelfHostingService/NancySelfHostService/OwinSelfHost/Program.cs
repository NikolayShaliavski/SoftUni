using System;
using System.Configuration.Install;
using System.Reflection;
using System.ServiceProcess;

namespace OwinSelfHost
{
    class Program
    {
        static void Main(string[] args)
        {
            if (args.Length > 0)
            {
                //Install service
                if (args[0].Trim().ToLower() == "/i")
                {
                    ManagedInstallerClass.InstallHelper(new string[] {
                        "/i", Assembly.GetExecutingAssembly().Location
                    });
                }
                //Uninstall service                 
                else if (args[0].Trim().ToLower() == "/u")
                {
                    ManagedInstallerClass.InstallHelper(new string[] {
                        "/u", Assembly.GetExecutingAssembly().Location
                    });
                }
                return;
            }
            if (Environment.UserInteractive)
            {
                OwinService owinService = new OwinService();
                owinService.TestAsConsoleApp(args);
                return;
            }
            ServiceBase[] ServicesToRun;
            ServicesToRun = new ServiceBase[]
            {
                new OwinService()
            };
            ServiceBase.Run(ServicesToRun);
        }
    }
}
