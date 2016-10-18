using System;

class BitBuilder
{
    static void Main()
    {
        long number = int.Parse(Console.ReadLine());

        string position = Console.ReadLine();

        while (position != "quit")
        {
            int bit = int.Parse(position);
            string command = Console.ReadLine();
            long right = 0;

            switch (command)
            {
                case "flip":
                    number ^= (1 << bit);               
                    break;
                case "remove":
                    right = number & ((1 << bit) - 1);
                    number = number >> bit + 1;
                    number = (number << bit) | right;
                    break;
                case "insert":
                    right = number & ((1 << bit) - 1);
                    number = number >> bit;
                    number = (number << 1) | 1;
                    number = (number << bit) | right;
                    break;
                default:
                    break;
            }
            position = Console.ReadLine();
        }
        Console.WriteLine(number);
    }
}
