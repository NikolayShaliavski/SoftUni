using System.Text;

namespace SocialNetworkData.Helpers
{
    public static class TagTransformer
    {
        public static string Transform(string tag)
        {
            if (string.IsNullOrWhiteSpace(tag))
            {
                return tag;
            }
            StringBuilder builder = new StringBuilder();
            if (tag[0] != '#')
            {
                builder.Append("#");
            }
            int charactersAdded = 0;
            for (int i = 0; i < tag.Length; i++)
            {
                if (charactersAdded >= 20)
                {
                    break;
                }
                if (tag[i] == ' ')
                {
                    continue;
                }
                builder.Append(tag[i]);
                charactersAdded++;
            }

            return builder.ToString();
        }
    }
}
