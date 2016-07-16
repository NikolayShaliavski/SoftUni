using System;

class BinaryToDecimal
{
    static void Main()
    {
        string binaryNumber = Console.ReadLine();
        long numberInDecimal = 0;

        for (int i = 0; i < binaryNumber.Length - 1; i++)
        {
            int element = int.Parse(binaryNumber[i].ToString());
            element *= (int)Math.Pow(2, (binaryNumber.Length - 1 - i));
            numberInDecimal += element;
        }
        if (binaryNumber[binaryNumber.Length - 1] == '1')
        {
            Console.WriteLine(numberInDecimal + 1);
        }
        else
        {
            Console.WriteLine(numberInDecimal);
        }
    }
}
