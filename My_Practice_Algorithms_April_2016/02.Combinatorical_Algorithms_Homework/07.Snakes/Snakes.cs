using System;
using System.Collections.Generic;

namespace _07.Snakes
{
    class Snakes
    {
        private static List<char> snake;
        private static List<string> allSnakes;
        private static int snakeCount;

        static void Main(string[] args)
        {
            var blocks = 4;
            snake = new List<char>();
            snake.Add('S');
            allSnakes = new List<string>();
            snakeCount = 0;

            GenerateSnake(blocks - 1, 'R');
            PrintSnakes();
            Console.WriteLine(snakeCount);
        }

        private static void PrintSnakes()
        {
            foreach (var snake in allSnakes)
            {
                Console.WriteLine(snake);
            }
        }

        private static void GenerateSnake(int blocks, char direction)
        {
            snake.Add(direction);
            if (blocks <= 1)
            {
                
                if (FoundNewSnake())
                {
                    string newSnake = string.Join("", snake);
                    allSnakes.Add(newSnake);
                    snakeCount++;
                }
              
                snake.RemoveAt(snake.Count - 1);
                
                return;
            }
            if (direction == 'R')
            {
                GenerateSnake(blocks - 1, 'R');
                GenerateSnake(blocks - 1, 'D');
                GenerateSnake(blocks - 1, 'U');
            }
            else if (direction == 'D')
            {
                GenerateSnake(blocks - 1, 'R');
                GenerateSnake(blocks - 1, 'D');
                GenerateSnake(blocks - 1, 'L');
            }
            else if (direction == 'L')
            {
                GenerateSnake(blocks - 1, 'D');
                GenerateSnake(blocks - 1, 'L');
                GenerateSnake(blocks - 1, 'U');
            }
            else
            {
                GenerateSnake(blocks - 1, 'R');
                GenerateSnake(blocks - 1, 'L');
                GenerateSnake(blocks - 1, 'U');
            }

            snake.RemoveAt(snake.Count - 1);
            
        }

        private static bool FoundNewSnake()
        {
            bool hasFound = true;
            foreach (var currentSnake in allSnakes)
            {
                List<char> copyOfSnake = new List<char>();
                string newSnake = "";
                for (int i = 0; i < snake.Count; i++)
                {
                    if (snake[i] == 'R')
                    {
                        copyOfSnake.Add('L');
                    }
                    else if (snake[i] == 'L')
                    {
                        copyOfSnake.Add('R');
                    }
                    else
                    {
                        copyOfSnake.Add(snake[i]);
                    }
                }
                newSnake = string.Join("", copyOfSnake);
                if (currentSnake == newSnake)
                {
                    hasFound = false;
                    break;
                }
                copyOfSnake.Clear();
                for (int i = 0; i < snake.Count; i++)
                {
                    if (snake[i] == 'D')
                    {
                        copyOfSnake.Add('U');
                    }
                    else if (snake[i] == 'U')
                    {
                        copyOfSnake.Add('D');
                    }
                    else
                    {
                        copyOfSnake.Add(snake[i]);
                    }
                }
                newSnake = string.Join("", copyOfSnake);
                if (currentSnake == newSnake)
                {
                    hasFound = false;
                    break;
                }
            }
            return hasFound;
        }
    }
}
