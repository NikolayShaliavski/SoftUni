using System;

class Boat
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int leftdot = n - 1;
        int rightDot = n;
        int stars = 1;

        for (int i = 0; i < n / 2 + 1; i++)
        {
            Console.WriteLine("{0}{1}{2}", new string('.', leftdot), new string('*', stars), new string('.', rightDot));
            leftdot -= 2;
            stars += 2;
        }
        leftdot = 2;
        stars = n - 2;

        for (int i = 0; i < n / 2; i++)
        {           
            Console.WriteLine("{0}{1}{2}", new string('.', leftdot), new string('*', stars), new string('.', rightDot));
            leftdot += 2;
            stars -= 2;
        }
        stars = n * 2;
        leftdot = 0;

        for (int i = 0; i < (n - 1) / 2; i++)
        {
            Console.WriteLine("{0}{1}{0}", new string('.', leftdot), new string('*', stars));
            leftdot++;
            stars -= 2;
        }
    }
}
