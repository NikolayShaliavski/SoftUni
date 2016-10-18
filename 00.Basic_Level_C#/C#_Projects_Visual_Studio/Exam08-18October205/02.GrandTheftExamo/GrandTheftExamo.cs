using System;

class GrandTheftExamo
{
    static void Main()
    {
        int attempts = int.Parse(Console.ReadLine());
        int slapped = 0;
        long escaped = 0;
        long allBeers = 0;

        for (int i = 0; i < attempts; i++)
        {
            int thieves = int.Parse(Console.ReadLine());
            int beers = int.Parse(Console.ReadLine());

            if (thieves >= 5)
            {
                slapped += 5;
                escaped += (thieves - 5);
            }
            else
            {
                slapped += thieves;
                escaped += 0;
            }
            allBeers += beers;
        }

        Console.WriteLine("{0} thieves slapped.", slapped);
        Console.WriteLine("{0} thieves escaped.", escaped);

        long packs = (long)allBeers / 6;
        long bottles = (long)allBeers % 6;

        Console.WriteLine("{0} packs, {1} bottles.", packs, bottles);
    }
}
