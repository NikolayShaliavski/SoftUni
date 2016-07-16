using System;
using System.Collections.Generic;
using System.Linq;

class PerimeterAndAreaPolygon
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        List<int> coordinates = new List<int>();
        List<double> area = new List<double>();
        List<double> perimeter = new List<double>();

        for (int i = 0; i < n; i++)
        {
            string[] points = Console.ReadLine().Split();
            int x = int.Parse(points[0]);
            int y = int.Parse(points[1]);

            coordinates.Add(x);
            coordinates.Add(y);
        }

        double side = 0;

        for (int i = 0; i < coordinates.Count - 2; i += 2)
        {
            side = Math.Pow((coordinates[i + 2] - coordinates[i]), 2) + Math.Pow((coordinates[i + 3] - coordinates[i + 1]), 2);
            side = Math.Sqrt(side);
            perimeter.Add(side);
        }

        double current = 0;

        for (int i = 0; i < coordinates.Count - 2; i += 2)
        {
            current = (double)(coordinates[i] * coordinates[i + 3]) - (coordinates[i + 1] * coordinates[i + 2]);
            area.Add(current);
        }

        double sumArea = area.Sum() / 2;
        double sumPerimeter = perimeter.Sum();

        Console.WriteLine("perimeter = {0:0.00}", sumPerimeter);
        Console.WriteLine("area = {0:0.00}", Math.Abs(sumArea));
    }
}
