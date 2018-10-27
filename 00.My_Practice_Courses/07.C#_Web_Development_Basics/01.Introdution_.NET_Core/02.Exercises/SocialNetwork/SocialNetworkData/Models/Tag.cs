using System.Collections.Generic;

namespace SocialNetworkData.Models
{
    public class Tag
    {
        public int Id { get; set; }

        public string TagValue { get; set; }

        public List<AlbumTag> ALbums { get; set; } = new List<AlbumTag>();
    }
}
