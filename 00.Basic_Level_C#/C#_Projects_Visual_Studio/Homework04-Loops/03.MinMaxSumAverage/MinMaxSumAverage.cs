using System;

class MinMaxSumAverage
{
    static void Main()
    {
        int iterations = int.Parse(Console.ReadLine());

        int sum = 0;
        int min = int.MaxValue;
        int max = int.MinValue;

        for (int i = 0; i < iterations; i++)
        {
            int number = int.Parse(Console.ReadLine());
            sum += number;

            if (number < min)
            {
                min = number;
            }
            if (number > max)
            {
                max = number;
            }
        }
        double average = (double)sum / iterations;
        Console.WriteLine("min = " + min);
        Console.WriteLine("max = " + max);
        Console.WriteLine("sum = " + sum);
        Console.WriteLine("avg = {0:0.00}", average);
    }
}
