using System;
using System.Linq;

class Numerology
{
    static void Main()
    {
        string input = Console.ReadLine();

        string[] dateName = input.Split('.', ' ');

        ulong celestial = ulong.Parse(dateName[0]) * ulong.Parse(dateName[1]) * ulong.Parse(dateName[2]);

        if (ulong.Parse(dateName[1]) % 2 != 0)
        {
            celestial *= celestial;
        }
        char[] name = dateName[3].ToCharArray();
        ulong asciiChar = 0;

        for (int i = 0; i < name.Length; i++)
        {
            if (name[i] >= 48 && name[i] <= 57)
            {
                asciiChar = (ulong)name[i] - 48;
                celestial += asciiChar;
            }
            else if (name[i] >= 65 && name[i] <= 90)
            {
                asciiChar = (ulong)name[i] - 64;
                celestial += (2 * asciiChar);
            }
            else if (name[i] >= 97 && name[i] <= 122)
            {
                asciiChar = (ulong)name[i] - 96;
                celestial += asciiChar;
            }
        }
        while (celestial > 13)
        {
            ulong[] digits = celestial.ToString().ToCharArray().Select(x => (ulong)Char.GetNumericValue(x)).ToArray();

            celestial = 0;

            for (int i = 0; i < digits.Length; i++)
            {
                celestial += digits[i];
            }
        }
        Console.WriteLine(celestial);
    }
}
