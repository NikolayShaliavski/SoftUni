using System;
using System.Collections.Generic;
using System.Linq;

class ZigZagMatrix
{
    static void Main(string[] args)
    {
        var rows = int.Parse(Console.ReadLine());
        var cols = int.Parse(Console.ReadLine());
        var matrix = new int[rows, cols];
        FillMatrix(rows, matrix);
  
        var maxPaths = new int[rows, cols];
        var prevRowIndex = new int[rows, cols];

        for (int i = 1; i < rows; i++)
        {
            maxPaths[i, 0] = matrix[i, 0];
        }

        for (int col = 1; col < cols; col++)
        {
            for (int row = 0; row < rows; row++)
            {
                int prevMax = 0;
                if (col % 2 != 0)// odd column -> we check cells below the row in previous column
                {
                    for (int i = row + 1; i < rows; i++)
                    {
                        if (maxPaths[i, col - 1] > prevMax)
                        {
                            prevMax = maxPaths[i, col - 1];
                            prevRowIndex[row, col] = i;
                        }
                    }
                }
                else// even column -> we go up the row from previous column
                {
                    for (int i = 0; i <= row - 1; i++)
                    {
                        if (maxPaths[i, col - 1] > prevMax)
                        {
                            prevMax = maxPaths[i, col - 1];
                            prevRowIndex[row, col] = i;
                        }
                    }
                }
                maxPaths[row, col] = prevMax + matrix[row, col];
            }
        }

        var lastColumnRowIndex = GetLastRowIndexOfPath(maxPaths, cols);

        List<int> path = RecoverMaxPath(matrix, cols, lastColumnRowIndex, prevRowIndex);
        Console.WriteLine("{0} = {1}", path.Sum(), string.Join(" + ", path));
    }

    private static List<int> RecoverMaxPath(int[,] matrix, 
                                            int cols, 
                                            int rowIndex, 
                                            int[,] prevRowIndex)
    {
        List<int> maxPath = new List<int>();
        var columnIndex = cols - 1;

        while (columnIndex >= 0)
        {
            maxPath.Add(matrix[rowIndex, columnIndex]);
            rowIndex = prevRowIndex[rowIndex, columnIndex];
            columnIndex--;
        }
        maxPath.Reverse();
        return maxPath;
    }

    private static int GetLastRowIndexOfPath(int[,] maxPaths, int cols)
    {
        int currentRowIndex = -1;
        int globalMax = 0;

        for (int row = 0; row < maxPaths.GetLength(0); row++)
        {
            if (maxPaths[row, cols - 1] > globalMax)
            {
                currentRowIndex = row;
                globalMax = maxPaths[row, cols - 1];
            }
        }
        return currentRowIndex;
    }

    private static void FillMatrix(int rows, int[,] matrix)
    {
        for (int i = 0; i < rows; i++)
        {
            var currRow = Console.ReadLine().Split(',').Select(int.Parse).ToArray();
            for (int j = 0; j < currRow.Length; j++)
            {
                matrix[i, j] = currRow[j];
            }
        }
    }
}