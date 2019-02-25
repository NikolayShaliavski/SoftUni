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
        }
        private FileInfoResponse GetFileInfo(string fileName)
        {
            Console.WriteLine($"[REQUEST][{DateTime.Now.ToString("MM/dd/yyyy hh:mm:ss.fff tt")}] File: {fileName}");
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