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
            char space = ' ';
            char star = '*';
            char verticalLine = '|';
            char horizontalLine = '-';
            char plus = '+';

            int n = int.Parse(Console.ReadLine());

            Console.Write(new string(dot, n - 1));
            Console.Write(star);
            Console.WriteLine(new string(dot, n - 1));

            int spaceCount = 1;
            for (int i = n - 2; i > 0; i--)
            {
                Console.Write(new string(dot, i));
                Console.Write(star);
                Console.Write(new string(space, spaceCount));
                Console.Write(star);
                Console.WriteLine(new string(dot, i));
                spaceCount += 2;
            }

            for (int i = 0; i < n * 2 - 1; i++)
            {
                if (i % 2 == 0) {
                    Console.Write(new string(star, 1));
                }
                else
                {
                    Console.Write(new string(space, 1));
                }
            }

            Console.WriteLine(new string(space, n * 2 - 1));

            Console.Write(plus);
            Console.Write(new string(horizontalLine, n * 2 - 3));
            Console.WriteLine(plus);

            for (int i = 0; i < n - 2; i++)
            {
                Console.Write(verticalLine);
                Console.Write(new string(space, n * 2 - 3));
                Console.WriteLine(verticalLine);
            }
            Console.Write(plus);
            Console.Write(new string(horizontalLine, n * 2 - 3));
            Console.WriteLine(plus);
        }
    }
}
