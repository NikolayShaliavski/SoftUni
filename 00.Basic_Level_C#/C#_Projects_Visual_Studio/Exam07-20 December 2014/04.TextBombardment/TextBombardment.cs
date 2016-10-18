using System;
using System.Collections.Generic;
using System.Linq;

class TextBombardment
{
    static void Main()
    {
        string text = Console.ReadLine();
        int line = int.Parse(Console.ReadLine());
        List<int> bomb = Console.ReadLine().Split().Select(Int32.Parse).ToList();

        int cols = line;
        int rows = (int)Math.Ceiling((double)text.Length / line);

        char[,] table = new char[rows, cols];
        int count = 0;

        for (int row = 0; row < table.GetLength(0); row++)
        {
            for (int col = 0; col < table.GetLength(1); col++)
            {
                if (count >= text.Length)
                {
                    table[row, col] = ' ';
                }
                else
                {
                    table[row, col] = text[count];
                    count++;
                }                                
            }
        }

        for (int row = 0; row < table.GetLength(0); row++)
        {
            for (int col = 0; col < table.GetLength(1); col++)
            {
                if (col == bomb.Count)
                {
                    break;
                }
                char current = table[row, bomb[col]];
                table[row, bomb[col]] = ' ';
                if (row != table.GetLength(0) - 1 && table[row + 1, bomb[col]] == ' ' && current != ' ')
                {
                    bomb.RemoveAt(col);
                    col--;
                }
            }
        }
        List<char> result = new List<char>();

        for (int row = 0; row < table.GetLength(0); row++)
        {
            for (int col = 0; col < table.GetLength(1); col++)
            {
                result.Add(table[row, col]);
                if (result.Count == text.Length)
                {
                    break;
                }
            }
        }

        foreach (char i in result)
        {
            Console.Write(i);
        }
        Console.WriteLine();
    }
}
