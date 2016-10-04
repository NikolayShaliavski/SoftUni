using System;
using System.Collections.Generic;

class LineInverter
{
    static int size;
    static bool[,] matrix;
    static int steps;
    static int bestLine;
    static bool isRow;
    static bool[] visitedRows;
    static bool[] visitedCols;

    static int maxWhiteCells;
    static int currWhiteCells;

    static void Main(string[] args)
    {
        size = int.Parse(Console.ReadLine());
        matrix = new bool[size, size];
        steps = 0;
        bestLine = 0;
        isRow = false;
        visitedRows = new bool[size];
        visitedCols = new bool[size];

        for (int row = 0; row < size; row++)
        {
            string line = Console.ReadLine();
            for (int col = 0; col < size; col++)
            {
                matrix[row, col] = (line[col] == 'W');
            }
        }

        for (int step = 0; step < 2 * size; step++)
        {
            FindBestLine();
            if (maxWhiteCells == 0)
            {
                Console.WriteLine(steps);
                return;
            }
            if (isRow && !visitedRows[bestLine])
            {
                InvertRow(bestLine);
                visitedRows[bestLine] = true;
                steps++;
            }
            else if (!visitedCols[bestLine])
            {
                InvertCol(bestLine);
                visitedCols[bestLine] = true;
                steps++;
            }
        }
        Console.WriteLine(-1);
    }

    private static void PrintMatrix()
    {
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                Console.Write(matrix[row, col]);
            }
            Console.WriteLine();
        }
    }

    private static void InvertCol(int col)
    {
        for (int row = 0; row < size; row++)
        {
            if (matrix[row, col])
            {
                matrix[row, col] = false;
            }
            else
            {
                matrix[row, col] = true;
            }
        }
    }

    private static void InvertRow(int row)
    {
        for (int col = 0; col < size; col++)
        {
            if (matrix[row, col])
            {
                matrix[row, col] = false;
            }
            else
            {
                matrix[row, col] = true;
            }
        }
    }

    private static void FindBestLine()
    {
        maxWhiteCells = 0;
        currWhiteCells = 0;

        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            {
                if (matrix[row, col])
                {
                    currWhiteCells++;
                }
            }
            if (maxWhiteCells < currWhiteCells)
            {
                maxWhiteCells = currWhiteCells;
                bestLine = row;
                isRow = true;
            }
            currWhiteCells = 0;

        }
        for (int col = 0; col < size; col++)
        {
            for (int row = 0; row < size; row++)
            {
                if (matrix[row, col])
                {
                    currWhiteCells++;
                }
            }
            if (maxWhiteCells < currWhiteCells)
            {
                maxWhiteCells = currWhiteCells;
                bestLine = col;
                isRow = false;
            }
            currWhiteCells = 0;

        }
    }
}
