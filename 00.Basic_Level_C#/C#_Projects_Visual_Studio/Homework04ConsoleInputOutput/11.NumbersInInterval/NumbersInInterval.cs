using System;

class NumbersInInterval
{
    static void Main()
    {
        int startNumber = int.Parse(Console.ReadLine());
        int endNumber = int.Parse(Console.ReadLine());

        int count = 0;

        for (int i = startNumber; i <= endNumber; i++)
        {
            if (i % 5 == 0)
            {
               count++; 
            }
        }
        Console.WriteLine(count);
    }
}

