using System;

class DifferenceBetweenDates
{
    static void Main()
    {
        DateTime firstDate = DateTime.Parse(Console.ReadLine());
        DateTime secondDate = DateTime.Parse(Console.ReadLine());

        int difference = (int)(secondDate - firstDate).TotalDays;
        Console.WriteLine(difference);
    }
}
