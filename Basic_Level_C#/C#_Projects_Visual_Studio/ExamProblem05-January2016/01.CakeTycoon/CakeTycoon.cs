using System;

class CakeTycoon
{
    static void Main()
    {
        decimal cakes = decimal.Parse(Console.ReadLine());
        decimal flour = decimal.Parse(Console.ReadLine());
        decimal availableFlour = decimal.Parse(Console.ReadLine());
        decimal trufels = decimal.Parse(Console.ReadLine());
        decimal trufelPrice = decimal.Parse(Console.ReadLine());

        decimal cakesAvailable = availableFlour / flour;
        decimal cakePrice = ((trufels * trufelPrice) / cakes) * 1.25M;

        if (cakesAvailable >= cakes)
        {
            Console.WriteLine("All products available, price of a cake: {0:0.00}", cakePrice);
        }
        else if (cakesAvailable < cakes)
        {
            cakesAvailable = Math.Floor(cakesAvailable);
            decimal neededFlour = (cakes * flour) - availableFlour;
            Console.WriteLine("Can make only {0} cakes, need {1:0.00} kg more flour", cakesAvailable, neededFlour);
        }
    }
}
