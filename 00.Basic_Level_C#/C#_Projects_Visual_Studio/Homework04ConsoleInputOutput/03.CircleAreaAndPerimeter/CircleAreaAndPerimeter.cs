using System;

class CircleAreaAndPerimeter
{
    static void Main()
    {
        Console.WriteLine("This programm calculates circle area and perimeter.");
        Console.Write("Please insert circle radius: ");
        double circleRadius = double.Parse(Console.ReadLine());

        double perimeter = (circleRadius * 2 * Math.PI);
        double area = (circleRadius * circleRadius * Math.PI);

        Console.WriteLine("The perimeter of the circle is: {0:F2}", perimeter);
        Console.WriteLine("The area of the circle is: {0:F2}", area);

    }
}

