using System;

class GreatestCommonDiviser
{
    static void Main()
    {
        int first = int.Parse(Console.ReadLine());
        int second = int.Parse(Console.ReadLine());

        if (first == 0)
        {
            Console.WriteLine(second); return;
        }
        else
        {
            Console.WriteLine(first); return;
        }

        int number = Math.Max(first, second);
        int divisor = Math.Min(first, second);
        int reminder = 0;

        while (number % divisor != 0)
        {
            reminder = number % divisor;
            number = divisor;
            divisor = reminder;
        }
        Console.WriteLine(divisor);
    }
}
