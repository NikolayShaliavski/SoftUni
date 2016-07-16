using System;

class ModifyBits
{
    static void Main()
    {
        int inputNumber = int.Parse(Console.ReadLine());
        int position = int.Parse(Console.ReadLine());
        int valueOfBit = int.Parse(Console.ReadLine());

        if (valueOfBit == 1)
        {
            int mask = 1 << position;
            int result = (inputNumber | mask);
            Console.WriteLine(result);
        }
        else if (valueOfBit == 0)
        {
            int mask = ~(1 << position);
            int result = (inputNumber & mask);
            Console.WriteLine(result);
        }

    }
}

