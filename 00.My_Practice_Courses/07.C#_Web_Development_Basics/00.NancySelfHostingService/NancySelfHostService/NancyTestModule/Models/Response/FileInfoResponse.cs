using System;

namespace NancyTestModule.Models.Response
{
    public class FileInfoResponse
    {
        public string Name { get; set; }
        public long Size { get; set; }
        public DateTime CreationDate { get; set; }
        public DateTime LastModified { get; set; }
        public FileInfoResponse(string _name,
                                long _size,
                                DateTime _creationDate,
                                DateTime _lastModified)
        {
            this.Name = _name;
            this.Size = _size;
            this.CreationDate = _creationDate;
            this.LastModified = _lastModified;
        }
    }
}
