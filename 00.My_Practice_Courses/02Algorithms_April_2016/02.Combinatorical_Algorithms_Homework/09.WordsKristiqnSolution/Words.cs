using System;
using System.Collections.Generic;
using System.Linq;

class Words
{
    private static List<char[]> permutations = new List<char[]>();

    public static void Main(string[] args)
    {
        string str = Console.ReadLine();
        char[] chars = str.ToArray();
        Array.Sort(chars);

        permuteRep(chars, 0, str.Length - 1);

        int count = 0;
        for (int i = 0; i < permutations.Count; i++)
        {
            bool correctSequence = true;
            for (int j = 0; j < permutations[i].Length - 1; j++)
            {
                if (permutations[i][j] == permutations[i][j + 1])
                {
                    correctSequence = false;
                    break;
                }
            }

            if (correctSequence)
            {
                count++;
            }
        }

        Console.WriteLine(count);
    }

    private static void permuteRep(char[] arr, int start, int end)
    {
        char[] currentArr = new char[arr.Length];
        for (int i = 0; i < currentArr.Length; i++)
        {
            currentArr[i] = arr[i];
        }
        permutations.Add(currentArr);

        for (int left = end; left >= start; left--)
        {
            for (int right = left + 1; right <= end; right++)
            {
                if (arr[left] != arr[right])
                {
                    Swap(arr, right, left);
                    permuteRep(arr, left + 1, end);
                }
            }

            // Undo all modifications done by the
            // previous recursive calls and swaps
            char firstElement = arr[left];
            for (int i = left; i <= end - 1; i++)
            {
                arr[i] = arr[i + 1];
            }
            arr[end] = firstElement;
        }
    }

    private static void Swap(char[] arr, int firstIndex, int secondIndex)
    {
        char swapValue = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = swapValue;
    }
}

