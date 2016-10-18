using System;

class Plane
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        int outerDots = (n * 3) / 2;

        Console.WriteLine("{0}*{0}", new string('.', outerDots));

        outerDots--;
        int middleDots = 1;

        for (int i = 0; i < (n / 2) + 2; i++)
        {
            Console.WriteLine("{0}*{1}*{0}", new string('.', outerDots), new string('.', middleDots));
            outerDots--;
            middleDots += 2;
        }

        outerDots--;
        middleDots += 2;

        for (int i = 0; i < (n / 2) - 1; i++)
        {
            Console.WriteLine("{0}*{1}*{0}", new string('.', outerDots), new string('.', middleDots));
            outerDots -= 2;
            middleDots += 4;
        }

        middleDots = n;
        int innerDots = n - 2;

        Console.WriteLine("*{0}*{1}*{0}*", new string('.', innerDots), new string('.', middleDots));

        innerDots -= 2;
        outerDots = 1;

        for (int i = 0; i < (n / 2) - 1; i++)
        {
            Console.WriteLine("*{0}*{1}*{2}*{1}*{0}*", new string('.', innerDots),new string('.', outerDots), new string('.', middleDots));
            innerDots -= 2;
            outerDots += 2;
        }

        outerDots += 1;

        for (int i = 0; i < n - 1; i++)
        {
            Console.WriteLine("{0}*{1}*{0}", new string('.', outerDots), new string('.', middleDots));
            outerDots--;
            middleDots += 2;
        }

        Console.WriteLine("{0}", new string('*', n * 3));
    }
}
