using System;

namespace WebServer.Server.Validation
{
    public static class CoreValidator
    {
        public static void ThrowIfNull(object value, string name)
        {
            if (value == null)
            {
                throw new NullReferenceException($"Value of {name} cannot be null.");
            }
        }
        public static void ThrowIfNullOrEmpty(string value)
        {
            if (string.IsNullOrWhiteSpace(value))
            {
                throw new InvalidOperationException($"Value cannot be null or empty.");
            }
        }
    }
}
