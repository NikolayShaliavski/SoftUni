using System;
using System.Collections.Generic;
using System.Threading;

struct Car //struct, which creates cars
{
    public int x;
    public int y;
    public ConsoleColor color;
    public string symbol;
}

class JustCarsNikiVideoTelerik
{
    public static void PrintOnPosition(int x, int y, string c, ConsoleColor color = ConsoleColor.Gray) //method, which prints cars
    {
        Console.SetCursorPosition(y, x);
        Console.ForegroundColor = color;
        Console.Write(c);
    }

    static void Main()
    {
        for (int i = 0; i < Console.WindowHeight; i++) //drawing playfield
        {
            PrintOnPosition(i, 10, "|", ConsoleColor.DarkRed);

        }

        Car myCar = new Car(); //creating my car
        myCar.x = Console.WindowHeight - 1;
        myCar.y = 10;
        myCar.symbol = "A";
        myCar.color = ConsoleColor.DarkRed;

        Random racerPosition = new Random();
        List<Car> racers = new List<Car>();
        int lives = 5;
        int points = 0;
        int counter = 0;
        int playfield = 20;
        int speed = 200;

        while (true)
        {
            for (int i = 0; i < Console.WindowHeight; i++) //drawing playfield again
            {
                PrintOnPosition(i, playfield, "|", ConsoleColor.DarkRed);
            }

            PrintOnPosition(5, 40, "POINTS " + points, ConsoleColor.Green);
            PrintOnPosition(7, 40, "LIVES " + lives, ConsoleColor.Green);

            counter++;
            if (counter % 3 == 0)
            {
                Car racer = new Car(); //on every THIRD iteration we create new racer and increasing speed
                racer.x = 0;
                racer.y = racerPosition.Next(0, playfield);
                racer.symbol = "#";
                racer.color = ConsoleColor.White;
                racers.Add(racer);
                if (speed > 0)
                {
                    speed--;
                } 
            }
            
            foreach (Car car in racers) //printing racers on the console
            {
                PrintOnPosition(car.x, car.y, car.symbol, car.color);
            }

            List<Car> newRacers = new List<Car>();//new list to add new racers

            for (int i = 0; i < racers.Count; i++)
            {
                Car newCar = new Car();
                newCar.x = racers[i].x + 1;
                newCar.y = racers[i].y;
                newCar.symbol = racers[i].symbol;
                newCar.color = racers[i].color;
                if (newCar.x < Console.WindowHeight)
                {
                    newRacers.Add(newCar); //adding new racers
                }
            }

            racers = newRacers; // updating old list

            for (int i = 0; i < racers.Count; i++)
            {
                if (racers[i].y == myCar.y && racers[i].x == myCar.x)//checking if we crashed
                {
                    lives--;
                    racers.Clear();
                    speed = 200;
                }
            }

            if (Console.KeyAvailable) //moving my car
            {
                ConsoleKeyInfo arrow = Console.ReadKey();
                if (arrow.Key == ConsoleKey.LeftArrow && myCar.y > 0)
                {
                    myCar.y--;
                }
                if (arrow.Key == ConsoleKey.RightArrow && myCar.y < playfield - 1)
                {
                    myCar.y++;
                }
                while (Console.KeyAvailable)
                {
                    Console.ReadKey(true);
                }
            }

            PrintOnPosition(myCar.x, myCar.y, myCar.symbol, myCar.color); //drawing my car
            Thread.Sleep(speed);
            Console.Clear();
            points++;

            if (lives == 0)
            {
                PrintOnPosition(10, 26, "GAME OVER", ConsoleColor.Red);
                PrintOnPosition(12, 20, "YOUR SCORE IS " + points + " POINTS", ConsoleColor.Red);
                Console.WriteLine();
                return;
            }
        }
    }
}
