using System;
using System.Configuration.Install;
using System.Reflection;
using System.ServiceProcess;

namespace NancySelfHost
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
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
                NancyService nancyService = new NancyService();
                nancyService.TestAsConsoleApp(args);
                return;
            }
            ServiceBase[] ServicesToRun;
            ServicesToRun = new ServiceBase[]
            {
                new NancyService()
            };
            ServiceBase.Run(ServicesToRun);
        }
    }
}
