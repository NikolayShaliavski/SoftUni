using System;

class KingOfThieves
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        char symbol = char.Parse(Console.ReadLine());

        char lines = '-';

        int symbolCounter = 1;
        int linesCounter = (n / 2);
        for (int i = 0; i < (n / 2); i++)
        {
            Console.WriteLine("{0}{1}{0}", new string(lines, linesCounter), new string(symbol, symbolCounter));
            linesCounter--;
            symbolCounter += 2;
        }

        Console.Write("{0}", new string(symbol, n));
        Console.WriteLine();

        symbolCounter = (n - 2);
        linesCounter = 1;
        for (int i = 0; i < (n / 2); i++)
        {
            Console.WriteLine("{0}{1}{0}", new string(lines, linesCounter), new string(symbol, symbolCounter));
            symbolCounter -= 2;
            linesCounter++;
        }
    }
}

