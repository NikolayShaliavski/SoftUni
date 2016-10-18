using System;

class ShufflingDigits
{
    static void Main()
    {
        int inputNumber = int.Parse(Console.ReadLine());

        int fourthDigit = inputNumber % 10;
        int thirdDigit = (inputNumber / 10) % 10;
        int secondDigit = (inputNumber / 100) % 10;
        int firstDigit = (inputNumber / 1000) % 10;

        Console.WriteLine("The sum of digits is {0}.", (firstDigit + secondDigit + thirdDigit + fourthDigit));
        Console.WriteLine("Reversed digits of number are {3}{2}{1}{0}.", firstDigit, secondDigit, thirdDigit, fourthDigit);
        Console.WriteLine("Last digit in front: {3}{0}{1}{2}", firstDigit, secondDigit, thirdDigit, fourthDigit);
        Console.WriteLine("Changing second and third digit: {0}{2}{1}{3}", firstDigit, secondDigit, thirdDigit, fourthDigit);
    }
}

