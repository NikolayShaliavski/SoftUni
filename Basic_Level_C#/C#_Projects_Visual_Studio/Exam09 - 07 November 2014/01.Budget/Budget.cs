using System;

class Budget
{
    static void Main()
    {
        int salary = int.Parse(Console.ReadLine());
        int partyDays = int.Parse(Console.ReadLine());
        int home = int.Parse(Console.ReadLine());

        int weekend = 8 - (home * 2);
        int party = (int)Math.Floor(salary * 0.03);
        int partyExpenses = party * partyDays;
        int weekendExpenses = weekend * 20;

        int expenses = 220 + 150 + partyExpenses + weekendExpenses;

        if (expenses == salary)
        {
            Console.WriteLine("Exact Budget.");
        }
        else if (expenses > salary)
        {
            Console.WriteLine("No, not enough {0}.", expenses - salary);
        }
        else if (expenses < salary)
        {
            Console.WriteLine("Yes, leftover {0}.", salary - expenses);
        }
    }
}
