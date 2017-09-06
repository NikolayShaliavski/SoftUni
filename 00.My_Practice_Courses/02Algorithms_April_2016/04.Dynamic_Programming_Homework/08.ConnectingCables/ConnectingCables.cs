using System;
using System.Collections.Generic;

namespace _08.ConnectingCables
{
    class ConnectingCables
    {
        static void Main(string[] args)
        {

            int[] side01 = { 1, 2, 3 };
            int[] side02 = { 1, 2, 3 };

            int[] result = ConnectCables(side01, side02);

            Console.WriteLine("Maximum pairs connected: " + result.Length);
            Console.WriteLine("Connected pairs: " + string.Join(" ", result));
        }

        static int[] ConnectCables(int[] side01, int[] side02)
        {
            var room = new int[side01.Length + 1, side02.Length + 1];

            for (int row = 1; row < room.GetLength(0); row++)
            {
                for (int col = 1; col < room.GetLength(1); col++)
                {
                    if (side01[row - 1] == side02[col - 1])
                    {
                        room[row, col] = room[row - 1, col - 1] + 1;
                    }
                    else
                    {
                        room[row, col] = Math.Max(room[row - 1, col], room[row, col - 1]);
                    }
                }
            }
            List<int> result = RetrieveConnectedPairs(side01, side02, room);
            result.Reverse();
            return result.ToArray();
        }

        private static List<int> RetrieveConnectedPairs(int[] side01, int[] side02, int[,] room)
        {
            List<int> connectedPairs = new List<int>();
            int row = side01.Length;
            int col = side02.Length;

            while (row > 0 && col > 0)
            {
                if (side01[row -1] == side02[col - 1])
                {
                    connectedPairs.Add(side01[row - 1]);
                    row--;
                    col--;
                }
                else if (room[row, col] == room[row - 1, col])
                {
                    row--;
                }
                else
                {
                    col--;
                }
            }
            return connectedPairs;
        }
    }
}
