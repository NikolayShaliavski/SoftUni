using System;

class PiggyBank
{
    static void Main()
    {
        int price = int.Parse(Console.ReadLine());
        int parti = int.Parse(Console.ReadLine());

        int normalDays = 30 - parti;
        int spent = parti * 5;
        int saved = normalDays * 2;

        if (spent >= saved)
        {
            Console.WriteLine("never");
        }
        else
        {
            int savedPerMonth = saved - spent;
            double allMonths = (double)price / savedPerMonth;
            int convertedMonths = (int)Math.Ceiling(allMonths);

            int years = convertedMonths / 12;
            int months = convertedMonths % 12;

            Console.WriteLine("{0} years, {1} months", years, months);

        }
    }
}
