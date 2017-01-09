package words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordsIterative {

    //passes test for time in judge but memory tests doesn't pass
    //memoization with numbers instead strings
    private static char[] letters;
    static int[] keys;
    static Map<Character, Integer> keyMap;
    private static int steps = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        letters = reader.readLine().toCharArray();
        Set<Long> words = new HashSet<>();
        keys = new int[letters.length];
        keyMap = new HashMap<>();
        int permutationsCounter = 0;

        genKeys(letters);

        Long newWord = buildNewWordKey();
        if (IsPossible(letters) && !words.contains(newWord))
        {
            permutationsCounter++;

            words.add(newWord);
        }

        int[] p = new int[letters.length];
        int i = 1;

        while (i < letters.length)
        {
            steps++;
            if (p[i] < i)
            {
                int j = (i % 2) == 0 ? 0 : p[i];
                Swap(letters, j, i);
                newWord = buildNewWordKey();
                if (IsPossible(letters) && !words.contains(newWord))
                {
                    words.add(newWord);
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
        System.out.println(permutationsCounter);
        System.out.println(steps);
    }

    private static void genKeys(char[] letters)
    {
        int counter = 1;
        for (int i = 0; i < letters.length; i++)
        {
            if (!keyMap.containsKey(letters[i]))
            {
                keyMap.put(letters[i], counter);
                counter++;
            }
        }
        for (int i = 0; i < letters.length; i++)
        {
            keys[i] = keyMap.get(letters[i]);
        }
    }

    private static long buildNewWordKey()
    {
        long newWord = keys[0];
        for (int i = 1; i < keys.length; i++)
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

    private static boolean IsPossible(char[] letters)
    {
        for (int i = 0; i < letters.length - 1; i++)
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
