using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace SocialNetworkData.Models
{
    public class Picture
    {
        public int Id { get; set; }

        [Required]
        public string Title { get; set; }

        public string Caption { get; set; }

        [Required]
        public string Path { get; set; }

        public List<AlbumPicture> Albums { get; set; } = new List<AlbumPicture>();
    }
}
