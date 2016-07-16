using System;

class DivideBySevenAndFive
{
    static void Main()
    {
        int inputnumber = int.Parse(Console.ReadLine());

        bool isDivided = (inputnumber % 35 == 0);
        Console.WriteLine(isDivided);
    }
}

