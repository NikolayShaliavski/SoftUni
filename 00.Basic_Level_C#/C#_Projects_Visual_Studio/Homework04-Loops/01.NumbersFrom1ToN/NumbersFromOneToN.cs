using System;

class NumbersFromOneToN
{
    static void Main()
    {
        int inputNumber = int.Parse(Console.ReadLine());

        for (int i = 1; i < inputNumber; i++)
        {
            Console.Write(i + " ");
        }
        Console.WriteLine();
    }
}