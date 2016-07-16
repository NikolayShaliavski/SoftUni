using System;

class TeleportPoints
{
    static void Main()
    {
        string[] cornerA = Console.ReadLine().Split();
        string[] cornerB = Console.ReadLine().Split();
        string[] cornerC = Console.ReadLine().Split();
        string[] cornerD = Console.ReadLine().Split();

        double radius = double.Parse(Console.ReadLine());
        double step = double.Parse(Console.ReadLine());
        int teleport = 0;

        for (double i = 0; i < radius; i += 0.1)
        {
            for (int j = 0; j < radius; j++)
            {
                if ((radius * radius) >= (i * i) + (j * j))
                {
                    teleport++;
                }
            }
        }
        Console.WriteLine(teleport);
    }
}
