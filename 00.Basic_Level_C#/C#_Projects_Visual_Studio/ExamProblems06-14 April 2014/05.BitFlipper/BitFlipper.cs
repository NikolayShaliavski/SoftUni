using System;

class BitFlipper
{
    static void Main()
    {
        ulong number = ulong.Parse(Console.ReadLine());
        ulong mask = 7;
        int position = 0;
        ulong current = 0;

        for (int i = 3; i <= 64; i++)
        {
            position = 64 - i;
            current = (number >> position) & mask;

            if (current == 0 || current == 7)
            {
                number = number ^ (mask << position);
                i += 2;
            }
        }
        Console.WriteLine(number);
    }
}
