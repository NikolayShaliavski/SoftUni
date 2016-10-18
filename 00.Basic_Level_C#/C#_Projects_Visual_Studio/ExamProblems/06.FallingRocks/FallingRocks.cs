using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

struct Items
{
    public int x;
    public int y;
    public string c;
    public ConsoleColor color;
}
class FallingRocks
{
    static void PrintOnPosition(int x, int y, string c, ConsoleColor color = ConsoleColor.Gray)
    {
        Console.SetCursorPosition(x, y);
        Console.ForegroundColor = color;
        Console.Write(c);
    }

    static void Main()
    {
        // Changing the size of the Console, removing Console buffer and making our playfield
        Console.BufferHeight = Console.WindowHeight = 30;
        Console.BufferWidth = Console.WindowWidth = 50;
        int playfieldWidth = Console.WindowWidth - 20;

        // Creating our player
        Items ourPlayer = new Items();
        ourPlayer.color = ConsoleColor.White;
        ourPlayer.x = playfieldWidth / 2;
        ourPlayer.y = Console.WindowHeight - 1;
        ourPlayer.c = "(0)";
        
        // Creating randomgenerator
        Random randomGenerator = new Random();

        // Creating list of rocks
        List<Items> listOfRocks = new List<Items>();

        while (true)
        {
            // Creating rocks
            {
                Items rock = new Items();
                rock.color = ConsoleColor.Blue;
                rock.x = randomGenerator.Next(0, playfieldWidth);
                rock.y = 0;
                rock.c = "@";
                listOfRocks.Add(rock);
            }

            // Move our Player(key pressed)
            if (Console.KeyAvailable)
            {
                ConsoleKeyInfo pressedKey = Console.ReadKey(true);
                while (Console.KeyAvailable)
                {
                    Console.ReadKey(true);
                }
                if (pressedKey.Key == ConsoleKey.LeftArrow)
                {
                    if (ourPlayer.x - 1 >= 0)
                    {
                        ourPlayer.x--;
                    }
                }
                if (pressedKey.Key == ConsoleKey.RightArrow)
                {
                    if (ourPlayer.x + 1 < playfieldWidth)
                    {
                        ourPlayer.x++;
                    }
                }
            }
            // Move items
            List<Items> newListOfRocks = new List<Items>();
            for (int i = 0; i < listOfRocks.Count; i++)
            {
                Items oldRock = listOfRocks[i];
                Items rock = new Items();
                rock.x = oldRock.x;
                rock.y = oldRock.y + 1;
                rock.color = oldRock.color;
                if (rock.y < Console.WindowHeight)
                {
                    listOfRocks.Add(rock);
                }
                newListOfRocks.Add(rock);
            }
            listOfRocks = newListOfRocks;   
            // Check if rocks are hitting us
            // Clear the console
            Console.Clear();
            // Redraw playfield
            PrintOnPosition(ourPlayer.x, ourPlayer.y, ourPlayer.c, ourPlayer.color);
            foreach (Items rock in newListOfRocks)
            {
                PrintOnPosition(rock.x, rock.y, rock.c, rock.color);
            }
            // Draw info
            // Slowing movement 
            Thread.Sleep(400);
        }
    }
}


