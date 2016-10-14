using System;
using System.Collections.Generic;
using System.Linq;

class Words
{
    //doesn't pass some tests in judge -> for memory && for time
    static int permutationsCounter = 0;
    static char[] letters;
    static ISet<string> words = new HashSet<string>();

    static void Main(string[] args)
    {
        letters = Console.ReadLine().ToArray();

        Permute();
        Console.WriteLine(permutationsCounter);
    }

    private static void Permute(int startIndex = 0)
    {
        if (startIndex == letters.Length - 1)
        {
            string currWord = new string(letters);
            if (IsPossible() && !words.Contains(currWord))
            {
                permutationsCounter++;
                words.Add(currWord);
            }           
            return;
        }
        for (int i = startIndex; i < letters.Length; i++)
        {
            Swap(i, startIndex);
            Permute(startIndex + 1);
            Swap(i, startIndex);
        }
    }

    private static bool IsPossible()
    {
        for (int i = 0; i < letters.Length - 1; i++)
        {
            if (letters[i] == letters[i + 1])
            {
                return false;
            }
        }
        return true;
    }

    private static void Swap(int i, int j)
    {
        if (i == j)
        {
            return;
        }
        char temp = letters[i];
        letters[i] = letters[j];
        letters[j] = temp;
    }
}

