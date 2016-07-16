using System;

class BasketBattle
{
    static void Main()
    {
        string firstShooting = Console.ReadLine();
        int numberOfRounds = int.Parse(Console.ReadLine());

        int resultSimeon = 0;
        int resultNakov = 0;

        switch (firstShooting)
        {
            case "Simeon":
                for (int i = 1; i <= numberOfRounds; i++)
                {
                    if (i % 2 != 0)
                    {
                        int points = int.Parse(Console.ReadLine());
                        string info = Console.ReadLine();
                        if (info == "success" && (points + resultSimeon) <= 500)
                        {
                            resultSimeon += points;
                        }
                        if (resultSimeon == 500)
                        {
                            Console.WriteLine("Simeon");
                            Console.WriteLine(i);
                            Console.WriteLine(resultNakov); return;
                        }

                        int points2 = int.Parse(Console.ReadLine());
                        string info2 = Console.ReadLine();
                        if (info2 == "success" && (points2 + resultNakov) <= 500)
                        {
                            resultNakov += points2;
                        }
                        if (resultNakov == 500)
                        {
                            Console.WriteLine("Nakov");
                            Console.WriteLine(i);
                            Console.WriteLine(resultSimeon); return;
                        }
                    }
                    else
                    {
                        int points = int.Parse(Console.ReadLine());
                        string info = Console.ReadLine();
                        if (info == "success" && (points + resultNakov) <= 500)
                        {
                            resultNakov += points;
                        }
                        if (resultNakov == 500)
                        {
                            Console.WriteLine("Nakov");
                            Console.WriteLine(i);
                            Console.WriteLine(resultSimeon); return;
                        }

                        int points2 = int.Parse(Console.ReadLine());
                        string info2 = Console.ReadLine();
                        if (info2 == "success" && (points2 + resultSimeon) <= 500)
                        {
                            resultSimeon += points2;
                        }
                        if (resultSimeon == 500)
                        {
                            Console.WriteLine("Simeon");
                            Console.WriteLine(i);
                            Console.WriteLine(resultNakov); return;
                        }
                    }
                }
                if (resultSimeon == resultNakov)
                {
                    Console.WriteLine("DRAW");
                    Console.WriteLine(resultSimeon);
                }
                else if ((resultSimeon > resultNakov) && (resultSimeon < 500))
                {
                    Console.WriteLine("Simeon");
                    Console.WriteLine(resultSimeon - resultNakov);
                }
                else if ((resultNakov > resultSimeon) && (resultNakov < 500))
                {
                    Console.WriteLine("Nakov");
                    Console.WriteLine(resultNakov - resultSimeon);
                }
                break;

            case "Nakov":
                for (int i = 1; i <= numberOfRounds; i++)
                {
                    if (i % 2 != 0)
                    {
                        int points = int.Parse(Console.ReadLine());
                        string info = Console.ReadLine();
                        if (info == "success" && (points + resultNakov) <= 500)
                        {
                            resultNakov += points;
                        }
                        if (resultNakov == 500)
                        {
                            Console.WriteLine("Nakov");
                            Console.WriteLine(i);
                            Console.WriteLine(resultSimeon); return;
                        }

                        int points2 = int.Parse(Console.ReadLine());
                        string info2 = Console.ReadLine();
                        if (info2 == "success" && (points2 + resultSimeon) <= 500)
                        {
                            resultSimeon += points2;
                        }
                        if (resultSimeon == 500)
                        {
                            Console.WriteLine("Simeon");
                            Console.WriteLine(i);
                            Console.WriteLine(resultNakov); return;
                        }
                    }
                    else
                    {
                        int points = int.Parse(Console.ReadLine());
                        string info = Console.ReadLine();
                        if (info == "success" && (points + resultSimeon) <= 500)
                        {
                            resultSimeon += points;
                        }
                        if (resultSimeon == 500)
                        {
                            Console.WriteLine("Simeon");
                            Console.WriteLine(i);
                            Console.WriteLine(resultNakov); return;
                        }

                        int points2 = int.Parse(Console.ReadLine());
                        string info2 = Console.ReadLine();
                        if (info2 == "success" && (points2 + resultNakov) <= 500)
                        {
                            resultNakov += points2;
                        }
                        if (resultNakov == 500)
                        {
                            Console.WriteLine("Nakov");
                            Console.WriteLine(i);
                            Console.WriteLine(resultSimeon); return;
                        }
                    }
                }
                if (resultSimeon == resultNakov)
                {
                    Console.WriteLine("DRAW");
                    Console.WriteLine(resultSimeon);
                }
                else if ((resultSimeon > resultNakov) && (resultSimeon < 500))
                {
                    Console.WriteLine("Simeon");
                    Console.WriteLine(resultSimeon - resultNakov);
                }
                else if ((resultNakov > resultSimeon) && (resultNakov < 500))
                {
                    Console.WriteLine("Nakov");
                    Console.WriteLine(resultNakov - resultSimeon);
                }
                break;
            default:
                Console.WriteLine("Wrong Input. Try again!!!"); break;
        }
    }
}


