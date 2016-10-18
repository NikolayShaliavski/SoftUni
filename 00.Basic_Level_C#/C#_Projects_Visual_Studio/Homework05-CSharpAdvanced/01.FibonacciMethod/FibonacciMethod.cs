using System;

class FibonacciMethod
{
    static int Fibonacci(int number)
    {
        int firstNumber = 0;
        int secondNumber = 1;
        int fibNumber = 0;

        for (int i = 0; i < number; i++)
        {
            fibNumber = (firstNumber + secondNumber);

            firstNumber = secondNumber;
            secondNumber = fibNumber;
        }
        return fibNumber;
    }
    static void Main()
    {
        int input = int.Parse(Console.ReadLine());
        int result = Fibonacci(input);

        Console.WriteLine(result);
    }
}
