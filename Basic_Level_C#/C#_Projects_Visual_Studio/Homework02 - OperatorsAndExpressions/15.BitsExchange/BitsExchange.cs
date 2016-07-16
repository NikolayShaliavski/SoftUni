using System;
using System.Text;

class BitsExchange
{
    static void Main()
    {
        int inputNumber = int.Parse(Console.ReadLine());
        char[] bitsArray = Convert.ToString(inputNumber, 2).PadLeft(32, '0').ToCharArray();
        Array.Reverse(bitsArray);

        if (bitsArray[3] != bitsArray[24])
        {
            char swap = bitsArray[3];
            bitsArray[3] = bitsArray[24];
            bitsArray[24] = swap;
        }
        if (bitsArray[4] != bitsArray[25])
        {
            char swap = bitsArray[4];
            bitsArray[4] = bitsArray[25];
            bitsArray[25] = swap;
        }
        if (bitsArray[5] != bitsArray[26])
        {
            char swap = bitsArray[5];
            bitsArray[5] = bitsArray[26];
            bitsArray[26] = swap;
        }
        Array.Reverse(bitsArray);
        StringBuilder bitsInString = new StringBuilder();
        for (int i = 0; i < bitsArray.Length; i++)
        {
            bitsInString.Append(bitsArray[i]);
        }
        string bits = bitsInString.ToString();
        int result = Convert.ToInt32(bits, 2);
        Console.WriteLine(result);
    }
}

