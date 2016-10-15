using System;
using System.Collections.Generic;
using System.Linq;

class WordsIterative
{
    //passes test for time in judge but memory tests doesn't pass
    //memoization with numbers instead srtings
    static int[] keys;
    static Dictionary<char, int> keyMap;

    static void Main(string[] args)
    {
        char[] letters = Console.ReadLine().ToArray();
        ISet<long> words = new HashSet<long>();
        keys = new int[letters.Length];
        keyMap = new Dictionary<char, int>();
        int permutationsCounter = 0;

        GenKeys(letters);

        long newWord = BuildNewWordKey();
        if (IsPossible(letters) && !words.Contains(newWord))
        {
            permutationsCounter++;
            
            words.Add(newWord);
        }

        int[] p = new int[letters.Length];
        int i = 1;

        while (i < letters.Length)
        {
            if (p[i] < i)
            {
                int j = (i % 2) == 0 ? 0 : p[i];
                Swap(letters, j, i);
                newWord = BuildNewWordKey();
                if (IsPossible(letters) && !words.Contains(newWord))
                {
                    words.Add(newWord);
                    permutationsCounter++;
                }

                p[i]++;
                i = 1;
            }
            else
            {
                p[i] = 0;
                i++;
            }
        }
        Console.WriteLine(permutationsCounter);
    }

    private static void GenKeys(char[] letters)
    {
        int counter = 1;
        for (int i = 0; i < letters.Length; i++)
        {
            if (!keyMap.ContainsKey(letters[i]))
            {
                keyMap[letters[i]] = counter;
                counter++;
            }
        }
        for (int i = 0; i < letters.Length; i++)
        {
            keys[i] = keyMap[letters[i]];
        }
    }

    private static long BuildNewWordKey()
    {
        long newWord = keys[0];
        for (int i = 1; i < keys.Length; i++)
        {
            if (keys[i] >= 9)
            {
                newWord = newWord * 100 + keys[i];
            }
            else
            {
                newWord = newWord * 10 + keys[i];
            }
        }
        return newWord;
    }

    private static bool IsPossible(char[] letters)
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

    private static void Swap(char[] letters, int i, int j)
    {
        if (i == j)
        {
            return;
        }
        char temp = letters[i];
        letters[i] = letters[j];
        letters[j] = temp;

        int temp2 = keys[i];
        keys[i] = keys[j];
        keys[j] = temp2;
    }
}

