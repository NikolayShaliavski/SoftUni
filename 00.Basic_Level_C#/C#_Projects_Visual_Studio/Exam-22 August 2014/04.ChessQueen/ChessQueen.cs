using System;
using System.Collections.Generic;

class ChessQueen
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        int distance = int.Parse(Console.ReadLine());

        if (distance >= n - 1)
        {
            Console.WriteLine("No valid positions");
        }

        List<int> first = new List<int>();
        List<int> second = new List<int>();
        distance += 1;
        for (int row = 1; row <= n; row++)
        {
            for (int col = 1; col <= n; col++)
            {
                if (col + distance <= n)
                {
                    first.Add(row);
                    first.Add(col);
                    second.Add(row);
                    second.Add(col + distance);
                }
                if (row + distance <= n && col + distance <= n)
                {
                    first.Add(row);
                    first.Add(col);
                    second.Add(row + distance);
                    second.Add(col + distance);
                }
                if (row + distance <= n)
                {
                    first.Add(row);
                    first.Add(col);
                    second.Add(row + distance);
                    second.Add(col);
                }
                if (row + distance <= n && col - distance >= 1)
                {
                    first.Add(row);
                    first.Add(col);
                    second.Add(row + distance);
                    second.Add(col - distance);
                }
                if (col - distance >= 1)
                {
                    first.Add(row);
                    first.Add(col);
                    second.Add(row);
                    second.Add(col - distance);
                }
                if (row - distance >= 1 && col - distance >= 1)
                {
                    first.Add(row);
                    first.Add(col);
                    second.Add(row - distance);
                    second.Add(col - distance);
                }
                if (row - distance >= 1)
                {
                    first.Add(row);
                    first.Add(col);
                    second.Add(row - distance);
                    second.Add(col);
                }
                if (row - distance >= 1 && col + distance <= n)
                {
                    first.Add(row);
                    first.Add(col);
                    second.Add(row - distance);
                    second.Add(col + distance);
                }
            }
        }

        for (int i = 0; i < first.Count - 1; i += 2)
        {
            Console.WriteLine("{0}{1} - {2}{3}", (char)(first[i] + 96), first[i + 1], (char)(second[i] + 96), second[i + 1]);
        }
    }
}
