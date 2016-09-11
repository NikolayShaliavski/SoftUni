using System;
using System.Collections.Generic;

namespace _07.Snakes
{
    class Snakes
    {
        private static List<char> snake;
        private static HashSet<string> uniqueSnakes;
        private static int snakeCount;

        private static bool[,] visited;

        static void Main(string[] args)
        {
            var blocks = int.Parse(Console.ReadLine());
            snake = new List<char>();
            snakeCount = 0;
            visited = new bool[blocks * 2, blocks * 2];
            visited[blocks - 1, blocks - 1] = true;
            snake.Add('S');
            uniqueSnakes = new HashSet<string>();
            

            GenerateSnake(blocks - 1, 'R', blocks - 1, blocks);
            PrintSnakes();
            Console.WriteLine("Snakes count = " + snakeCount);
        }

        private static void PrintSnakes()
        {
            foreach (var snake in uniqueSnakes)
            {
                Console.WriteLine(snake);
            }
        }

        private static void GenerateSnake(int blocks, char direction, int row, int col)
        {
            if (visited[row, col])
            {
                return;
            }
            visited[row, col] = true;
            snake.Add(direction);
            if (blocks == 1)
            {

                if (FoundNewSnake())
                {
                    string newSnake = string.Join("", snake);
                    uniqueSnakes.Add(newSnake);
                    snakeCount++;
                }

                snake.RemoveAt(snake.Count - 1);
                visited[row, col] = false;
                return;
            }
            GenerateSnake(blocks - 1, 'R', row, col + 1);
            GenerateSnake(blocks - 1, 'D', row + 1, col);
            GenerateSnake(blocks - 1, 'L', row, col - 1);
            GenerateSnake(blocks - 1, 'U', row - 1, col);

            visited[row, col] = false;
            snake.RemoveAt(snake.Count - 1);

        }

        private static bool FoundNewSnake()
        {
            HashSet<string> snakeForms = new HashSet<string>();
            List<char> copyOfSnake = new List<char>(snake);

            //add original snake form
            snakeForms.Add(string.Join("", copyOfSnake));
            copyOfSnake = Flip(copyOfSnake);
            //add flipped snake form
            snakeForms.Add(string.Join("", copyOfSnake));
            List<char> reversedSnake = Reverse();
            //add reversed snake
            snakeForms.Add(string.Join("", reversedSnake));
            copyOfSnake = Flip(reversedSnake);//flip reversed snake
            //add flipped reversed snake
            snakeForms.Add(string.Join("", copyOfSnake));

            foreach (var snake in uniqueSnakes)
            {
                foreach (var snakeForm in snakeForms)
                {
                    if (snake == snakeForm)
                    {
                        return false;
                    }
                }
            }
            return true;
        }

        private static List<char> Reverse()
        {
            List<char> reversedSnake = new List<char>(snake);
            reversedSnake.Reverse();
            reversedSnake.Remove('S');
            reversedSnake.Insert(0, 'S');//add snake head to the start

            //rotate snake 
            while (reversedSnake[1] != 'R')
            {
                for (int i = 1; i < reversedSnake.Count; i++)
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
            return reversedSnake;
        }

        private static List<char> Flip(List<char> snakeToFlip)
        {
            List<char> flipped = new List<char>();
            for (int i = 0; i < snakeToFlip.Count; i++)
            {
                if (snakeToFlip[i] == 'D')
                {
                    flipped.Add('U');
                }
                else if (snakeToFlip[i] == 'U')
                {
                    flipped.Add('D');
                }
                else
                {
                    flipped.Add(snakeToFlip[i]);
                }
            }
            return flipped;
        }
    }
}
