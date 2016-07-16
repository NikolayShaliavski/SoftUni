using System;

class OddOrEvenIntegers
{
    static void Main()
    {
        int inputNumber = int.Parse(Console.ReadLine());

        bool oddOrEven = (inputNumber % 2 == 0);
        Console.WriteLine(oddOrEven);
    }
}

