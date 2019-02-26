using Owin;

namespace OwinSelfHost
{
    public class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            app.MaxConcurrentRequests(-1);
            app.UseNancy();
        }
    }
}
