using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class Program
{
    static void Main()
    {
        double a = double.Parse(Console.ReadLine());
        double b = double.Parse(Console.ReadLine());

        double greaterValue = Math.Max(a, b);
        double smallerValue = Math.Min(a, b);

        bool result = (greaterValue - smallerValue < 0.000001);

        Console.WriteLine(result);
    }
}

