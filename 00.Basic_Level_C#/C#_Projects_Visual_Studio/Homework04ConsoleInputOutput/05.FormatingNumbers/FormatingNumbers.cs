using System;

class FormatingNumbers
{
    static void Main()
    {
        int firstNumber = int.Parse(Console.ReadLine());
        double secondNumber = double.Parse(Console.ReadLine());
        double thirdNumber = double.Parse(Console.ReadLine());
        string binary = Convert.ToString(firstNumber, 2).PadLeft(10, '0');

        Console.WriteLine("|{0, -10:X}|{3}|{1, 10:0.##}|{2, -10:F3}|", firstNumber, secondNumber, thirdNumber, binary);
    }
}
