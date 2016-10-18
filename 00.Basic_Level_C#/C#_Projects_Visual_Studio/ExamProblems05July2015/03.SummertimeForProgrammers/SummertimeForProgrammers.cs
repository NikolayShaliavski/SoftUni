using System;

class SummertimeForProgrammers
{
    static void Main()
    {
        int size = int.Parse(Console.ReadLine());
        int starsCount = size + 1;
        int spacesCount = ((2 * size) - starsCount) / 2;
        int middleSpace = size - 1;

        Console.WriteLine("{0}{1}{0}", new string(' ', spacesCount), new string('*', starsCount));

        for (int i = 0; i < size / 2 + 1; i++)
        {
            Console.WriteLine("{0}*{1}*{0}", new string(' ', spacesCount), new string(' ', middleSpace));
        }

        for (int i = 0; i < size / 2 - 1; i++)
        {
            spacesCount--;
            middleSpace += 2;
            Console.WriteLine("{0}*{1}*{0}", new string(' ', spacesCount), new string(' ', middleSpace));
        }

        middleSpace += 2;
        for (int i = 0; i < size; i++)
        {
            Console.WriteLine("*{0}*", new string('.', middleSpace));
        }

        for (int i = 0; i < size; i++)
        {
            Console.WriteLine("*{0}*", new string('@', middleSpace));
        }

        Console.WriteLine("{0}", new string('*', 2 * size));
    }
}
