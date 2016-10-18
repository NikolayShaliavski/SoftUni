using System;

class SumOfNumbers
{
    static void Main()
    {
        int countOfNumbers = int.Parse(Console.ReadLine());
        double sum = 0;

        for (double i = 1; i <= countOfNumbers; i++)
        {
            double number = double.Parse(Console.ReadLine());
            sum += number;
        }

        Console.WriteLine(sum);
    }
}

