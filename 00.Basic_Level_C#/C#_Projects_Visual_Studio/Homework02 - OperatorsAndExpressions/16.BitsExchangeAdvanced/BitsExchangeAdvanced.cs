using System;
using System.Text;

class BitsExchangeAdvanced
{
    static void Main()
    {
        uint inputNumber = uint.Parse(Console.ReadLine());
        int firstPosition = int.Parse(Console.ReadLine());
        int secondPosition = int.Parse(Console.ReadLine());
        int numberOfPositions = int.Parse(Console.ReadLine());

        char[] bitsArray = Convert.ToString(inputNumber, 2).PadLeft(32, '0').ToCharArray();
        Array.Reverse(bitsArray);

        for (int i = firstPosition, j = secondPosition; i <= (firstPosition + numberOfPositions - 1); i++, j++)
        {
            char swap = bitsArray[i];
            bitsArray[i] = bitsArray[j];
            bitsArray[j] = swap;

        }

        Array.Reverse(bitsArray);
        StringBuilder bitsInString = new StringBuilder();
        for (int i = 0; i < bitsArray.Length; i++)
        {
            bitsInString.Append(bitsArray[i]);
        }
        string bits = bitsInString.ToString();
        ulong result = Convert.ToUInt64(bits, 2);
        Console.WriteLine(result);
    }
}
