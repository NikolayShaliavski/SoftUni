using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

class IsoscelesTriangle
{
    static void Main()
    {
        Console.Write("Please insert a number to choose the height of the triangle:");
        int n = int.Parse(Console.ReadLine());

        string firstSpace = new string(' ', n);
        string secondSpace = new string(' ', (n - 1));

        Console.WriteLine(firstSpace + "©" + firstSpace);
        Console.WriteLine(secondSpace + "©" + " " + "©" + secondSpace);

        for (int i = 1; i < (n - 1); i++)
        {
            string space = new string(' ', (n - i - 1));
            string middleSpace = new string(' ', i * 2 + 1);

            Console.WriteLine("{0}" + "©" + "{1}" + "©" + "{0}", space, middleSpace);
        }
        for (int i = 0; i <= n; i++)
        {
            Console.Write("©" + " ");
        }
    }
}

