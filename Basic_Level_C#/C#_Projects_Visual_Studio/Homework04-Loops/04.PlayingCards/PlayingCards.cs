using System;

class PlayingCards
{
    static void Main()
    {
        string[] cardArray = new string[]
        {
            "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"
        };

        for (int i = 0; i < cardArray.Length; i++)
        {
            for (int j = 1; j <= 4; j++)
            {
                switch (j)
                {
                    case 1:
                        Console.Write("{0, 2}{1, -4}", cardArray[i], '\u2660');
                        break;
                    case 2:
                        Console.Write("{0, 2}{1, -4}", cardArray[i], '\u2666');
                        break;
                    case 3:
                        Console.Write("{0, 2}{1, -4}", cardArray[i], '\u2665');
                        break;
                    case 4:
                        Console.Write("{0, 2}{1, -4}", cardArray[i], '\u2663');
                        break;
                    default:
                        break; 
                }
            }
            Console.WriteLine();
        }
    }
}
