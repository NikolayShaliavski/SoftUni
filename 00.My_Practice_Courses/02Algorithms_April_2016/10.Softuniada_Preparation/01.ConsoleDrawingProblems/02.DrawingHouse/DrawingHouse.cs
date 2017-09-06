using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _02.DrawingHouse
{
    class DrawingHouse
    {
        static void Main(string[] args)
        {
            char dot = '.';
            char line = '_';
            char xor = '^';
            char righte = '/';
            char left = '\\';
            char vertical = '|';

            int n = int.Parse(Console.ReadLine());

            Console.Write(new string(dot, n - 1));
            Console.Write("_/_");
            Console.WriteLine(new string(dot, n - 1));

            Console.Write("/");
            Console.Write(new string(dot, n - 2));
            Console.Write("^,^");
            Console.Write(new string(dot, n - 2));
            Console.WriteLine("\\");

            for (int i = 0; i < n - 3; i++)
            {
                Console.Write("|");
                Console.Write(new string(dot, n * 2 - 1));
                Console.WriteLine("|");
            }

            Console.Write("\\");
            Console.Write(new string(dot, n - 2));
            Console.Write("\\_/");
            Console.Write(new string(dot, n - 2));
            Console.WriteLine("/");
        }
    }
}
