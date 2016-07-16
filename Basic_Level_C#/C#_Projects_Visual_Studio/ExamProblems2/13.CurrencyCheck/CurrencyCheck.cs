using System;

class CurrencyCheck
{
    static void Main()
    {
        double gameInRubles = double.Parse(Console.ReadLine());
        double gameInDolllars = double.Parse(Console.ReadLine());
        double gameInEuros = double.Parse(Console.ReadLine());
        double gameInLevaHalfPrice = double.Parse(Console.ReadLine());
        double gameInLeva = double.Parse(Console.ReadLine());

        double rublesInLeva = ((gameInRubles/100) * 3.5);
        double dollarsInLeva = gameInDolllars * 1.5;
        double eurosInLeva = gameInEuros * 1.95;
        double gameHalfPrice = gameInLevaHalfPrice / 2;

        double[] pricesArray = new double[]
                {
                    rublesInLeva, dollarsInLeva, eurosInLeva,
                    gameHalfPrice, gameInLeva
                };
        Array.Sort(pricesArray);
        Console.WriteLine("{0:F2}", pricesArray[0]);
    }
}

