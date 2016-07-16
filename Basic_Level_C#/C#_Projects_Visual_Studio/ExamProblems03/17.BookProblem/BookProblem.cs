using System;

class BookProblem
{
    static void Main()
    {
        int pages = int.Parse(Console.ReadLine());
        int campingDays = int.Parse(Console.ReadLine());
        int pagesPerDay = int.Parse(Console.ReadLine());

        int normalDays = 30 - campingDays;
        int pagesPerMonth = normalDays * pagesPerDay;
        int allMonths = (int)Math.Ceiling((double)pages / pagesPerMonth);
        int years = allMonths / 12;
        int months = allMonths % 12;

        if (campingDays == 30 || pagesPerDay == 0)
        {
            Console.WriteLine("never");
        }
        else
        {
            Console.WriteLine("{0} years {1} months", years, months);
        }
    }
}
