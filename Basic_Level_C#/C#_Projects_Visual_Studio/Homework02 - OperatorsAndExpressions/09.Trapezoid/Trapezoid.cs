using System;

class Trapezoid
{
    static void Main()
    {
        Console.WriteLine("Please enter a, b and h of trapezoid: ");
        Console.Write("a = ");
        double sideA = double.Parse(Console.ReadLine());
        Console.Write("b = ");
        double sideB = double.Parse(Console.ReadLine());
        Console.Write("h = ");
        double height = double.Parse(Console.ReadLine());

        double area = ((sideA + sideB) / 2) * height;

        Console.WriteLine("The area of trapezoid is: " + area);
    }
}
