using System;

class PrimeNumberCheck
{
    static void Main()
    {
        Console.Write("Enter a positive number: ");
        int inputNumber = int.Parse(Console.ReadLine());
        bool isPrime = true;
        for (int i = 2; i <= Math.Sqrt(inputNumber); i++)
        {
            if (inputNumber % i == 0 || inputNumber <= 0)
            {
                isPrime = false;
            }
        }
        Console.WriteLine("The number is prime: " + isPrime);
    }
}

