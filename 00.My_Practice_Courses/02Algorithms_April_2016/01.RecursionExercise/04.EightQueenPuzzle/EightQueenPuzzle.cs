using System;
using System.Collections.Generic;

namespace _04.EightQueenPuzzle
{
    class EightQueenPuzzle
    {

        const int Size = 8;
        static bool[,] chessBoard = new bool[Size, Size];
        static int solutionsCounter = 0;

        static HashSet<int> attackedRows = new HashSet<int>();
        static HashSet<int> attackedCols = new HashSet<int>();
        static HashSet<int> attackedLeftDiagonals = new HashSet<int>();
        static HashSet<int> attackedRightDiagonals = new HashSet<int>();

        static void Main(string[] args)
        {
            PutQueen(0);
            Console.WriteLine(solutionsCounter);
        }

        static void PutQueen(int row)
        {

            if (row == Size)
            {
                PrintSolution();
            }
            else
            {
                for (int i = 0; i < Size; i++)
                {
                    if (CanPlaceQueen(row, i))
                    {
                        MarkAttackedPositions(row, i);
                        PutQueen(row + 1);
                        UnmarkAttackedPositions(row, i);
                    }
                }
            }
        }

        private static bool CanPlaceQueen(int row, int col)
        {
            return !attackedRows.Contains(row) &&
                !attackedCols.Contains(col) &&
                !attackedLeftDiagonals.Contains(col - row) &&
                !attackedRightDiagonals.Contains(col + row);
        }

        private static void MarkAttackedPositions(int row, int col)
        {
            attackedRows.Add(row);
            attackedCols.Add(col);
            attackedLeftDiagonals.Add(col - row);
            attackedRightDiagonals.Add(col + row);
            chessBoard[row, col] = true;
        }

        private static void UnmarkAttackedPositions(int row, int col)
        {
            attackedRows.Remove(row);
            attackedCols.Remove(col);
            attackedLeftDiagonals.Remove(col - row);
            attackedRightDiagonals.Remove(col + row);
            chessBoard[row, col] = false;
        }

        private static void PrintSolution()
        {
            for (int i = 0; i < Size; i++)
            {
                for (int j = 0; j < Size; j++)
                {
                    if (chessBoard[i, j])
                    {
                        Console.Write("*");
                    }
                    else
                    {
                        Console.Write("-");
                    }
                }
                Console.WriteLine();
            }
            Console.WriteLine();
            solutionsCounter++;
        }
    }
}
