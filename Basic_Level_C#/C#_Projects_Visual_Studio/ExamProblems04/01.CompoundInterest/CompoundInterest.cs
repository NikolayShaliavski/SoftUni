using System;

class CompoundInterest
{
    static void Main()
    {
        double priceTV = double.Parse(Console.ReadLine());
        double term = double.Parse(Console.ReadLine());
        double bankInterest = double.Parse(Console.ReadLine());
        double friendInterest = double.Parse(Console.ReadLine());

        double interestSum = 1 + bankInterest;
        double bankLoan = priceTV * Math.Pow(interestSum, term);

        double friendLoan = priceTV * (1 + friendInterest);
        if (friendLoan <= bankLoan)
        {
            Console.WriteLine("{0:F2} Friend", friendLoan);
        }
        else
        {
            Console.WriteLine("{0:F2} Bank", bankLoan);
        }
    }
}

