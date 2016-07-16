using System;

class Problem1
{
    static void Main()
    {
        decimal allMoney = decimal.Parse(Console.ReadLine());
        decimal exchange = decimal.Parse(Console.ReadLine());

        decimal pizzaPrice = decimal.Parse(Console.ReadLine()) / exchange;
        decimal lasagnaPrice = decimal.Parse(Console.ReadLine()) / exchange;
        decimal sandwichPrice = decimal.Parse(Console.ReadLine()) / exchange;

        long pizzaCount = long.Parse(Console.ReadLine());
        long lasagnaCount = long.Parse(Console.ReadLine());
        long sandwichCount = long.Parse(Console.ReadLine());

        decimal moneySpent = (pizzaPrice * pizzaCount) + (lasagnaPrice * lasagnaCount) + (sandwichPrice * sandwichCount);

        if (allMoney >= moneySpent)
        {
            Console.WriteLine("Garfield is well fed, John is awesome. Money left: ${0:0.00}.", (allMoney - moneySpent));
        }
        else
        {
            Console.WriteLine("Garfield is hungry. John is a badass. Money needed: ${0:0.00}.", moneySpent - allMoney);
        }
    }
}
