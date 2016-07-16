using System;

class Rectangle
{
    static void Main()
    {
        double width = double.Parse(Console.ReadLine());
        double height = double.Parse(Console.ReadLine());

        double perimeter = 2 * (width + height);
        double area = width * height;
        Console.WriteLine("Perimeter of the rectangle is {0}.", perimeter);
        Console.WriteLine("Area of the rectangle is {0}.", area);

    }
}

