using System;

class MasterHerbalist
{
    static void Main()
    {
        int expenses = int.Parse(Console.ReadLine());

        string input = Console.ReadLine();
        int seasonDays = 0;
        int hours = 0;
        int price = 0;
        int herbs = 0;
        int totalMoney = 0;
        double earningsPerDay = 0;
        double extraMoney = 0;
        int neededMoney = 0;

        while (input != "Season Over")
        {
            seasonDays++;
            string[] day = input.Split(' ');
            hours = int.Parse(day[0]);
            price = int.Parse(day[2]);
            char[] path = day[1].ToCharArray();

            for (int i = 0; i < path.Length; i++)
            {
                if (path[i] == 'H')
                {
                    herbs++;
                }
                if (i == path.Length - 1 && hours > i)
                {
                    hours -= (i + 1);
                    i = -1;
                }
                if (hours == i + 1)
                {
                    break;
                }
            }
            totalMoney += (herbs * price);
            herbs = 0;
            input = Console.ReadLine();
        }

        earningsPerDay = (double)totalMoney / seasonDays;

        if (earningsPerDay >= expenses)
        {
            extraMoney = earningsPerDay - expenses;
            Console.WriteLine("Times are good. Extra money per day: {0:0.00}.", extraMoney);
        }
        else
        {
            neededMoney = (expenses * seasonDays) - totalMoney;
            Console.WriteLine("We are in the red. Money needed: {0}.", neededMoney);
        }
    }
}
