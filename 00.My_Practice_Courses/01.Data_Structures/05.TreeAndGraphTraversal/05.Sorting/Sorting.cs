using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

class Sorting
{
    static void Main(string[] args)
    {
        var n = int.Parse(Console.ReadLine());
        var elements = Console.ReadLine().Split(' ').Select(char.Parse).ToArray();
        var k = int.Parse(Console.ReadLine());

        Queue<string> queue = new Queue<string>();
        string sequence = ConvertToString(elements);
        queue.Enqueue(sequence);
        int counter = 0;

        while (queue.Count > 0)
        {
            sequence = queue.Dequeue();
            counter++;
            if (IsSorted(sequence.ToCharArray()))
            {
                break;
            }
            for (int i = 0; i <= n - k; i++)
            {
                sequence = Reverse(sequence.ToCharArray(), i, k);
                if (!queue.Contains(sequence))
                {
                    queue.Enqueue(sequence);
                }
                //for (int j = i; j < n - k; j++)
                //{
                    
                //}
            }
        }
        Console.WriteLine(counter);
        //Console.WriteLine(Reverse(elements, 1, k));
    }

    private static bool IsSorted(char[] sequence)
    {
        for (int i = 0; i < sequence.Length - 1; i++)
        {
            if (sequence[i] > sequence[i + 1])
            {
                return false;
            }
        }
        return true;
    }

    static string Reverse(char[] elements, int start, int length)
    {
        char[] temp = new char[elements.Length];
        Array.Copy(elements, temp, elements.Length);

        for (int i = start + length - 1, j = start; i >= start; i--, j++)
        {
            temp[j] = elements[i];
        }

        return ConvertToString(temp);
    }

    private static string ConvertToString(char[] temp)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < temp.Length; i++)
        {
            builder.Append(temp[i]);
        }

        return builder.ToString();
    }
}

