using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _01.FallenInLove
{
    class FallenInLove
    {
        static void Main(string[] args)
        {
            char dot = '.';
            char hashTag = '#';
            char tilda = '~';

            int n = int.Parse(Console.ReadLine());

            int dots = n * 2;
            for (int i = 0; i < n; i++)
            {
                Console.Write(new string(hashTag, 1));
                Console.Write(new string(tilda, i));
                Console.Write(new string(hashTag, 1));

                Console.Write(new string(dot, dots));
                Console.Write(new string(hashTag, 1));

                Console.Write(new string(dot, i * 2));

                Console.Write(new string(hashTag, 1));
                Console.Write(new string(dot, dots));

                Console.Write(new string(hashTag, 1));
                Console.Write(new string(tilda, i));
                Console.WriteLine(new string(hashTag, 1));

                dots -= 2;
            }
            int outerDots = 1;
            dots = n * 2;
            for (int i = n; i > 0; i--)
            {
                Console.Write(new string(dot, outerDots));

                Console.Write(new string(hashTag, 1));
                Console.Write(new string(tilda, i));
                Console.Write(new string(hashTag, 1));

                Console.Write(new string(dot, dots));

                Console.Write(new string(hashTag, 1));
                Console.Write(new string(tilda, i));
                Console.Write(new string(hashTag, 1));

                Console.WriteLine(new string(dot, outerDots));

                outerDots += 2;
                dots -= 2;
            }
            Console.Write(new string(dot, outerDots));
            Console.Write(new string(hashTag, 4));
            Console.WriteLine(new string(dot, outerDots));

            outerDots++;
            for (int i = 0; i < n; i++)
            {
                Console.Write(new string(dot, outerDots));
                Console.Write(new string(hashTag, 2));
                Console.WriteLine(new string(dot, outerDots));
            }
        }
    }
}
