namespace SocialNetworkData.Models
{
    public class AlbumTag
    {
        public int ALbumId { get; set; }

        public Album Album { get; set; }

        public int TagId { get; set; }

        public Tag Tag { get; set; }
    }
}
