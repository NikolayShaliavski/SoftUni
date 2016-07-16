using System;

class MagicWand
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int width = (3 * n) + 2;
        string asteriks = new string('*', 1);
        string outerDots = new string('.', width / 2);
        string middleDots = new string('.', 0);
        Console.WriteLine("{0}{1}{0}", outerDots, asteriks);

        int height = n - (n / 2);
        int j = 1;
        for (int i = 0; i < height; i++, j += 2)
        {
            outerDots = new string('.', n + (height - 1 - i));
            middleDots = new string('.', j);
            Console.WriteLine("{0}*{1}*{0}", outerDots, middleDots);
        }
        asteriks = new string('*', n);
        outerDots = new string('.', n + 2);
        Console.WriteLine("{0}{1}{0}", asteriks, outerDots);

        for (int i = 0; i < height - 1; i++)
        {
            outerDots = new string('.', i + 1);
            middleDots = new string('.', width - ((i + 2) * 2));
            Console.WriteLine("{0}*{1}*{0}", outerDots, middleDots);
        }

        j = (n - (n / 2 + 2));
        for (int i = 0; i < height - 1; i++, j--)
        {
            outerDots = new string('.', j);
            string middleDots2 = new string('.', (n - (n / 2 + 1)));
            string outerDots2 = new string('.', i);
            middleDots = new string('.', n);
            Console.WriteLine("{0}*{1}*{2}*{3}*{2}*{1}*{0}", outerDots, middleDots2, outerDots2, middleDots);
        }

        asteriks = new string('*', n - (n / 2));
        outerDots = new string('.', n - (n / 2 + 1));
        middleDots = new string('.', n);
        Console.WriteLine("{0}{1}*{2}*{1}{0}", asteriks, outerDots, middleDots);

        for (int i = 0; i < n; i++)
        {
            outerDots = new string('.', n);
            middleDots = new string('.', n);
            Console.WriteLine("{0}*{1}*{0}", outerDots, middleDots);
        }
        outerDots = new string('.', n);
        asteriks = new string('*', n + 2);
        Console.WriteLine("{0}{1}{0}", outerDots, asteriks);
    }
}

