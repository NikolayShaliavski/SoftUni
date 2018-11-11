using System;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using WebServer.Server.Handlers;
using WebServer.Server.Handlers.Contracts;
using WebServer.Server.Http;
using WebServer.Server.Http.Contracts;
using WebServer.Server.Http.Response;
using WebServer.Server.Routing.Contracts;
using WebServer.Server.Validation;

namespace WebServer.Server
{
    public class ConnectionHandler
    {
        private readonly Socket client;

        private readonly IServerRouteConfig serverRouteConfig;

        public ConnectionHandler(Socket client, IServerRouteConfig serverRouteConfig)
        {
            CoreValidator.ThrowIfNull(client, nameof(client));
            CoreValidator.ThrowIfNull(serverRouteConfig, nameof(serverRouteConfig));

            this.client = client;
            this.serverRouteConfig = serverRouteConfig;
        }

        public async Task ProcessRequestAsync()
        {
            string request = await this.ReadRequest();

            IHttpContext context = new HttpContext(request);

            IRequestHandler handler = new HttpHandler(this.serverRouteConfig);

            IHttpResponse response = handler.Handle(context);

            ArraySegment<byte> toBytes = new ArraySegment<byte>(Encoding.UTF8.GetBytes(response.ToString()));

            await this.client.SendAsync(toBytes, SocketFlags.None);

            Console.WriteLine("===================Request===================");
            Console.WriteLine(request);
            Console.WriteLine("===================Response===================");
            Console.WriteLine(response.ToString());
            Console.WriteLine();

            this.client.Shutdown(SocketShutdown.Both);
        }

        private async Task<string> ReadRequest()
        {
            StringBuilder request = new StringBuilder();
            ArraySegment<byte> data = new ArraySegment<byte>(new byte[1024]);

            while(true)
            {
                int numBytesRead = await this.client.ReceiveAsync(data, SocketFlags.None);
                if (numBytesRead == 0)
                {
                    break;
                }
                request.Append(Encoding.UTF8.GetString(data.Array, 0, numBytesRead));

                if (numBytesRead < 1023)
                {
                    break;
                }
            }
            return request.ToString();
        }
    }
}
