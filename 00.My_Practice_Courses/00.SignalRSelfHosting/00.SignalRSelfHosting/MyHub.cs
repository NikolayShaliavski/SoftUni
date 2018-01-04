using Microsoft.AspNet.SignalR;
using System.Collections.Generic;
using System.Timers;
using System.Threading.Tasks;

namespace _00.SignalRSelfHosting
{
    public class MyHub : Hub
    {
        private static readonly Timer timer = new Timer();
        private static readonly IHubContext hub;

        private static readonly List<string[]> history = new List<string[]>();

        static MyHub()
        {
            hub = GlobalHost.ConnectionManager.GetHubContext<MyHub>();
            timer.Interval = 1000;
            timer.Elapsed += TimerElapsed;
            timer.Start();
        }
        public override Task OnConnected()
        {
            string name = "";
            if (Context.QueryString != null)
            {
                name = Context.QueryString["username"];
            }
            if (name == null ||
                name == "")
            {
                name = "User";
            }
            Clients.Caller.addMessage("SERVER", $"Welcome, {name}. Lets start chat!!!");
            dynamic caller = Clients.Caller;
            foreach (var item in history)
            {
                Clients.Caller.addMessage(item[0], item[1]);
            }
            return base.OnConnected();
        }
        //public void AddUser(string name)
        //{
        //    Clients.Caller.addMessage("Santa Claus", $"Hello, {name}. Merry christmas!!!");
        //}
        public void Send(string name, string message)
        {
            history.Add(new string[] { name, message });
            Clients.All.addMessage(name, message);
        }
        static void TimerElapsed(object sender, ElapsedEventArgs e)
        {
            //hub.Clients.All.snow();
            //hub.Clients.All.addMessage("SERVER", "Hello from Server!!!");
            //hub.Clients.All.delete();
            //foreach (var item in history)
            //{
            //    hub.Clients.All.addMessage(item[0], item[1]);
            //}
        }
    }
}
