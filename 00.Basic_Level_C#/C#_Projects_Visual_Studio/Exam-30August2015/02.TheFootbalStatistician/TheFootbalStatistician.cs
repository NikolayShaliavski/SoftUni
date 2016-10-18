using System;
using System.Collections.Generic;
using System.Text.RegularExpressions;

class TheFootbalStatistician
{
    static void Main()
    {
        Dictionary<string, int> table = new Dictionary<string, int>()
        {
            {"Arsenal", 0 },
            {"Chelsea", 0 },
            {"Everton", 0 },
            {"Liverpool", 0 },
            {"Manchester City", 0 },
            {"Manchester United", 0 },
            {"Southampton", 0 },
            {"Tottenham", 0 }
        };

        decimal price = decimal.Parse(Console.ReadLine()) * 1.94M;
        string input = Console.ReadLine();
        decimal amount = 0;

        while (input != "End of the league.")
        {
            amount += price;
            char[] separator = new char[] { ' ' };

            string[] match = input.Split(separator, StringSplitOptions.RemoveEmptyEntries);

            for (int i = 0; i < match.Length; i++)
            {
                if (match[i] == "ManchesterCity" || match[i] == "ManchesterUnited")
                {
                    match[i] = Regex.Replace(match[i], "(\\B[A-Z])", " $1");
                }
            }

            string team1 = match[0];
            string team2 = match[2];
            string result = match[1];

            switch (result)
            {
                case "1":
                    table[team1] += 3;
                    break;
                case "X":
                    table[team1] += 1;
                    table[team2] += 1;
                    break;
                case "2":
                    table[team2] += 3;
                    break;
                default:
                    break;
            }

            input = Console.ReadLine();
        }

        Console.WriteLine("{0:0.00}lv.", amount);

        foreach (KeyValuePair<string, int> points in table)
        {
            Console.WriteLine("{0} - {1} points.", points.Key, points.Value);
        }
    }
}
