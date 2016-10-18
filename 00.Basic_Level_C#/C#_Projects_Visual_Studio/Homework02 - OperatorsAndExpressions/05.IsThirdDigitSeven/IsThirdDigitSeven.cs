using System;

class IsThirdDigitSeven
{
    static void Main()
    {
        int inputnumber = int.Parse(Console.ReadLine());

        bool isSevenOrNot = ((inputnumber / 100) % 10 == 7);
        Console.WriteLine(isSevenOrNot);
    }
}

