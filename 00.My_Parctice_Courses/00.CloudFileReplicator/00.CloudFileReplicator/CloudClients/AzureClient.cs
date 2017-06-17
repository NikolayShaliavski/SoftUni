using Microsoft.WindowsAzure.Storage;
using Microsoft.WindowsAzure.Storage.Blob;
using Microsoft.WindowsAzure.Storage.RetryPolicies;
using System;
using System.Text;

namespace CloudFileReplicator.CloudClients
{
    class AzureClient : IClient
    {
        private const string AzureMetaNameKey = "name";
        private const string AzureMetaCreationDateKey = "crtime";
        private const string AzureMetaAccessDateKey = "acctime";
        private const string AzureMetaFlagKey = "flags";

        private const string DataKeyPrefix = ".tt_rt/data/";
        private const string MetaKeyPrefix = ".tt_rt/meta/";

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

        public void CreateFolder(string containerName, string name, string parentId, string id)
        {
            string key = MetaKeyPrefix + parentId + "/" + id;

            byte[] bytes = Encoding.ASCII.GetBytes(name);
            string nameDecoded = Convert.ToBase64String(bytes);
            TimeSpan span = DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc));
            string time = Convert.ToUInt64(span.TotalSeconds).ToString();
            CloudBlobContainer container = this.client.GetContainerReference(containerName);
            bool success = this.CreateBlob(container, key, nameDecoded, time, time, "d", new byte[0]);
            if (success)
            {
                Console.WriteLine(string.Format("Successfully created meta object for folder: {0}.", name));
            }
            else
            {
                Console.WriteLine(string.Format("Creating meta object failed, folder name: {0}.", name));
            }
        }

        public void ReplicateFile(string containerName,string name, string parentId, string id, byte[] data)
        {
            string metaKey = MetaKeyPrefix + parentId + "/" + id;
            string dataKey = DataKeyPrefix + id;

            byte[] bytes = Encoding.ASCII.GetBytes(name);
            string nameDecoded = Convert.ToBase64String(bytes);
            TimeSpan span = DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc));
            string time = Convert.ToUInt64(span.TotalSeconds).ToString();
            CloudBlobContainer container = this.client.GetContainerReference(containerName);

            bool success = this.CreateBlob(container, metaKey, nameDecoded, time, time, "r", new byte[0]);
            if (success)
            {
                Console.WriteLine(string.Format("Successfully created meta object for file: {0}.", name));
            }
            else
            {
                Console.WriteLine(string.Format("Creating meta object failed, file name: {0}.", name));
                return;
            }

            success = this.CreateBlob(container, dataKey, nameDecoded, data);
            if (success)
            {
                Console.WriteLine(string.Format("Successfully created data object for file: {0}.", name));
            }
            else
            {
                Console.WriteLine(string.Format("Creating data object failed, file name: {0}.", name));
            }
        }

        private bool CreateBlob(CloudBlobContainer container, string key, string name, string creationTime, string accessTime, string flag, byte[] data)
        {
            bool success = true;
            try
            {
                CloudBlockBlob blob = container.GetBlockBlobReference(key);
                blob.Metadata.Add(AzureMetaNameKey, name);
                blob.Metadata.Add(AzureMetaCreationDateKey, creationTime);
                blob.Metadata.Add(AzureMetaAccessDateKey, accessTime);
                if (flag != null)
                {
                    blob.Metadata.Add(AzureMetaFlagKey, flag);
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
                blob.Metadata.Add(AzureMetaNameKey, name);

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
