using System;

class PlaidTowel
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        char back = char.Parse(Console.ReadLine());
        char romb = char.Parse(Console.ReadLine());

        Console.WriteLine("{0}{1}{2}{1}{0}",
                        new string(back, n),
                        new string(romb, 1),
                        new string(back, (n * 2) - 1));

        int outDots = n - 1;
        int middleDots = (n * 2) - 3;
        int inDots = 1;

        for (int i = 0; i < n - 1; i++)
        {
            Console.WriteLine("{0}{1}{2}{1}{3}{1}{2}{1}{0}", 
                        new string(back, outDots),
                        new string(romb, 1),
                        new string(back, inDots),
                        new string(back, middleDots));
            outDots--;
            inDots += 2;
            middleDots -= 2;
        }

        Console.WriteLine("{0}{1}{0}{1}{0}", new string(romb, 1), new string(back, (n * 2) - 1));

        outDots = 1;
        inDots = (n * 2) - 3;
        middleDots = 1;

        for (int i = 0; i < n - 1; i++)
        {
            Console.WriteLine("{0}{1}{2}{1}{3}{1}{2}{1}{0}",
                        new string(back, outDots),
                        new string(romb, 1),
                        new string(back, inDots),
                        new string(back, middleDots));
            outDots++;
            inDots -= 2;
            middleDots += 2;
        }

        Console.WriteLine("{0}{1}{2}{1}{0}",
                        new string(back, n),
                        new string(romb, 1),
                        new string(back, (n * 2) - 1));

        outDots = n - 1;
        middleDots = (n * 2) - 3;
        inDots = 1;

        for (int i = 0; i < n - 1; i++)
        {
            Console.WriteLine("{0}{1}{2}{1}{3}{1}{2}{1}{0}",
                        new string(back, outDots),
                        new string(romb, 1),
                        new string(back, inDots),
                        new string(back, middleDots));
            outDots--;
            inDots += 2;
            middleDots -= 2;
        }

        Console.WriteLine("{0}{1}{0}{1}{0}", new string(romb, 1), new string(back, (n * 2) - 1));

        outDots = 1;
        inDots = (n * 2) - 3;
        middleDots = 1;

        for (int i = 0; i < n - 1; i++)
        {
            Console.WriteLine("{0}{1}{2}{1}{3}{1}{2}{1}{0}",
                        new string(back, outDots),
                        new string(romb, 1),
                        new string(back, inDots),
                        new string(back, middleDots));
            outDots++;
            inDots -= 2;
            middleDots += 2;
        }

        Console.WriteLine("{0}{1}{2}{1}{0}",
                        new string(back, n),
                        new string(romb, 1),
                        new string(back, (n * 2) - 1));
    }
}
