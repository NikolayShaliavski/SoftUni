using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;

struct Position //struct, where are coordinates of snake
{
    public int row;
    public int col;
    public Position(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
}

class Snake
{
    static void Main()
    {
        byte right = 0;
        byte left = 1;
        byte down = 2;
        byte up = 3;

        Position[] directions = new Position[]
        {
            new Position(0, 1),  //right
            new Position(0, -1),  //left
            new Position(1, 0),  //down
            new Position(-1, 0),  //up
        };

        int direction = right;
        int sleep = 100;
        int points = 0;

        Console.BufferHeight = Console.WindowHeight;
        Console.ForegroundColor = ConsoleColor.DarkCyan;

        Random foodPosition = new Random();  //creating food for snake on a random position
        Position food = new Position(foodPosition.Next(0, Console.WindowHeight), foodPosition.Next(0, Console.WindowWidth - 19));

        Queue<Position> snake = new Queue<Position>();
        for (int i = 0; i < 6; i++)
        {
            snake.Enqueue(new Position(0, i));  // creating snake body -> * * * * * @ -> 6 elements
        }

        foreach (Position position in snake)  // printing snake body on console
        {
            Console.SetCursorPosition(position.col, position.row);
            Console.Write("*");
        }

        for (int i = 0; i < Console.WindowHeight; i++)
        {
            Console.SetCursorPosition(Console.WindowWidth - 20, i);
            Console.WriteLine("|");//drawing playfield
        }

        Console.SetCursorPosition(Console.WindowWidth - 15, Console.WindowHeight / 2 - 5);
        Console.Write("POINTS: " + points);//drawing points

        while (true)
        {
            if (Console.KeyAvailable)
            {
                ConsoleKeyInfo arrow = Console.ReadKey();  //taking user input -> keyboard arrows
                if (arrow.Key == ConsoleKey.RightArrow)
                {
                    if (direction != left) direction = right;//if snake is moving left -> it can't go to right
                }
                if (arrow.Key == ConsoleKey.LeftArrow)
                {
                    if (direction != right) direction = left;//........
                }
                if (arrow.Key == ConsoleKey.DownArrow)
                {
                    if (direction != up) direction = down;//..........
                }
                if (arrow.Key == ConsoleKey.UpArrow)
                {
                    if (direction != down) direction = up;//............
                }
            }

            Position snakeHead = snake.Last(); // taking last added element in snake(first in snakes body) -> snake head    
            Console.SetCursorPosition(snakeHead.col, snakeHead.row);
            Console.ForegroundColor = ConsoleColor.DarkCyan;
            Console.Write("*");  //redrawing it

            Position nextDirection = directions[direction]; //which direction to move snake. It depends of user input
            Position newSnakeHead = new Position(snakeHead.row + nextDirection.row, snakeHead.col + nextDirection.col); //new position of new snake head

            if (newSnakeHead.row < 0 || newSnakeHead.col < 0 || newSnakeHead.row == Console.WindowHeight || //crashing with snake
                newSnakeHead.col == Console.WindowWidth - 20 || snake.Contains(newSnakeHead))
            {
                Console.SetCursorPosition(Console.WindowWidth - 15, Console.WindowHeight / 2 - 1);
                Console.ForegroundColor = ConsoleColor.Red;
                Console.WriteLine("GAME OVER");
                return;
            }

            snake.Enqueue(newSnakeHead);//adding new snake head 
            Console.SetCursorPosition(newSnakeHead.col, newSnakeHead.row);
            Console.ForegroundColor = ConsoleColor.DarkCyan;
            Console.Write("@");//drawing it

            if (newSnakeHead.col == food.col && newSnakeHead.row == food.row) //if snake eats -> it grows, so we don't delete last element in snake
            {
                do
                {
                    food = new Position(foodPosition.Next(0, Console.WindowHeight), foodPosition.Next(0, Console.WindowWidth - 21));

                }
                while (snake.Contains(food));
                sleep--;//increasing speed
                points += 10;//adding points
                Console.SetCursorPosition(Console.WindowWidth - 15, Console.WindowHeight / 2 - 5);
                Console.ForegroundColor = ConsoleColor.DarkCyan;
                Console.Write("POINTS: " + points);//redrawing points
            }
            else
            {
                Position last = snake.Dequeue();  //deleting first added element in snake -> the end of the body if snake doesn't eat
                Console.SetCursorPosition(last.col, last.row);
                Console.Write(" ");
            }

            Console.SetCursorPosition(food.col, food.row);
            Console.ForegroundColor = ConsoleColor.White;
            Console.Write("$");//printing food                          

            Thread.Sleep(sleep);//making console slow down
        }
    }
}

