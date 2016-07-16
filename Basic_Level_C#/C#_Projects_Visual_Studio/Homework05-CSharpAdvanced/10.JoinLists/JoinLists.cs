using System;
using System.Collections.Generic;
using System.Linq;

class JoinLists
{
    static void Main()
    {
        string[] first = Console.ReadLine().Split();
        string[] second = Console.ReadLine().Split();

        List<int> firstList = new List<int>();
        List<int> secondList = new List<int>();
        List<int> noRepeating = new List<int>();

        foreach (string number in first)
        {
            firstList.Add(int.Parse(number));
        }

        foreach (string number in second)
        {
            secondList.Add(int.Parse(number));
        }

        for (int i = 0; i < secondList.Count; i++)
        {
            if (!firstList.Contains(secondList[i]))
            {
                firstList.Add(secondList[i]);
            }
        }

        noRepeating = firstList.Distinct().ToList();
        noRepeating.Sort();
        string numbers = string.Join(" ", noRepeating.ToArray());

        Console.WriteLine(numbers);
    }
}
