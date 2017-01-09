using System;
using System.Collections.Generic;
using System.Text;

class Point
{
    public int Row { get; set; }

    public int Col { get; set; }

    public string Direction { get; set; }

    public Point Previous { get; set; }
}


public class EscapeFromLabyrinth
{
    private const char VisitedCell = 's';
    private static char[,] maze;

    public static void Main()
    {
        var width = int.Parse(Console.ReadLine());
        var height = int.Parse(Console.ReadLine());

        ReadMaze(width, height);

        Point start = FindStartPoint();

        if (start == null)
        {
            Console.WriteLine("No exit!");
            return;
        }

        if (IsExit(start))
        {
            Console.WriteLine("Start is at the exit.");
            return;
        }
        
        Point exitPoint = FindExit(start);

        if (exitPoint == null)
        {
            Console.WriteLine("No exit!");
        }
        else
        {
            string pathToExit = RecoverPath(exitPoint);
            Console.WriteLine("Shortest exit: {0}", pathToExit);
        }       
    }

    private static string RecoverPath(Point cell)
    {
        StringBuilder builder = new StringBuilder();
        builder.Append(cell.Direction);

        while (cell.Previous != null)
        {
            cell = cell.Previous;
            builder.Append(cell.Direction);
        }
        StringBuilder reversed = new StringBuilder(builder.Length);
        for (int i = builder.Length - 1; i >= 0; i--)
        {
            reversed.Append(builder[i]);
        }
        return reversed.ToString();
    }

    private static Point FindExit(Point start)
    {
        var queue = new Queue<Point>();
        queue.Enqueue(start);

        while (queue.Count > 0)
        {
            Point current = queue.Dequeue();
            if (IsExit(current))
            {
                return current;
            }

            TryDirection(queue, current, "U", -1, 0);
            TryDirection(queue, current, "R", 0, 1);
            TryDirection(queue, current, "D", 1, 0);
            TryDirection(queue, current, "L", -0, -1);
        }
        return null;
    }

    private static void TryDirection(Queue<Point> queue, Point currentCell, string direction, int deltaRow, int deltaCol)
    {
        int newRow = currentCell.Row + deltaRow;
        int newCol = currentCell.Col + deltaCol;

        if (newRow >= 0 && newRow < maze.GetLength(0) && newCol >= 0 && newCol < maze.GetLength(1) && maze[newRow, newCol] == '-')
        {
            maze[newRow, newCol] = VisitedCell;
            Point newCell = new Point
            {
                Row = newRow,
                Col = newCol,
                Direction = direction,
                Previous = currentCell
            };
            queue.Enqueue(newCell);
        }
    }

    private static bool IsExit(Point start)
    {
        return start.Row == 0 ||
               start.Row == maze.GetLength(0) - 1 ||
               start.Col == 0 ||
               start.Col == maze.GetLength(1) - 1;
    }

    private static Point FindStartPoint()
    {
        Point start = null;

        for (int row = 0; row < maze.GetLength(0); row++)
        {
            for (int col = 0; col < maze.GetLength(1); col++)
            {
                if (maze[row, col] == 's')
                {
                    start = new Point();
                    start.Row = row;
                    start.Col = col;
                    return start;
                }
            }
        }
        return start;
    }

    private static void ReadMaze(int width, int height)
    {
        maze = new char[height, width];
        for (int row = 0; row < maze.GetLength(0); row++)
        {
            char[] inputRow = Console.ReadLine().ToCharArray();
            for (int col = 0; col < inputRow.Length; col++)
            {
                maze[row, col] = inputRow[col];
            }
        }
    }
}
