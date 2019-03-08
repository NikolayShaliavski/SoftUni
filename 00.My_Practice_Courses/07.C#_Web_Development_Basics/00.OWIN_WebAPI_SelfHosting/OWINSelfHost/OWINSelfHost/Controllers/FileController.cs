using OWINSelfHost.Models.Request;
using OWINSelfHost.Models.Response;
using System;
using System.IO;
using System.Threading.Tasks;
using System.Web.Http;

namespace OWINSelfHost.Controllers
{
    public class FileController : ApiController
    {
        public async Task<FileInfoResponse> Post([FromBody]FileInfoRequest req)
        {
            FileInfoResponse fileInfo = await this.GetFileInfoAsync(req.Path);
            if (fileInfo == null)
            {
                return null;
            }
            return fileInfo;
        }
        //private FileInfoResponse GetFileInfo(string fileName)
        //{
        //    Console.WriteLine($"[REQUEST GetFileInfo sync][{DateTime.Now.ToString("MM/dd/yyyy hh:mm:ss.fff tt")}] File: {fileName}");
        //    FileInfoResponse res = null;
        //    if (!File.Exists(fileName))
        //    {
        //        return res;
        //    }
        //    FileInfo file = new FileInfo(fileName);
        //    res = new FileInfoResponse(file.FullName, file.Length, file.CreationTime, file.LastWriteTime);
        //    return res;
        //}
        private async Task<FileInfoResponse> GetFileInfoAsync(string fileName)
        {
            await Task.Delay(5000);
            Console.WriteLine($"[REQUEST GetFileInfo async][{DateTime.Now.ToString("MM/dd/yyyy hh:mm:ss.fff tt")}] File: {fileName}");
            FileInfoResponse res = null;
            if (!File.Exists(fileName))
            {
                return res;
            }
            FileInfo file = new FileInfo(fileName);
            res = new FileInfoResponse(file.FullName, file.Length, file.CreationTime, file.LastWriteTime);
            return res;
        }
    }
}
