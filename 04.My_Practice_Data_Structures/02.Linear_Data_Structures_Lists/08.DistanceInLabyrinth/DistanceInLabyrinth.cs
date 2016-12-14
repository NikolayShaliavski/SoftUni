using System;

class DistanceInLabyrinth
{
    static string[,] labyrinth = new[,] { { "0", "0", "0", "x", "0", "x" },
                                          { "0", "x", "0", "x", "0", "x" },
                                          { "0", "*", "x", "0", "x", "0" },
                                          { "0", "x", "0", "0", "0", "0" },
                                          { "0", "0", "0", "x", "x", "0" },
                                          { "0", "0", "0", "x", "0", "x" } };

    static bool[,] visited = new bool[labyrinth.GetLength(0), labyrinth.GetLength(1)];
    static int stepsCounter = 0;

    static void Main(string[] args)
    {
        var startRow = 2;
        var startCol = 1;

        FindPath(startRow, startCol, 0);

        for (int row = 0; row < labyrinth.GetLength(0); row++)
        {
            for (int col = 0; col < labyrinth.GetLength(1); col++)
            {
                if (labyrinth[row, col] == "0")
                {
                    labyrinth[row, col] = "u";
                }
            }
        }

        PrintLabyrinth();
        Console.WriteLine(stepsCounter);
    }

    private static void FindPath(int row, int col, int steps)
    {
        stepsCounter++;
        int cell;

        if (row < 0 || row >= labyrinth.GetLength(0) ||
            col < 0 || col >= labyrinth.GetLength(1) || labyrinth[row, col] == "x")
        {
            return;
        }
        if (visited[row, col] && labyrinth[row, col] != "*")
        {
            cell = int.Parse(labyrinth[row, col]);
            if (steps < cell)
            {
                labyrinth[row, col] = steps + "";
            }
            return;
        }

        if (labyrinth[row, col] == "0")
        {
            labyrinth[row, col] = steps + "";
        }
        visited[row, col] = true;
        FindPath(row, col + 1, steps + 1);
        FindPath(row + 1, col, steps + 1);
        FindPath(row, col - 1, steps + 1);
        FindPath(row - 1, col, steps + 1);
    }
    private static void PrintLabyrinth()
{
    for (int row = 0; row < labyrinth.GetLength(0); row++)
    {
        for (int col = 0; col < labyrinth.GetLength(1); col++)
        {
            Console.Write(labyrinth[row, col] + " ");
        }
        Console.WriteLine();
    }
}

}

