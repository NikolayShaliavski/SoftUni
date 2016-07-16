using System;
using System.Collections.Generic;

class RandomizeNumbers
{
    static void Main()
    {
        int input = int.Parse(Console.ReadLine());
        List<int> randomList = new List<int>();
        Random number = new Random();
        int value = number.Next(1, input + 1);
        randomList.Add(value);

        for (int i = 1; i < input; i++)
        {
            do
            {
                value = number.Next(1, input + 1);
            } while (randomList.Contains(value));
            randomList.Add(value);
        }
        foreach (int random in randomList)
        {
            Console.Write(random + " ");
        }
        Console.WriteLine();
    }
}