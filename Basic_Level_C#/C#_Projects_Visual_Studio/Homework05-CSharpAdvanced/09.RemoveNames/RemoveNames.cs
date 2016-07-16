using System;
using System.Collections.Generic;

class ProgrRemoveNamesam
{
    static void Main()
    {
        string[] namesFirst = Console.ReadLine().Split();
        string[] namesSecond = Console.ReadLine().Split();

        List<string> firstList = new List<string>();
        List<string> secondList = new List<string>();

        foreach (string name in namesFirst)
        {
            firstList.Add(name);
        }

        foreach (string name in namesSecond)
        {
            secondList.Add(name);
        }

        for (int i = 0; i < firstList.Count; i++)
        {
            if (secondList.Contains(firstList[i]))
            {
                firstList[i] = "nameToRemove";
            }
        }
        firstList.RemoveAll(name => name == "nameToRemove"); 
        string names = string.Join(" ", firstList.ToArray());

        Console.WriteLine(names);
    }
}
