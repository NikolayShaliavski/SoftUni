using System;
using System.Collections.Generic;
using System.Linq;

class StudentCables
{
    static void Main()
    {
        int numberOfCables = int.Parse(Console.ReadLine());
        List<int> cables = new List<int>();

        for (int i = 0; i < numberOfCables; i++)
        {
            int length = int.Parse(Console.ReadLine());
            string measure = Console.ReadLine();

            if (measure == "meters")
            {
                length *= 100;
            }
            if (length < 20)
            {
                continue;
            }           
            else
            {
                cables.Add(length);
            }
        }

        int joinedCable = cables.Sum() - (3 * (cables.Count - 1));
        int newCable = joinedCable / 504;
        int reminder = joinedCable % 504;

        Console.WriteLine(newCable);
        Console.WriteLine(reminder);
    }
}
