using System;
using System.Collections.Generic;

namespace _07.PathInLabyrinth
{
    class PathInLabyrinth
    {
        static char[,] labyrinth = { { 's', ' ', ' ', ' ', ' ', ' ' },
                                     { ' ', '*', '*', ' ', '*', ' ' },
                                     { ' ', '*', '*', ' ', '*', ' ' },
                                     { ' ', '*', 'e', ' ', ' ', ' ' },
                                     { ' ', ' ', ' ', '*', ' ', ' ' } };

        static List<char> path = new List<char>();
        static int allPaths = 0;

        static void Main(string[] args)
        {
            FindWay(0, 0, 'S');
            Console.WriteLine(allPaths);
        }

        static void FindWay(int row, int col, char direction)
        {
            //bottoms of recursion
            if (!InRange(row, col))
            {
                return;
            }
            if (labyrinth[row, col] == '.' || labyrinth[row, col] == '*')
            {
                return;
            }
            //if coordinates are in range and we can move in that direction
            path.Add(direction);
            //if we have found the exit
            if (labyrinth[row, col] == 'e')
            {
                PrintPath();
                allPaths++;
                return;
            }
            //mark path behind -> Pre-action
            labyrinth[row, col] = '.';
            //recursive calls for all four possible directions
            FindWay(row, col + 1, 'R');
            FindWay(row + 1, col, 'D');
            FindWay(row, col - 1, 'L');
            FindWay(row - 1, col, 'U');
            //unmark path and remove last added direction in result list -> Post-action
            labyrinth[row, col] = ' ';
            path.RemoveAt(path.Count - 1);
        }

        private static void PrintPath()
        {
            Console.WriteLine(string.Join(" ", path));
        }

        private static bool InRange(int row, int col)
        {
            return row >= 0 && row < labyrinth.GetLength(0) &&
                    col >= 0 && col < labyrinth.GetLength(1);
        }
    }
}
