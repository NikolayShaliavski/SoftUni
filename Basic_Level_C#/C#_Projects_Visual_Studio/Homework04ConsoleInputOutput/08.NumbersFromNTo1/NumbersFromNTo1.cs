using System;

class NumbersFromNTo1
{
    static void Main()
    {
        int userNumber = int.Parse(Console.ReadLine());

        for (int i = 1; i <= userNumber; i++)
        {
            Console.WriteLine(i);
        }
    }
}

