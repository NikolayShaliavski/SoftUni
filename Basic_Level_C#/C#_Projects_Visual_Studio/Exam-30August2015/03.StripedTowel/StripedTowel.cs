using System;

class StripedTowel
{
    static void Main()
    {
        int width = int.Parse(Console.ReadLine());
        int height = (int)Math.Floor(width * 1.5);

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if ((j + i) % 3 == 0)
                {
                    Console.Write("#");
                }
                else
                {
                    Console.Write(".");
                }
            }
            Console.WriteLine();
        }
    }
}