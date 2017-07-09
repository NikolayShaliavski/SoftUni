using System.Collections.Generic;
using System.Threading.Tasks;

namespace CloudReplicator.CloudClients
{
    internal interface IClient
    {
        Task<List<string>> GetVolumes();

        Task<bool> CreateFolder(string containerName, string name, string parentId, string id);

        Task<bool> ReplicateFile(string containerName, string name, string parentId, string id, byte[] data);
    }
}
