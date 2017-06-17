using CloudFileReplicator.CloudClients;
using System;
using System.IO;
using System.Text;

namespace CloudFileReplicator
{
    class CloudReplicatorMain
    {
        static void Main(string[] args)
        {
            IClient azureClient = new AzureClient();

            Replicate(azureClient, "nick2", "00000000000000000000000000000000", "C:\\Users\\Nikolay\\Desktop\\Root");
        }

        private static void Replicate(IClient client, string containerName, string parentId, string path)
        {
            int index = path.LastIndexOf("\\");
            string name = path.Substring(index + 1); ;

            string id = GenerateGuid();
            if (Directory.Exists(path))
            {
                client.CreateFolder(containerName, name, parentId, id);

                string[] content = Directory.GetFileSystemEntries(path, "*", SearchOption.TopDirectoryOnly);
                foreach (var subPath in content)
                {
                    Replicate(client, containerName, id, subPath);
                }
            }
            else if (File.Exists(path))
            {
                byte[] data = File.ReadAllBytes(path);
                client.ReplicateFile(containerName, name, parentId, id, data);
            }
        }
        private static string GenerateGuid()
        {
            string guid = Guid.NewGuid().ToString();
            guid = guid.Replace("-", "");

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < guid.Length; i++)
            {
                char symbol = guid[i];
                if (symbol >= 97 && symbol <= 122)
                {
                    symbol = (char)(symbol- 32);
                }
                builder.Append(symbol);
            }
            return builder.ToString();
        }
    }
}
