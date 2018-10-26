using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Drawing;

namespace SocialNetworkData.Models
{
    public class Album
    {
        public int Id { get; set; }

        [Required]
        public string Name { get; set; }

        public KnownColor BackgroundColor { get; set; }

        public bool IsPublic { get; set; }

        public int UserId { get; set; }

        public User Owner { get; set; }

        public List<AlbumPicture> Pictures { get; set; } = new List<AlbumPicture>();
    }
}
