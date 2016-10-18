using System;

class ChessboardGame
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        string userInput = Console.ReadLine().PadRight((n * n) + 1, ' ');
        
        string userMessage = userInput.Substring(0, (n * n)); //if string is loger than n * n!!!!!!!!!!!
        char[] charArray = userMessage.ToCharArray();

        int[] messageArray = new int[charArray.Length];

        for (int i = 0; i < messageArray.Length; i++)
        {
            messageArray[i] = (int)charArray[i];
        }

        int[] unicodeArray = new int[messageArray.Length];

        for (int i = 0; i < unicodeArray.Length; i++)
        {
            if (messageArray[i] >= 48 && messageArray[i] <= 57)
            {
                unicodeArray[i] = messageArray[i];
            }
            else if (messageArray[i] >= 65 && messageArray[i] <= 90)
            {
                unicodeArray[i] = messageArray[i];
            }
            else if (messageArray[i] >= 97 && messageArray[i] <= 122)
            {
                unicodeArray[i] = messageArray[i];
            }
        }

        int whiteTeam = 0;
        int blackTeam = 0;
        for (int i = 0; i < unicodeArray.Length; i++)
        {
            if (i % 2 == 0 && !(unicodeArray[i] >= 65 && unicodeArray[i] <= 90))
            {
                blackTeam += unicodeArray[i];
            }
            else if (i % 2 != 0 && !(unicodeArray[i] >= 65 && unicodeArray[i] <= 90))
            {
                whiteTeam += unicodeArray[i];
            }
            else if (i % 2 == 0 && (unicodeArray[i] >= 65 && unicodeArray[i] <= 90))
            {
                whiteTeam += unicodeArray[i];
            }
            else if (i % 2 != 0 && (unicodeArray[i] >= 65 && unicodeArray[i] <= 90))
            {
                blackTeam += unicodeArray[i];
            }
        }

        if (blackTeam > whiteTeam)
        {
            Console.WriteLine("The winner is: black team");
            Console.WriteLine(blackTeam - whiteTeam);
        }
        else if (whiteTeam > blackTeam)
        {
            Console.WriteLine("The winner is: white team");
            Console.WriteLine(whiteTeam - blackTeam);
        }
        else if (blackTeam == whiteTeam)
        {
            Console.WriteLine("Equal result: " + blackTeam);
        }
    }
}

