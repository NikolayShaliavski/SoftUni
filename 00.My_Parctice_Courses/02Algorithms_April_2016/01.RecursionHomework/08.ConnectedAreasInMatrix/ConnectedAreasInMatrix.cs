using System;
using System.Collections.Generic;
using System.Linq;

namespace _08.ConnectedAreasInMatrix
{
    class ConnectedAreasInMatrix
    {
        //for testing only set the name of matrix to testMatrix
        private static char[,] testMatrix = { { ' ', ' ', ' ', '*', ' ', ' ' , ' ', '*', ' '},
                                              { ' ', ' ', ' ', '*', ' ', ' ' , ' ', '*', ' '},
                                              { ' ', ' ', ' ', '*', ' ', ' ' , ' ', '*', ' '},
                                              { ' ', ' ', ' ', ' ', '*', ' ' , '*', ' ', ' '}};

        private static char[,] matrix02 = { { '*', ' ', ' ', '*', ' ', ' ' , ' ', '*', ' ', ' ' },
                                            { '*', ' ', ' ', '*', ' ', ' ' , ' ', '*', ' ', ' ' },
                                            { '*', ' ', ' ', '*', '*', '*' , '*', '*', ' ', ' ' },
                                            { '*', ' ', ' ', '*', ' ', ' ' , ' ', '*', ' ', ' ' },
                                            { '*', ' ', ' ', '*', ' ', ' ' , ' ', '*', ' ', ' ' }};

        private static char[,] matrix03 = { { ' ', ' ', ' ', ' ', ' '},
                                            { ' ', ' ', ' ', ' ', ' '},
                                            { ' ', ' ', ' ', ' ', ' '},
                                            { ' ', ' ', ' ', ' ', ' '}};

        private static char[,] matrix04 = { { '*', '*', '*', '*'},
                                            { '*', '*', '*', '*'},
                                            { '*', '*', '*', '*'},
                                            { '*', '*', '*', '*'}};
        //bool matrix to mark already visited cells
        private static bool[,] visited = new bool[testMatrix.GetLength(0), testMatrix.GetLength(1)];

        private static int cellCounter = 0;

        static void Main(string[] args)
        {
            ISet<Area> allAreas = new SortedSet<Area>();

            for (int row = 0; row < testMatrix.GetLength(0); row++)
            {
                for (int col = 0; col < testMatrix.GetLength(1); col++)
                {
                    if (testMatrix[row, col] == ' ' && InRange(row, col) && !visited[row, col])
                    {
                        //call recursion for current unvisited annd free cell
                        TraverseArea(row, col);
                        //create Area object when all recursive calls finished
                        Area area = new Area(row, col, cellCounter);

                        allAreas.Add(area);//add found area
                        //set counter to zero because we need it for another areas
                        cellCounter = 0;
                    }
                }
            }
            PrintAllAreas(allAreas);
        }

        private static void TraverseArea(int row, int col)
        {
            //bottoms of recursion
            if (!InRange(row, col))
            {
                return;
            }
            if (visited[row, col] || testMatrix[row, col] != ' ')
            {
                return;
            }
            //Pre-action
            //mark current cell as visited an increase cells counter - we have found free unvisited cell
            visited[row, col] = true;
            cellCounter++;

            //recursive calls for all neighbour cells
            TraverseArea(row - 1, col);//up
            TraverseArea(row, col + 1);//right
            TraverseArea(row + 1, col);//down
            TraverseArea(row, col - 1);//left
        }

        private static bool InRange(int row, int col)
        {
            return row >= 0 && row < testMatrix.GetLength(0) &&
                col >= 0 && col < testMatrix.GetLength(1);
        }

        private static void PrintAllAreas(ISet<Area> allAreas)
        {
            int localCounter = 0;

            int totalNumberOfAreas = allAreas.Count();
            Console.WriteLine("Total areas found: {0}", totalNumberOfAreas);

            foreach (var area in allAreas)
            {
                localCounter++;
                Console.WriteLine("Area #{0} at ({1}, {2}), size: {3}",
                    localCounter, area.Row, area.Col, area.Cells);
            }
        }
    }
}
