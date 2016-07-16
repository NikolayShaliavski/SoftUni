using System;

class SumOfFiveNumbers
{
    static void Main()
    {
        string[] userINput = Console.ReadLine().Split();
        double[] numbers = new double[5];
        double sum = 0;

        for (int i = 0; i < userINput.Length; i++)
        {
            numbers[i] = Convert.ToDouble(userINput[i]);
            sum += numbers[i];
        }
        Console.WriteLine("{0:0.##}", sum);
    }

}

