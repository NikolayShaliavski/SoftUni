using System;
using System.Collections.Generic;

internal class GameOfLife
{
    private static void Main()
    {
        int input = int.Parse(Console.ReadLine());
        int[,] matrix = new int[10, 10];
        List<int> listMatrix = new List<int>();

        for (int i = 0; i < input; i++)
        {
            int x = int.Parse(Console.ReadLine());
            int y = int.Parse(Console.ReadLine());

            for (int row = 0; row < matrix.GetLength(0); row++)
            {
                for (int col = 0; col < matrix.GetLength(1); col++)
                {
                    if (row == x && col == (matrix.GetLength(1) - y - 1))
                    {
                        matrix[row, col] = 1;
                    }
                }
            }
            listMatrix.Add(x);
            listMatrix.Add(y);
        }

        List<int> listCells = new List<int>();
        List<int> listValues = new List<int>();
        int counter = 0;

        for (int row = 0; row < matrix.GetLength(0); row++)
        {
            for (int col = 0; col < matrix.GetLength(1); col++)
            {
                int x = row;
                int y = col;
                if (x == 0 && y == 0)
                {
                    listCells.Add(matrix[x, y + 1]);
                    listCells.Add(matrix[x + 1, y + 1]);
                    listCells.Add(matrix[x + 1, y]);
                }
                else if (x == 0 && y > 0 && y < matrix.GetLength(1) - 1)
                {
                    listCells.Add(matrix[x, y - 1]);
                    listCells.Add(matrix[x, y + 1]);
                    listCells.Add(matrix[x + 1, y - 1]);
                    listCells.Add(matrix[x + 1, y]);
                    listCells.Add(matrix[x + 1, y + 1]);
                }
                else if (x == 0 && y == matrix.GetLength(1) - 1)
                {
                    listCells.Add(matrix[x, y - 1]);
                    listCells.Add(matrix[x + 1, y - 1]);
                    listCells.Add(matrix[x + 1, y]);
                }
                else if (x == matrix.GetLength(0) - 1 && y == 0)
                {
                    listCells.Add(matrix[x, y + 1]);
                    listCells.Add(matrix[x - 1, y]);
                    listCells.Add(matrix[x - 1, y + 1]);
                }
                else if (x == matrix.GetLength(0) - 1 && y > 0 && y < matrix.GetLength(1) - 1)
                {
                    listCells.Add(matrix[x - 1, y - 1]);
                    listCells.Add(matrix[x - 1, y]);
                    listCells.Add(matrix[x - 1, y + 1]);
                    listCells.Add(matrix[x, y - 1]);
                    listCells.Add(matrix[x, y + 1]);
                }
                else if (x == matrix.GetLength(0) - 1 && y == matrix.GetLength(1) - 1)
                {
                    listCells.Add(matrix[x, y - 1]);
                    listCells.Add(matrix[x - 1, y - 1]);
                    listCells.Add(matrix[x - 1, y]);
                }
                else if (y == 0 && x > 0 && x < matrix.GetLength(0) - 1)
                {
                    listCells.Add(matrix[x - 1, y]);
                    listCells.Add(matrix[x - 1, y + 1]);
                    listCells.Add(matrix[x, y + 1]);
                    listCells.Add(matrix[x + 1, y]);
                    listCells.Add(matrix[x + 1, y + 1]);
                }
                else if (y == matrix.GetLength(1) - 1 && x > 0 && x < matrix.GetLength(0) - 1)
                {
                    listCells.Add(matrix[x - 1, y - 1]);
                    listCells.Add(matrix[x - 1, y]);
                    listCells.Add(matrix[x, y - 1]);
                    listCells.Add(matrix[x + 1, y - 1]);
                    listCells.Add(matrix[x + 1, y]);
                }
                else
                {
                    listCells.Add(matrix[x - 1, y - 1]);
                    listCells.Add(matrix[x - 1, y]);
                    listCells.Add(matrix[x - 1, y + 1]);
                    listCells.Add(matrix[x, y - 1]);
                    listCells.Add(matrix[x, y + 1]);
                    listCells.Add(matrix[x + 1, y - 1]);
                    listCells.Add(matrix[x + 1, y]);
                    listCells.Add(matrix[x + 1, y + 1]);
                }
                foreach (int c in listCells)
                {
                    if (c == 1)
                    {
                        counter++;
                    }
                }
                listCells.Clear();
                listValues.Add(counter);
                counter = 0;
            }
        }

        for (int row = 0; row < matrix.GetLength(0); row++)
        {
            for (int col = 0, k = row * 10; col < matrix.GetLength(1); col++, k++)
            {
                int x = row;
                int y = col;
                if (matrix[x, y] == 1 && listValues[k] < 2)
                {
                    matrix[x, y] = 0;
                }
                else if (matrix[x, y] == 1 && (listValues[k] == 2 || listValues[k] == 3))
                {
                    matrix[x, y] = 1;
                }
                else if (matrix[x, y] == 1 && listValues[k] > 3)
                {
                    matrix[x, y] = 0;
                }
                else if (matrix[x, y] == 0 && listValues[k] == 3)
                {
                    matrix[x, y] = 1;
                }
            }
        }

        for (int row = 0; row < matrix.GetLength(0); row++)
        {
            for (int col = 0; col < matrix.GetLength(1); col++)
            {
                Console.Write(matrix[row, col]);
            }
            Console.WriteLine();
        }
    }
}


