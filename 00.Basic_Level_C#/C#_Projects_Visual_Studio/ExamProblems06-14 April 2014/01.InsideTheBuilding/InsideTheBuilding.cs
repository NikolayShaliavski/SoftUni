using System;
using System.Collections.Generic;

class InsideTheBuilding
{
    static void Main()
    {
        int height = int.Parse(Console.ReadLine());
        List<int> points = new List<int>();

        for (int i = 0; i < 5; i++)
        {
            int x = int.Parse(Console.ReadLine());
            int y = int.Parse(Console.ReadLine());

            points.Add(x);
            points.Add(y);
        }

        for (int i = 0; i < points.Count - 1; i += 2)
        {
            if (points[i] >= 0 && points[i] <= (3 * height) && points[i + 1] >= 0 && points[i + 1] <= height)
            {
                Console.WriteLine("inside");
            }
            else if (points[i] >= height && points[i] <= (2 * height) && points[i + 1] >= 0 && points[i + 1] <= (4 * height))
            {
                Console.WriteLine("inside");
            }
            else
            {
                Console.WriteLine("outside");
            }
        }
    }
}
