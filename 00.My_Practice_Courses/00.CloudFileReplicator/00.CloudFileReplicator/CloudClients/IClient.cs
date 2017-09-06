namespace CloudFileReplicator.CloudClients
{
    internal interface IClient
    {
        void CreateFolder(string containerName, string name, string parentId, string id);

        void ReplicateFile(string containerName, string name, string parentId, string id, byte[] data);
    }
}
