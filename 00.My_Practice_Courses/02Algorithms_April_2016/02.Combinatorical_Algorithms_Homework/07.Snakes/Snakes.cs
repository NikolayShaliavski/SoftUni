using System;
using System.Collections.Generic;
using System.Text;

namespace _07.Snakes
{
    class Snakes
    {
        private static StringBuilder currentSnake;
        private static StringBuilder resultSnake;
        private static HashSet<string> allSnakes;
        private static int snakeCount;

        private static bool[,] visited;

        static void Main(string[] args)
        {
            var blocks = int.Parse(Console.ReadLine());

            currentSnake = new StringBuilder();
            resultSnake = new StringBuilder();
            allSnakes = new HashSet<string>();
            snakeCount = 0;

            visited = new bool[blocks * 2, blocks * 2];
            visited[blocks - 1, blocks - 1] = true;
            currentSnake.Append('S');
            
            GenerateSnake(blocks - 1, 'R', blocks - 1, blocks);
            Console.WriteLine(resultSnake.ToString().Trim());
            Console.WriteLine("Snakes count = " + snakeCount);
        }

        private static void GenerateSnake(int blocks, char direction, int row, int col)
        {
            if (visited[row, col])
            {
                return;
            }
            visited[row, col] = true;
            currentSnake.Append(direction);
            if (blocks == 1)
            {

                if (!allSnakes.Contains(currentSnake.ToString()))
                {
                    //string newSnake = string.Join("", snake);
                    AddSnakeVariations();
                    resultSnake.Append(currentSnake).AppendLine();
                    snakeCount++;
                }

                currentSnake.Remove(currentSnake.Length - 1, 1);
                visited[row, col] = false;
                return;
            }
            GenerateSnake(blocks - 1, 'R', row, col + 1);
            GenerateSnake(blocks - 1, 'D', row + 1, col);
            GenerateSnake(blocks - 1, 'L', row, col - 1);
            GenerateSnake(blocks - 1, 'U', row - 1, col);

            visited[row, col] = false;
            currentSnake.Remove(currentSnake.Length - 1, 1);

        }


        private static void AddSnakeVariations()
        {
            string snakeCopy = currentSnake.ToString();
            //add original snake form
            allSnakes.Add(snakeCopy);
            snakeCopy = Flip(snakeCopy);
            //add flipped snake form
            allSnakes.Add(snakeCopy);

            string reversedSnake = Reverse();
            //add reversed snake
            allSnakes.Add(reversedSnake);
            snakeCopy = Flip(reversedSnake);//flip reversed snake
            //add flipped reversed snake
            allSnakes.Add(snakeCopy);
        }

        private static string Reverse()
        {
            StringBuilder reversedSnake = new StringBuilder();
            reversedSnake.Append('S');//add snake head to the start
            for (int i = currentSnake.Length - 1; i > 0; i--)
            {
                reversedSnake.Append(currentSnake[i]);
            }

            //rotate snake 
            while (reversedSnake[1] != 'R')
            {
                for (int i = 1; i < reversedSnake.Length; i++)
                {
                    if (reversedSnake[i] == 'R')
                    {
                        reversedSnake[i] = 'D';
                    }
                    else if (reversedSnake[i] == 'D')
                    {
                        reversedSnake[i] = 'L';
                    }
                    else if (reversedSnake[i] == 'L')
                    {
                        reversedSnake[i] = 'U';
                    }
                    else
                    {
                        reversedSnake[i] = 'R';
                    }
                }
            }
            return reversedSnake.ToString();
        }

        private static string Flip(string snakeToFlip)
        {
            StringBuilder flipped = new StringBuilder();
            for (int i = 0; i < snakeToFlip.Length; i++)
            {
                if (snakeToFlip[i] == 'D')
                {
                    flipped.Append('U');
                }
                else if (snakeToFlip[i] == 'U')
                {
                    flipped.Append('D');
                }
                else
                {
                    flipped.Append(snakeToFlip[i]);
                }
            }
            return flipped.ToString();
        }
    }
}
