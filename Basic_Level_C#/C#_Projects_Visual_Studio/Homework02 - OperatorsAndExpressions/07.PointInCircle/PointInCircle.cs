using System;

class PointInCircleAndOutsideRectangle
{
    static void Main()
    {
        Console.WriteLine("Please insert coordinates of the point:");
        Console.Write("x = ");
        double x = double.Parse(Console.ReadLine());
        Console.Write("y = ");
        double y = double.Parse(Console.ReadLine());

        bool pointInCircle = ((x * x) + (y * y) <= (1.5 * 1.5));
        Console.WriteLine(pointInCircle);
    }
}

