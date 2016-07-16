using System;

class IlluminatiLock
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        string sharp = new string('#', n);
        string dot = new string('.', n);
        string middleDot = new string('.', n - 2);

        Console.WriteLine("{0}{1}{0}", dot, sharp);
        int outerDot = n - 2;
        int sharpCount = 3;
        Console.WriteLine("{0}{1}{2}{1}{0}", new string('.', outerDot), new string('#', sharpCount), middleDot);

        int dotsCount = 2;
        outerDot -= 2;
        for (int i = 0; i < n / 2 - 1; i++)
        {
            Console.WriteLine("{0}##{1}#{2}#{1}##{0}", new string('.', outerDot), new string('.', dotsCount), middleDot);
            outerDot -= 2;
            dotsCount += 2;
        }

        outerDot = 1;
        dotsCount = n - 3;
        for (int i = 0; i < n / 2 - 1; i++)
        {
            Console.WriteLine("{0}##{1}#{2}#{1}##{0}", new string('.', outerDot), new string('.', dotsCount), middleDot);
            outerDot += 2;
            dotsCount -= 2;
        }

        Console.WriteLine("{0}{1}{2}{1}{0}", new string('.', outerDot), new string('#', sharpCount), middleDot);


        Console.WriteLine("{0}{1}{0}", dot, sharp);

    }
}
