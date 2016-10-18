using System;

class ExtractThirdBit
{
    static void Main()
    {
        int inputNumber = int.Parse(Console.ReadLine());

        int mask = 1 << 3;
        int thirdBit = (inputNumber & mask) >> 3;

        Console.WriteLine(thirdBit);
    }
}

