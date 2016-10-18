using System;

class PointInCircle
{
    static void Main()
    {
        Console.WriteLine("Please insert coordinates of the point:");
        Console.Write("x = ");
        double x = double.Parse(Console.ReadLine());
        Console.Write("y = ");
        double y = double.Parse(Console.ReadLine());
        Console.WriteLine("Is the point in circle and outside the rectangle?");
        x -= 1; y -= 1;
        if ((x * x + y * y <= 1.5 * 1.5) && (y + 1) > 1)
        {
            Console.WriteLine("Yes!!!!!");
        }
        else
        {
            Console.WriteLine("No.");
        }
    }
}

