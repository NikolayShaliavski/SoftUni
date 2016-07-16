using System;

class BeerStock
{
    static void Main()
    {
        long reservedBeers = long.Parse(Console.ReadLine());
        string shipment = Console.ReadLine();
        long amount = 0;
        long allBeers = 0;

        while (shipment != "Exam Over")
        {
            string[] input = shipment.Split(' ');

            amount = int.Parse(input[0]);

            switch (input[1])
            {
                case "cases":
                    allBeers += amount * 24;
                    break;
                case "sixpacks":
                    allBeers += amount * 6;
                    break;
                case "beers":
                    allBeers += amount;
                    break;
                default:
                    break;
            }
            shipment = Console.ReadLine();
        }
        long cases = 0;
        long sixpacks = 0;
        long beers = 0;
        long leftBeers = 0;
        long neededBeers = 0;

        if (allBeers >= 100)
        {
            allBeers -= (allBeers / 100);
        }

        if (allBeers >= reservedBeers)
        {
            allBeers -= reservedBeers;
            cases = allBeers / 24;
            leftBeers = allBeers % 24;
            sixpacks = leftBeers / 6;
            beers = leftBeers % 6;

            Console.WriteLine("Cheers! Beer left: {0} cases, {1} sixpacks and {2} beers.", cases, sixpacks, beers);
        }
        else if (allBeers < reservedBeers)
        {
            reservedBeers -= allBeers;
            cases = reservedBeers / 24;
            neededBeers = reservedBeers % 24;
            sixpacks = neededBeers / 6;
            beers = neededBeers % 6;

            Console.WriteLine("Not enough beer. Beer needed: {0} cases, {1} sixpacks and {2} beers.", cases, sixpacks, beers);
        }
    }
}
