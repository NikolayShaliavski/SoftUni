using System;
using System.Text;

class DecimalToBinary
{
    static void Main()
    {
        long input = long.Parse(Console.ReadLine());
        long digit = 0;
        StringBuilder binary = new StringBuilder();

        do
        {
            digit = input % 2;
            input /= 2;
            binary.Append(digit);
        } while (input >= 2);

        binary.Append('1');
        char[] binaryArray = binary.ToString().ToCharArray();
        Array.Reverse(binaryArray);
        binary = binary.Clear();

        foreach (char b in binaryArray)
        {
            binary.Append(b);
        }
        Console.WriteLine(binary);
    }
}
