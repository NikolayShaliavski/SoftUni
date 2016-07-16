using System;
using System.Collections.Generic;
using System.Text;

class BitsAtCrossroads
{
    static void Main()
    {
        int size = int.Parse(Console.ReadLine());
        int[,] board = new int[size, size];
        int count = 0;
        string command = Console.ReadLine();

        while (command != "end")
        {
            string[] array = command.Split();

            int line = int.Parse(array[0]);
            int column = int.Parse(array[1]);
            column = board.GetLength(1) - column - 1;
            board[line, column] = 1;
            count += 1;
            int lineFor = line;
            int colFor = column;
            for (int row = lineFor; row < board.GetLength(0) - 1; row++)//1
            {
                for (int col = colFor; col < board.GetLength(1) - 1; col += board.GetLength(1))
                {
                    if (board[row + 1, col + 1] == 1)
                    {
                        count++;
                    }
                    board[row + 1, col + 1] = 1;
                    colFor++;
                }
            }
            lineFor = line;
            colFor = column;
            for (int row = lineFor; row > 0; row--)//2
            {
                for (int col = colFor; col < board.GetLength(1) - 1; col += board.GetLength(1))
                {
                    if (board[row - 1, col + 1] == 1)
                    {
                        count++;
                    }
                    board[row - 1, col + 1] = 1;
                    colFor++;
                }
            }
            lineFor = line;
            colFor = column;
            for (int row = lineFor; row < board.GetLength(0) - 1; row++)//3
            {
                for (int col = colFor; col > 0; col -= board.GetLength(1))
                {
                    if (board[row + 1, col - 1] == 1)
                    {
                        count++;
                    }
                    board[row + 1, col - 1] = 1;
                    colFor--;
                }
            }
            lineFor = line;
            colFor = column;
            for (int row = lineFor; row > 0; row--)//4
            {
                for (int col = colFor; col > 0; col -= board.GetLength(1))
                {
                    if (board[row - 1, col - 1] == 1)
                    {
                        count++;
                    }
                    board[row - 1, col - 1] = 1;
                    colFor--;
                }
            }
            command = Console.ReadLine();
        }
        List<int> boardList = new List<int>();
        for (int i = 0; i < board.GetLength(0); i++)
        {
            for (int j = 0; j < board.GetLength(1); j++)
            {
                boardList.Add(board[i, j]);
            }
            StringBuilder builder = new StringBuilder();
            foreach (int s in boardList)
            {
                builder.Append(s);
            }
            string result = builder.ToString();
            uint number = Convert.ToUInt32(result, 2);
            Console.WriteLine(number);
            boardList.Clear();
        }
        Console.WriteLine(count);
    }
}
