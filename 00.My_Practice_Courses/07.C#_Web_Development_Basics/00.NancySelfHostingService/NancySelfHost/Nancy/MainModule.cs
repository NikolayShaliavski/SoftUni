using Nancy;

namespace NancySelfHost.Nancy
{
    public class MainModule : NancyModule
    {
        public MainModule()
        {
            Get["/"] = _ =>
            {
                return "<h1>Hello from Nancy self hosting service!</h1>";
            };
        }
    }
}