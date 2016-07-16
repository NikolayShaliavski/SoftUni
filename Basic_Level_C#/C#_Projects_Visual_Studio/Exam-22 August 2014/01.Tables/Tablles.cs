using System;

class Tablles
{
    static void Main()
    {
        long packs1 = long.Parse(Console.ReadLine());
        long packs2 = long.Parse(Console.ReadLine());
        long packs3 = long.Parse(Console.ReadLine());
        long packs4 = long.Parse(Console.ReadLine());

        int tops = int.Parse(Console.ReadLine());
        long neededTables = long.Parse(Console.ReadLine());

        long allLegs = packs1 + packs2 * 2 + packs3 * 3 + packs4 * 4;
        long tableMade = 0;

        if (allLegs / 4 >= tops)
        {
            tableMade = tops;
        }
        if (allLegs / 4 <= tops)
        {
            tableMade = allLegs / 4;
        }

        if (tableMade == neededTables)
        {
            Console.WriteLine("Just enough tables made: " + tableMade);
        }
        else if (tableMade > neededTables)
        {
            Console.WriteLine("more: {0}", (tableMade - neededTables));
            Console.WriteLine("tops left: {0}, legs left: {1}", (tops - neededTables), (allLegs - neededTables * 4));
        }
        else if (tableMade < neededTables)
        {
            long neededLegs = 0;
            long neededTops = 0;
            if (allLegs >= neededTables * 4)
            {
                neededLegs = 0;
            }
            if (allLegs < neededTables * 4)
            {
                neededLegs = neededTables * 4 - allLegs;
            }
            if (tops >= neededTables)
            {
                neededTops = 0;
            }
            if (tops < neededTables)
            {
                neededTops = neededTables - tops;
            }
            Console.WriteLine("less: {0}", (tableMade - neededTables));
            Console.WriteLine("tops needed: {0}, legs needed: {1}", neededTops, neededLegs);
        }
    }
}