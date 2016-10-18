using System;
using System.Numerics;

class Problem4
{
    static void Main()
    {
        decimal money = decimal.Parse(Console.ReadLine());

        string input = Console.ReadLine();

        while (input != "mall.Enter")
        {
            input = Console.ReadLine();
        }

        input = Console.ReadLine();
        decimal price = 0;
        int purchases = 0;

        while (input != "mall.Exit")
        {
            for (int i = 0; i < input.Length; i++)
            {
                price = input[i];

                if (price >= 65 && price <= 90 && (price * 0.5M) <= money)
                {
                    money -= (price * 0.5M);
                    purchases++;
                }
                else if (price >= 97 && price <= 122 && (price * 0.3M) <= money)
                {
                    money -= (price * 0.3M);
                    purchases++;
                }
                else if (price == '%' && money > 0)
                {
                    money /= 2;
                    purchases++;
                }
                else if (price == '*')
                {                  
                        money += 10;
                }
                else if (price <= money)
                {
                    money -= price;
                    purchases++;
                }
            }

            input = Console.ReadLine();
        }

        if (purchases == 0)
        {
            Console.WriteLine("No purchases. Money left: {0:0.00} lv.", money);
        }
        else
        {
            Console.WriteLine("{0} purchases. Money left: {1:0.00} lv.", purchases, money);
        }
    }
}
