using System;

class Gambling
{
    private static int i;

    static void Main()
    {
        double cash = double.Parse(Console.ReadLine());
        string housesHand = Console.ReadLine();

        string[] hand = housesHand.Split();
        int card = 0;
        int strength = 0;

        for (int i = 0; i < hand.Length; i++)
        {
            if (int.TryParse(hand[i], out card))
            {
                strength += card;
            }
            else
            {
                switch (hand[i])
                {
                    case "J":
                        strength += 11;
                        break;
                    case "Q":
                        strength += 12;
                        break;
                    case "K":
                        strength += 13;
                        break;
                    case "A":
                        strength += 14;
                        break;
                    default:
                        break;
                }
            }
        }
        double allHands = 0;
        double powerHands = 0;
        int currentHand = 0;

        for (int a = 2; a <= 14; a++)
        {
            for (int b = 2; b <= 14; b++)
            {
                for (int c = 2; c <= 14; c++)
                {
                    for (int d = 2; d <= 14; d++)
                    {
                        allHands++;
                        currentHand = (a + b + c + d);
                        if (currentHand > strength)
                        {
                            powerHands++;
                        }
                    }
                }
            }
        }

        double probability = (powerHands / allHands) * 100;
        double winning = (2 * cash * probability) / 100;

        if (probability < 50)
        {
            Console.WriteLine("FOLD");
            Console.WriteLine("{0:0.00}", winning);
        }
        else
        {
            Console.WriteLine("DRAW");
            Console.WriteLine("{0:0.00}", winning);
        }
    }
}