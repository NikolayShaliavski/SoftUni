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

        // The owner of the album - user which has created it - many-to-one
        public User Owner { get; set; }

        // Users with which the album is shared - many-to-many
        public List<UserAlbum> SharedAlbums { get; set; } = new List<UserAlbum>();

        public List<AlbumPicture> Pictures { get; set; } = new List<AlbumPicture>();

        public List<AlbumTag> Tags { get; set; } = new List<AlbumTag>();
    }
}
