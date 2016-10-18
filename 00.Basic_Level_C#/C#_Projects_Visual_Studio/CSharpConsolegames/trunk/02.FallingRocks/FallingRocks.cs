using System;
using System.Collections.Generic;
using System.Threading;

struct PrintRocks
{
    public int x;
    public int y;
    public string symbol;
    public ConsoleColor color;
}

class FallingRocks
{
    public static void PrintOnPosition(int x, int y, string symbol, ConsoleColor color)//method to print items on the console
    {
        Console.SetCursorPosition(y, x);
        Console.ForegroundColor = color;
        Console.Write(symbol);
    }

    static void Main()
    {
        string[] symbols = new string[] { "&", "@", "#", "%", "$" };//array with falling rocks
        string[] dwarfBody = { "=", "O", "=" };//array with dwarf items

        List<PrintRocks> rocks = new List<PrintRocks>();//list with rocks positions
        List<PrintRocks> dwarf = new List<PrintRocks>();//list with dwarf positions
        //List<PrintRocks> bonuses = new List<PrintRocks>();

        Random rnd = new Random();
        int playfield = Console.WindowWidth - 20;
        int count = 0;
        int points = 0;
        int lives = 5;
        int speed = 200;

        for (int i = 0; i < 3; i++)  //creating our dwarf
        {
            PrintRocks player = new PrintRocks();
            player.x = Console.WindowHeight - 1;
            player.y = 22 + i; 
            player.color = ConsoleColor.Red;
            dwarf.Add(player);
        }

        while (true)
        {
            count++;//counting iterations
            for (int i = 0; i < Console.WindowHeight; i++)
            {
                PrintOnPosition(i, playfield, "|", ConsoleColor.Red);//drawing playfield 
            }

            if (count % 3 == 0)
            {
                PrintRocks rock = new PrintRocks();
                rock.x = 0;
                rock.y = rnd.Next(0, playfield);
                rock.symbol = symbols[rnd.Next(0, 4)];
                rock.color = ConsoleColor.Blue;
                rocks.Add(rock);
                if (speed > 0)
                {
                    speed--;//increasing speed
                }
            }

            if (count % 10 == 0)
            {
                PrintRocks bonus = new PrintRocks();
                bonus.x = 0;
                bonus.y = rnd.Next(0, playfield);
                bonus.symbol = "B";
                bonus.color = ConsoleColor.Magenta;
                rocks.Add(bonus);
            }

            foreach (PrintRocks rock in rocks)
            {
                PrintOnPosition(rock.x, rock.y, rock.symbol, rock.color); //drawing falling rocks
            }
            List<PrintRocks> newRocks = new List<PrintRocks>();

            for (int i = 0; i < rocks.Count; i++)
            {
                PrintRocks newRock = new PrintRocks();
                newRock.x = rocks[i].x + 1;//moving rocks down
                newRock.y = rocks[i].y;
                newRock.symbol = rocks[i].symbol;
                newRock.color = rocks[i].color;

                if (newRock.x < Console.WindowHeight)
                {
                    newRocks.Add(newRock);//adding every new rock to new list
                }
            }
            rocks = newRocks;//refreshing old list with new rocks

            for (int i = 0; i < rocks.Count; i++)
            {
                for (int j = 0; j < dwarf.Count; j++)
                {
                    if (rocks[i].x == dwarf[j].x && rocks[i].y == dwarf[j].y && rocks[i].symbol != "B")//checking if our dwarf if hitted from rock
                    {
                        lives--;
                        speed = 200;
                        rocks.Clear();
                        break;
                    }
                    else if (rocks[i].x == dwarf[j].x && rocks[i].y == dwarf[j].y && rocks[i].symbol == "B")//checking if we have caught bonus
                    {
                        points += 10;
                    }
                }
            }

            if (Console.KeyAvailable)
            {
                ConsoleKeyInfo arrow = Console.ReadKey();
                if (arrow.Key == ConsoleKey.LeftArrow && dwarf[0].y > 0)
                {
                    List<PrintRocks> newDwarf = new List<PrintRocks>();
                    for (int i = 0; i < dwarf.Count; i++)     //moving our dwarf with loop, because it consists three elements -> left
                    {
                        PrintRocks newPlayer = new PrintRocks();
                        newPlayer.x = dwarf[i].x;
                        newPlayer.y = dwarf[i].y - 2;
                        newPlayer.color = dwarf[i].color;
                        newDwarf.Add(newPlayer);//adding every item of dwars in new list with dwarf positions -> only y -= 1
                    }
                    dwarf = newDwarf;
                }
                if (arrow.Key == ConsoleKey.RightArrow && dwarf[2].y < playfield - 1)
                {
                    List<PrintRocks> newDwarf = new List<PrintRocks>();
                    for (int i = 0; i < dwarf.Count; i++)    // ........-> right
                    {
                        PrintRocks newPlayer = new PrintRocks();
                        newPlayer.x = dwarf[i].x;
                        newPlayer.y = dwarf[i].y + 2;
                        newPlayer.color = dwarf[i].color;
                        newDwarf.Add(newPlayer);//......... -> only y += 1
                    }
                    dwarf = newDwarf;
                }
                while (Console.KeyAvailable)
                {
                    Console.ReadKey(true);
                }
            }

            
            for (int i = 0; i < dwarf.Count; i++)//drawing our dwarf
            {
                PrintOnPosition(dwarf[i].x, dwarf[i].y, dwarfBody[i], dwarf[i].color);
            }
            PrintOnPosition(5, 53, "POINTS " + points, ConsoleColor.DarkGreen);
            PrintOnPosition(7, 53, "LIVES " + lives, ConsoleColor.DarkGreen);
            if (lives == 0)
            {
                PrintOnPosition(9, 53, "GAME OVER", ConsoleColor.Red);
                Console.WriteLine();
                return;
            }
            Thread.Sleep(speed);
            Console.Clear();
        }
    }
}
