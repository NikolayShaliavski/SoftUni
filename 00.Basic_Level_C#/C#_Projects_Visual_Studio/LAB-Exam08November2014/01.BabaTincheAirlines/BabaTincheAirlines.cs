using System;

class BabaTincheAirlines
{
    static void Main()
    {
        string firstClass = Console.ReadLine();
        string businessClass = Console.ReadLine();
        string economyClass = Console.ReadLine();

        string[] first = firstClass.Split();
        string[] business = businessClass.Split();
        string[] economy = economyClass.Split();

        int passFirst = int.Parse(first[0]);
        int frequentFirst = int.Parse(first[1]);
        int mealFirst = int.Parse(first[2]);

        int passBusiness = int.Parse(business[0]);
        int frequentBusiness = int.Parse(business[1]);
        int mealBusiness = int.Parse(business[2]);

        int passEconomy = int.Parse(economy[0]);
        int frequentEconomy = int.Parse(economy[1]);
        int mealEconomy = int.Parse(economy[2]);

        double maxPriceFirst = (7000 * 0.005) + 7000;
        double maxIncomeFirst = maxPriceFirst * 12;

        double maxPriceBusiness = (3500 * 0.005) + 3500;
        double maxIncomeBusiness = maxPriceBusiness * 28;

        double maxPriceEconomy = (1000 * 0.005) + 1000;
        double maxIncomeEconomy = maxPriceEconomy * 50;

        double maxIncome = maxIncomeFirst + maxIncomeBusiness + maxIncomeEconomy;

        int priceFirst = (passFirst - (frequentFirst + mealFirst)) * 7000;
        double freqPriceFirst = 7000 - (7000 * 0.7);
        double mealPriceFirst = (7000 * 0.005) + 7000;

        double incomeFirst = priceFirst + (freqPriceFirst * frequentFirst) + (mealPriceFirst * mealFirst);

        int priceBusiness = (passBusiness - (frequentBusiness + mealBusiness)) * 3500;
        double freqPriceBusiness = 3500 - (3500 * 0.7);
        double mealPriceBusiness = (3500 * 0.005) + 3500;

        double incomeBusiness = priceBusiness + (freqPriceBusiness * frequentBusiness) + (mealPriceBusiness * mealBusiness);

        int priceEconomy = (passEconomy - (frequentEconomy + mealEconomy)) * 1000;
        double freqPriceEconomy = 1000 - (1000 * 0.7);
        double mealPriceEconomy = (1000 * 0.005) + 1000;

        double incomeEconomy = priceEconomy + (freqPriceEconomy * frequentEconomy) + (mealPriceEconomy * mealEconomy);

        Console.WriteLine((int)(incomeFirst + incomeBusiness + incomeEconomy));
        Console.WriteLine(Math.Ceiling(maxIncome - (incomeFirst + incomeBusiness + incomeEconomy)));
    }
}
