using SnakeGame.Models;
using System;
using System.Threading;

namespace SnakeGame
{
    class SnakeMain
    {
        static Snake snake;
        static Point food;
        static int sleepTime;
        static int width;
        static int height;
        static Direction direction;
        static bool alive;

        static void Main(string[] args)
        {
            snake = new Snake();
            sleepTime = 80;
            width = Console.WindowWidth;
            height = Console.WindowHeight;
            direction = Direction.Right;
            food = GenerateRandomFoodPoint();
            alive = true;
            Console.CursorVisible = false;

            while(true)
            {
                Thread.Sleep(sleepTime);
                Update();
                Render();
                if (!alive)
                {
                    break;
                }
            }
            Console.SetCursorPosition(0, 40);
            Console.WriteLine("Game Over!!!!!!!!!!!!!!!!!!!!");
        }
        static void Update()
        {
            if (Console.KeyAvailable)
            {
                ConsoleKey pressed = Console.ReadKey().Key;
                if (pressed == ConsoleKey.LeftArrow)
                {
                    if (direction != Direction.Left &&
                        direction != Direction.Right)
                    {
                        direction = Direction.Left;
                    }
                }
                else if (pressed == ConsoleKey.UpArrow)
                {
                    if (direction != Direction.Up &&
                        direction != Direction.Down)
                    {
                        direction = Direction.Up;
                    }
                }
                else if (pressed == ConsoleKey.RightArrow)
                {
                    if (direction != Direction.Left &&
                        direction != Direction.Right)
                    {
                        direction = Direction.Right;
                    }
                }
                else if (pressed == ConsoleKey.DownArrow)
                {
                    if (direction != Direction.Up &&
                        direction != Direction.Down)
                    {
                        direction = Direction.Down;
                    }
                }
            }
            bool foodEaten = snake.Move(direction, food, width, height);
            if (foodEaten)
            {
                food = GenerateRandomFoodPoint();
            }
            alive = snake.IsAlive(width, height);
        }
        static void Render()
        {
            Console.Clear();
            foreach (var point in snake.Body)
            {
                Console.ForegroundColor = ConsoleColor.White;
                PrintOnPosition(point);
            }
            if (food != null)
            {
                Console.ForegroundColor = ConsoleColor.Green;
                PrintOnPosition(food);
            }
        }
        private static Point GenerateRandomFoodPoint()
        {
            Random rnd = new Random();
            int x = rnd.Next(width - 1);
            int y = rnd.Next(height - 1);
            while (snake.ContainsPoint(x, y))
            {
                x = rnd.Next(width - 1);
                y = rnd.Next(height - 1);
            }
            Point food = new Point()
            {
                Symbol = Constants.Food,
                X = x,
                Y = y
            };
            return food;
        }
        private static void PrintOnPosition(Point point)
        {
            Console.SetCursorPosition(point.X, point.Y);
            Console.Write(point.Symbol);
        }
    }
}
