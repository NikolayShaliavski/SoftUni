using System;

class Car
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        char back = '.';
        char body = '*';

        Console.WriteLine("{0}{1}{0}", new string(back, n), new string(body, n));
        int outer = n - 1;
        int middle = n;

        for (int i = 0; i < n / 2 - 1; i++)
        {
            Console.WriteLine("{0}*{1}*{0}", new string(back, outer), new string(back, middle));
            outer--;
            middle += 2;
        }

        Console.WriteLine("{0}{1}{0}", new string(body, outer + 1), new string(back, middle));

        for (int i = 0; i < n / 2 - 2; i++)
        {
            Console.WriteLine("*{0}*", new string(back, 3 * n - 2));
        }

        Console.WriteLine("{0}", new string(body, 3 * n));
        outer = n / 2;
        middle = n - 2;
        int inner = n / 2 - 1;

        for (int i = 0; i < n / 2 - 2; i++)
        {
            Console.WriteLine("{0}*{1}*{2}*{1}*{0}", new string(back, outer), new string(back, inner), new string(back, middle));
        }
        inner += 2;
        Console.WriteLine("{0}{1}{2}{1}{0}", new string(back, outer), new string(body, inner), new string(back, middle));
    }
}
