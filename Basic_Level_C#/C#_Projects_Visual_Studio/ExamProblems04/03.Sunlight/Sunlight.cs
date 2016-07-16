using System;

class Sunlight
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int dotCounter = n + (n / 2);
        Console.WriteLine("{0}*{0}", new string('.', dotCounter));

        int outerDot = 1;
        int innerDot = dotCounter - 2;

        for (int i = 0; i < n - 1; i++)
        {
            Console.WriteLine("{0}*{1}*{1}*{0}", new string('.', outerDot), new string('.', innerDot));
            outerDot++;
            innerDot--;
        }

        outerDot = n;
        int asterics = n;
        for (int i = 0; i < n / 2; i++)
        {
            Console.WriteLine("{0}{1}{0}", new string('.', outerDot), new string('*', asterics));
        }

        Console.WriteLine(new string('*', n * 3));

        for (int i = 0; i < n / 2; i++)
        {
            Console.WriteLine("{0}{1}{0}", new string('.', outerDot), new string('*', asterics));
        }

        outerDot = n - 1;
        innerDot = n / 2;
        for (int i = 0; i < n - 1; i++)
        {
            Console.WriteLine("{0}*{1}*{1}*{0}", new string('.', outerDot), new string('.', innerDot));
            outerDot--;
            innerDot++;
        }
        Console.WriteLine("{0}*{0}", new string('.', dotCounter));
    }
}
