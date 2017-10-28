using Amazon.S3;
using Amazon.S3.Model;
using CloudReplicator.CloudClients;
using CloudReplicator.Common;
using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace _00.CloudReplicator.CloudClients
{
    public class AmazonClient : IClient
    {
        private IAmazonS3 client;
        public AmazonClient(string service_url, string access_key, string secret_key)
        {
            AmazonS3Config s3Config = new AmazonS3Config();
            s3Config.ServiceURL = service_url;
            s3Config.ForcePathStyle = true;
            this.client = new AmazonS3Client(access_key, secret_key, s3Config);
        }
        public async Task<List<string>> GetVolumes()
        {
            List<string> buckets = new List<string>();
            try
            {
                ListBucketsResponse response = await client.ListBucketsAsync();
                foreach (S3Bucket bucket in response.Buckets)
                {
                    if (bucket == null)
                    {
                        continue;
                    }
                    buckets.Add(bucket.BucketName);
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
            }
            return buckets;
        }
        public async Task<bool> CreateFolder(string bucketName, string name, string parentId, string id)
        {
            bool success = true;
            try
            {
                string key = Constants.MetaKeyPrefix + parentId + "/" + id;

                byte[] bytes = Encoding.ASCII.GetBytes(name);
                string nameDecoded = Convert.ToBase64String(bytes);
                TimeSpan span = DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc));
                string time = Convert.ToUInt64(span.TotalSeconds).ToString();
                success = await this.CreateObject(bucketName, key, nameDecoded, time, time, "d", new byte[0]);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                success = false;
            }
            return success;
        }
        public async Task<bool> ReplicateFile(string bucketName, string name, string parentId, string id, byte[] data)
        {
            bool success = true;
            try
            {
                string metaKey = Constants.MetaKeyPrefix + parentId + "/" + id;
                string dataKey = Constants.DataKeyPrefix + id;

                byte[] bytes = Encoding.ASCII.GetBytes(name);
                string nameDecoded = Convert.ToBase64String(bytes);
                TimeSpan span = DateTime.UtcNow.Subtract(new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc));
                string time = Convert.ToUInt64(span.TotalSeconds).ToString();

                success = await this.CreateObject(bucketName, metaKey, nameDecoded, time, time, "r", new byte[0]);

                success = await this.CreateObject(bucketName, dataKey, nameDecoded, data);
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                success = false;
            }
            return success;
        }
        private async Task<bool> CreateObject(string bucketName, string key, string name, string creationTime, string accessTime, string flag, byte[] data)
        {
            bool success = true;
            try
            {
                using (Stream stream = new MemoryStream(data))
                {
                    PutObjectRequest req = new PutObjectRequest()
                    {
                        BucketName = bucketName,
                        Key = key,
                        InputStream = stream,
                        ContentType = "text/plain; charset=utf-8"
                    };
                    req.Metadata.Add(Constants.AmazonMetaNameKey, name);
                    req.Metadata.Add(Constants.AmazonMetaCreationDateKey, creationTime);
                    req.Metadata.Add(Constants.AmazonMetaAccessDateKey, accessTime);
                    req.Metadata.Add(Constants.AmazonMetaFlagKey, flag);

                    PutObjectResponse res = await this.client.PutObjectAsync(req);
                    if (res == null ||
                        res.HttpStatusCode != HttpStatusCode.OK)
                    {
                        success = false;
                    }
                }

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                success = false;
            }
            return success;
        }
        private async Task<bool> CreateObject(string bucketName, string key, string name, byte[] data)
        {
            bool success = true;
            try
            {
                using (Stream stream = new MemoryStream(data))
                {
                    PutObjectRequest req = new PutObjectRequest()
                    {
                        BucketName = bucketName,
                        Key = key,
                        InputStream = stream,
                        ContentType = "text/plain; charset=utf-8"
                    };
                    req.Metadata.Add(Constants.AmazonMetaNameKey, name);

                    PutObjectResponse res = await this.client.PutObjectAsync(req);
                    if (res == null ||
                        res.HttpStatusCode != HttpStatusCode.OK)
                    {
                        success = false;
                    }
                }

            }
            catch (Exception ex)
            {
                Console.WriteLine(ex);
                success = false;
            }
            return success;
        }
    }
}
