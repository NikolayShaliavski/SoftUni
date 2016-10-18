using System;

class HexadecimalToDecimal
{
    static void Main()
    {
        string input = Console.ReadLine();
        long number = 0;
        long sum = 0;
        long length = input.Length;

        for (int i = 0; i < length; i++)
        {
            bool parse = long.TryParse(input[i].ToString(), out number);
            sum += number * (long)Math.Pow(16, (length - i - 1));
            if (parse == false)
            {
                switch (input[i])
                {
                    case 'A':
                        sum += 10 * (long)Math.Pow(16, (length - i - 1));
                        break;
                    case 'B':
                        sum += 11 * (long)Math.Pow(16, (length - i - 1));
                        break;
                    case 'C':
                        sum += 12 * (long)Math.Pow(16, (length - i - 1));
                        break;
                    case 'D':
                        sum += 13 * (long)Math.Pow(16, (length - i - 1));
                        break;
                    case 'E':
                        sum += 14 * (long)Math.Pow(16, (length - i - 1));
                        break;
                    case 'F':
                        sum += 15 * (long)Math.Pow(16, (length - i - 1));
                        break;
                    default:
                        break;
                }
            }
        }
        Console.WriteLine(sum);
    }
}