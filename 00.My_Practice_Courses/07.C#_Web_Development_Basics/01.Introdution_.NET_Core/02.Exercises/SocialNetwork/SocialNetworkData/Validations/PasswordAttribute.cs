using System.ComponentModel.DataAnnotations;
using System.Text.RegularExpressions;

namespace SocialNetworkData.Validations
{
    public class PasswordAttribute : ValidationAttribute
    {
        private static readonly string PATTERN = @"^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+<>?])(.{6,50})$";

        public override bool IsValid(object value)
        {
            string password = value as string;
            if (string.IsNullOrEmpty(password))
            {
                this.ErrorMessage = "Password is empty.";
                return false;
            }
            Regex reg = new Regex(PATTERN);
            bool isValid = reg.IsMatch(password);
            if (!isValid)
            {
                this.ErrorMessage = "Password is invalid.";
                return false;
            }
            return true;
        }
    }
}
