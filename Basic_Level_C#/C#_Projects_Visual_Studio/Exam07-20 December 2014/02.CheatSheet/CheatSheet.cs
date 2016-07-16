using System;
using System.Numerics;

class CheatSheet
{
    static void Main()
    {
        int row = int.Parse(Console.ReadLine());
        int col = int.Parse(Console.ReadLine());
        long startRow = long.Parse(Console.ReadLine());
        long startCol = long.Parse(Console.ReadLine());
        long column = startCol;

        BigInteger[,] sheet = new BigInteger[row, col];

        for (int i = 0; i < sheet.GetLength(0); i++)
        {
            for (int j = 0; j < sheet.GetLength(1); j++)
            {
                sheet[i, j] = startRow * column;
                column++;
            }
            column = startCol;
            startRow++;
        }

        for (int i = 0; i < sheet.GetLength(0); i++)
        {
            for (int j = 0; j < sheet.GetLength(1); j++)
            {
                Console.Write(sheet[i, j] + " ");
            }
            Console.WriteLine();
        }
    }
}
