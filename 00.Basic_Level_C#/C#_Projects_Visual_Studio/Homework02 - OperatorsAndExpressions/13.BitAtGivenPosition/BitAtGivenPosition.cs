using System;

class BitAtGivenPosition
{
    static void Main()
    {
        int inputNumber = int.Parse(Console.ReadLine());
        int position = int.Parse(Console.ReadLine());

        int mask = 1 << position;
        int bitOnPosition = (inputNumber & mask) >> position;
        bool result = (bitOnPosition == 1);

        Console.WriteLine(result);
    }
}
