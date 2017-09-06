using System;
using System.Collections.Generic;
using System.Linq;

class SortWords
{
    static int steps;

    static void Main(string[] args)
    {
        List<string> words = Console.ReadLine().Split().ToList();
        steps = 0;
        QuickSort(words, 0, words.Count() - 1);

        Console.WriteLine(string.Join(" ", words));
        Console.WriteLine(steps);
    }

    private static void QuickSort(List<string> collection, int start, int end)
    {
        if (start >= end)
        {
            return;
        }

        string pivot = collection[start];
        int storeIndex = start + 1;

        for (int i = storeIndex; i <= end; i++)
        {
            steps++;
            if (collection[i].CompareTo(pivot) <= 0)
            {
                Swap(collection, i, storeIndex);
                storeIndex++;
            }
        }
        storeIndex--;
        Swap(collection, start, storeIndex);

        QuickSort(collection, start, storeIndex - 1);
        QuickSort(collection, storeIndex + 1, end);
    }

    private static void Swap(List<string> collection, int firstIndex, int secondIndex)
    {
        string temporaryElement = collection[firstIndex];
        collection[firstIndex] = collection[secondIndex];
        collection[secondIndex] = temporaryElement;

    }
}

