using System.ComponentModel.DataAnnotations;

namespace SocialNetworkData.Validations
{
    public class TagAttribute : ValidationAttribute
    {
        public override bool IsValid(object value)
        {
            string tag = value as string;
            if (string.IsNullOrEmpty(tag))
            {
                return false;
            }
            return tag.StartsWith("#") &&
                   !tag.Contains(" ") &&
                   tag.Length <= 20;
        }
    }
}
