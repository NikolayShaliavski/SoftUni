using System;

class Roklq
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        int dots = n;
        int stars = n;

        Console.WriteLine("{0}{1}{0}", new string('.', dots), new string('*', stars));

        dots -= 2;
        int middleDots = n + 2;
        for (int i = 0; i < n / 2; i++)
        {
            Console.WriteLine("{0}*{1}*{0}", new string('.', dots), new string('.', middleDots));
            dots -= 2;
            middleDots += 4;
        }
        int outerDots = 1;
        dots = n - 2;
        middleDots = n;
        Console.WriteLine("*{0}*{1}*{0}*", new string('.', dots), new string('.', middleDots));

        dots -= 2;
        for (int i = 0; i < n / 2 - 1; i++)
        {
            Console.WriteLine("*{0}*{1}*{2}*{1}*{0}*", new string('.', dots), new string('.', outerDots), new string('.', middleDots));
            dots -= 2;
            outerDots += 2;
        }
        dots = n - 1;
        middleDots = n;

        for (int i = 0; i < n - 1; i++)
        {
            Console.WriteLine("{0}*{1}*{0}", new string('.', dots), new string('.', middleDots));
            dots -= 1;
            middleDots += 2;
        }
        stars = 3 * n;
        Console.WriteLine("{0}", new string('*', stars));
    }
}
