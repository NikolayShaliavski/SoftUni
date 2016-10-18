using System;
using System.Collections.Generic;

class FireFighters
{
    static void Main()
    {
        int fighters = int.Parse(Console.ReadLine());        
        int fireFighters = fighters;
        int kidsSaved = 0;
        int adultsSaved = 0;
        int seniorsSaved = 0;

        if (fighters == 0)
        {
            Console.WriteLine("Kids: " + kidsSaved);
            Console.WriteLine("Adults: " + adultsSaved);
            Console.WriteLine("Seniors: " + seniorsSaved);
            return;
        }

        List<char> kids = new List<char>();
        List<char> adults = new List<char>();
        List<char> seniors = new List<char>();
        List<char> people = new List<char>();


        string building = Console.ReadLine();

        while (building != "rain")
        {
            for (int i = 0; i < building.Length; i++)
            {
                if (building[i] == 'K')
                {
                    kids.Add(building[i]);
                }
                else if (building[i] == 'A')
                {
                    adults.Add(building[i]);
                }
                else if (building[i] == 'S')
                {
                    seniors.Add(building[i]);
                }
            }
            people.AddRange(kids);
            kids.Clear();
            people.AddRange(adults);
            adults.Clear();
            people.AddRange(seniors);
            seniors.Clear();

            for (int i = 0; i < people.Count; i++)
            {
                if (people[i] == 'K')
                {
                    kidsSaved++;
                    fireFighters--;
                }
                else if (people[i] == 'A')
                {
                    adultsSaved++;
                    fireFighters--;
                }
                else if (people[i] == 'S')
                {
                    seniorsSaved++;
                    fireFighters--;
                }
                if (fireFighters == 0)
                {
                    break;
                }
            }
            fireFighters = fighters;
            people.Clear();

            building = Console.ReadLine();
        }
        Console.WriteLine("Kids: " + kidsSaved);
        Console.WriteLine("Adults: " + adultsSaved);
        Console.WriteLine("Seniors: " + seniorsSaved);
    }
}
