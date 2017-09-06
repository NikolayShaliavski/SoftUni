using CloudReplicator.Common;
using Microsoft.WindowsAzure.Storage;
using Microsoft.WindowsAzure.Storage.Blob;
using Microsoft.WindowsAzure.Storage.RetryPolicies;
using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using System.Threading.Tasks;

namespace CloudReplicator.CloudClients
{
    class AzureClient : IClient
    {
        private CloudBlobClient client;

        public AzureClient()
        {
            Init("DefaultEndpointsProtocol=http;" +
                "AccountName=devstoreaccount1;" +
                "AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;" +
                "BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;",
                "Azure (local)");
        }
        public AzureClient(string account_name, string account_key)
        {
            if (account_name == "devstoreaccount1" &&
                account_key == "Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==")
            {
                Init("DefaultEndpointsProtocol=http;" +
                "AccountName=devstoreaccount1;" +
                "AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;" +
                "BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;",
                "Azure (local)");
            }
            else
            {
                Init("DefaultEndpointsProtocol=https;AccountName=" + account_name + ";AccountKey=" + account_key + ";",
                "Azure");
            }
        }
        private void Init(string connection_string, string root_name)
        {
            CloudStorageAccount account = CloudStorageAccount.Parse(connection_string);
            this.client = account.CreateCloudBlobClient();
            this.client.DefaultRequestOptions.RetryPolicy = new NoRetry();
        }
        public async Task<List<string>> GetVolumes()
        {
            List<string> children = new List<string>();
            BlobContinuationToken continuationToken = null;
            ContainerResultSegment resultSegment = null;
            do
            {
                resultSegment = await this.client.ListContainersSegmentedAsync(continuationToken);
                foreach (CloudBlobContainer container in resultSegment.Results)
                {
                    children.Add(container.Name);
                }
                continuationToken = resultSegment.ContinuationToken;
            } while (continuationToken != null);
            return children;
        }
        public async Task<bool> CreateFolder(string containerName, string name, string parentId, string id)
        {
            string key = Constants.MetaKeyPrefix + parentId + "/" + id;

            byte[] bytes = Encoding.ASCII.GetBytes(name);
            string nameDecoded = Convert.ToBase64String(bytes);
            TimeSpan span = DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc));
            string time = Convert.ToUInt64(span.TotalSeconds).ToString();
            CloudBlobContainer container = this.client.GetContainerReference(containerName);
            bool success = this.CreateBlob(container, key, nameDecoded, time, time, "d", new byte[0]);
            return success;
        }
        public async Task<bool> ReplicateFile(string containerName, string name, string parentId, string id, byte[] data)
        {
            string metaKey = Constants.MetaKeyPrefix + parentId + "/" + id;
            string dataKey = Constants.DataKeyPrefix + id;

            byte[] bytes = Encoding.ASCII.GetBytes(name);
            string nameDecoded = Convert.ToBase64String(bytes);
            TimeSpan span = DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc));
            string time = Convert.ToUInt64(span.TotalSeconds).ToString();
            CloudBlobContainer container = this.client.GetContainerReference(containerName);

            bool success = this.CreateBlob(container, metaKey, nameDecoded, time, time, "r", new byte[0]);

            success = this.CreateBlob(container, dataKey, nameDecoded, data);
            return success;
        }
        private bool CreateBlob(CloudBlobContainer container, string key, string name, string creationTime, string accessTime, string flag, byte[] data)
        {
            bool success = true;
            try
            {
                CloudBlockBlob blob = container.GetBlockBlobReference(key);
                blob.Metadata.Add(Constants.AzureMetaNameKey, name);
                blob.Metadata.Add(Constants.AzureMetaCreationDateKey, creationTime);
                blob.Metadata.Add(Constants.AzureMetaAccessDateKey, accessTime);
                if (flag != null)
                {
                    blob.Metadata.Add(Constants.AzureMetaFlagKey, flag);
                }

                blob.Properties.ContentType = "text/plain; charset=utf-8";
                blob.UploadFromByteArray(data, 0, data.Length);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                Console.WriteLine(ex.StackTrace);
                success = false;
            }
            return success;
        }
        private bool CreateBlob(CloudBlobContainer container, string key, string name, byte[] data)
        {
            bool success = true;
            try
            {
                CloudBlockBlob blob = container.GetBlockBlobReference(key);
                blob.Metadata.Add(Constants.AzureMetaNameKey, name);

                blob.Properties.ContentType = "text/plain; charset=utf-8";
                blob.UploadFromByteArray(data, 0, data.Length);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
                Console.WriteLine(ex.StackTrace);
                success = false;
            }
            return success;
        }
    }
}
