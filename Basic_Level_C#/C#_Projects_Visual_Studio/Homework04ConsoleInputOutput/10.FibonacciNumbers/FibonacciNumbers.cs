using System;

class FibonacciNumbers
{
    static void Main()
    {
        int fibonacciLenght = int.Parse(Console.ReadLine());
        int firstNumber = 0;
        int secondNumber = 1;

        Console.Write("{0} " + "{1} ", firstNumber, secondNumber);

        for (int i = 2; i < fibonacciLenght; i++)
        {

            int thirdNumber = (firstNumber + secondNumber);
            Console.Write(thirdNumber + " ");

            firstNumber = secondNumber;
            secondNumber = thirdNumber;
        }
    }
}


