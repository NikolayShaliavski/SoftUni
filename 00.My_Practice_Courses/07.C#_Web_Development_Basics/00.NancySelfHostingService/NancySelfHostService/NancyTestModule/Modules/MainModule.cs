using Nancy;
using Nancy.ModelBinding;
using NancyTestModule.Models.Request;
using NancyTestModule.Models.Response;
using System;
using System.IO;
using System.Threading.Tasks;

namespace NancyTestModule.Modules
{
    public class MainModule : NancyModule
    {
        public MainModule()
        {
            Get["/"] = _ =>
            {
                return "<h1>Hello from Nancy self-hosting service!</h1>";
            };
            Post["/fileinfo"] = _ =>
            {
                FileInfoRequest req = this.Bind<FileInfoRequest>();
                FileInfoResponse fileInfo = this.GetFileInfo(req.Path);
                if (fileInfo == null)
                {
                    return 404;
                }
                return Response.AsJson(fileInfo);
            };
            Post["/fileinfoasync", runAsync: true] = async (x, ct) =>
            {
                FileInfoRequest req = this.Bind<FileInfoRequest>();
                FileInfoResponse fileInfo = await this.GetFileInfoAsync(req.Path);
                if (fileInfo == null)
                {
                    return 404;
                }
                return Response.AsJson(fileInfo);
            };
        }
        private FileInfoResponse GetFileInfo(string fileName)
        {
            Console.WriteLine($"[REQUEST GetFileInfo sync][{DateTime.Now.ToString("MM/dd/yyyy hh:mm:ss.fff tt")}] File: {fileName}");
            FileInfoResponse res = null;
            if (!File.Exists(fileName))
            {
                return res;
            }
            FileInfo file = new FileInfo(fileName);
            res = new FileInfoResponse(file.FullName, file.Length, file.CreationTime, file.LastWriteTime);
            return res;
        }
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