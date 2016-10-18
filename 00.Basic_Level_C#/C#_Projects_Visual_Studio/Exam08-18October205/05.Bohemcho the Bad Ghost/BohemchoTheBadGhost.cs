using System;
using System.Linq;

class BohemchoTheBadGhost
{
    static void Main()
    {
        string floor = Console.ReadLine();
        int count = 0;
        ulong score = 0;

        while (floor != "Stop, God damn it")
        {
            uint number = uint.Parse(floor);
            int[] rooms = Console.ReadLine().Split().Select(x => Convert.ToInt32(x)).ToArray();
            uint mask = 1;
            uint current = 0;

            for (int i = 0; i < rooms.Length; i++)
            {
                int position = rooms[i];
                number = number ^ (mask << position);
            }

            for (int i = 0; i < 32; i++)
            {
                current = (number >> i) & 1;
                if (current == 1)
                {
                    count++;
                }
            }

            score += number;

            floor = Console.ReadLine();
        }
        Console.WriteLine("Bohemcho left {0} lights on and his score is {1}", count, score);
    }
}
