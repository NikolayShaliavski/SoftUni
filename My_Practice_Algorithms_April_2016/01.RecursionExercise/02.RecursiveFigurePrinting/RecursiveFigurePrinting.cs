using System;

namespace _02.RecursiveFigurePrinting
{
    class RecursiveFigurePrinting
    {
        static void Main(string[] args)
        {
            int count = int.Parse(Console.ReadLine());
            PrintFigure(count);
        }

        private static void PrintFigure(int count)
        {
            //bottom of the recursion
            if (count == 0)
            {
                return;
            }
            //Pre-Action -> code executed before recursive call
            Console.WriteLine(new string('*', count));

            //Recursively call
            PrintFigure(count - 1);

            //Post-Action -> code executed after recursive call(when recursion goes back)
            Console.WriteLine(new string('#', count));
        }
    }
}
