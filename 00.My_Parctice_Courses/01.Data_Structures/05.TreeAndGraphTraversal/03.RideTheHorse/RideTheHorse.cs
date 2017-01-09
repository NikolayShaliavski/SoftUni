using System;

using System.Collections.Generic;

class Cell
{
    public int Row { get; set; }

    public int Col { get; set; }

    public int Steps { get; set; }
}

class RideTheHorse
{
    private static int[,] board;

    static void Main(string[] args)
    {
        var rows = int.Parse(Console.ReadLine());
        var cols = int.Parse(Console.ReadLine());
        board = new int[rows, cols];

        var startRow = int.Parse(Console.ReadLine());
        var startCol = int.Parse(Console.ReadLine());

        Console.WriteLine();

        Cell startCell = new Cell
        {
            Row = startRow,
            Col = startCol,
            Steps = 1
        };
        board[startRow, startCol] = startCell.Steps;

        Queue<Cell> queue = new Queue<Cell>();
        queue.Enqueue(startCell);

        while (queue.Count > 0)
        {
            Cell currCell = queue.Dequeue();
            int currRow = currCell.Row;
            int currCol = currCell.Col;

            //horse moves on board
            TryMoveHorse(queue, currCell, currRow - 2, currCol + 1);//up -> up -> right
            TryMoveHorse(queue, currCell, currRow - 1, currCol + 2);//up -> right -> right

            TryMoveHorse(queue, currCell, currRow - 2, currCol - 1);//up -> up -> left
            TryMoveHorse(queue, currCell, currRow - 1, currCol - 2);//up -> left -> left

            TryMoveHorse(queue, currCell, currRow + 2, currCol - 1);//down -> down -> left
            TryMoveHorse(queue, currCell, currRow + 1, currCol - 2);//down -> left -> left


            TryMoveHorse(queue, currCell, currRow + 2, currCol + 1);//down -> down -> right
            TryMoveHorse(queue, currCell, currRow + 1, currCol + 2);//down -> right -> right
        }

        var column = cols / 2;
        for (int row = 0; row < rows; row++)
        {
            Console.WriteLine(board[row, column]);
        }

        PrintBoard();
    }

    private static void PrintBoard()
    {
        for (int row = 0; row < board.GetLength(0); row++)
        {
            for (int col = 0; col < board.GetLength(1); col++)
            {
                Console.Write(board[row, col] + " ");
            }
            Console.WriteLine();
        }
    }

    private static void TryMoveHorse(Queue<Cell> queue, Cell currCell, int newRow, int newCol)
    {
        if (newRow >= 0 &&
            newRow < board.GetLength(0) &&
            newCol >= 0 &&
            newCol < board.GetLength(1) &&
            board[newRow, newCol] == 0)
        {
            Cell newCell = new Cell
            {
                Row = newRow,
                Col = newCol,
                Steps = currCell.Steps + 1
            };

            board[newRow, newCol] = newCell.Steps;

            queue.Enqueue(newCell);
        }
    }
}
