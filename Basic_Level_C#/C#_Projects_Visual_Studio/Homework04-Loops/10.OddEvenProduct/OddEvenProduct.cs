using System;
using System.Collections.Generic;

class OddEvenProduct
{
    static void Main()
    {
        int input = int.Parse(Console.ReadLine());
        List<int> listOfNumbers = new List<int>();

        for (int i = 0; i < input; i++)
        {
            int number = int.Parse(Console.ReadLine());
            listOfNumbers.Add(number);
        }

        int productOdd = 1;
        int productEven = 1;

        for (int i = 0; i < listOfNumbers.Count; i++)
        {
            if (i % 2 == 0)
            {
                productOdd *= listOfNumbers[i];
            }
            else if (i % 2 != 0)
            {
                productEven *= listOfNumbers[i];
            }
        }

        if (productOdd == productEven)
        {
            Console.WriteLine("yes");
            Console.WriteLine("product = " + productOdd);
        }
        else
        {
            Console.WriteLine("no");
            Console.WriteLine("odd_product = " + productOdd);
            Console.WriteLine("even_product = " + productEven);
        }
    }
}
