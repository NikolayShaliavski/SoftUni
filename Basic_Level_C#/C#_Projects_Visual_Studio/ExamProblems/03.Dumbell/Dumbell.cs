using System;

class Dumbell
{
    static void Main()
    {
        int height = int.Parse(Console.ReadLine());

        string dot = new string('.', height / 2);
        string middleDot = new string('.', height);
        string ampersand = new string('&', (height / 2) + 1);
        string equal = new string('=', height);

        Console.WriteLine(dot + ampersand + middleDot + ampersand + dot);

        for (int i = 1; i < height / 2; i++)
        {
            dot = new string('.', (height / 2) - i);
            string asteriks = new string('*', (height / 2) + i - 1);

            Console.WriteLine(dot + '&' + asteriks + '&' + middleDot + '&' + asteriks + '&' + dot);
        }

        string middleAsteriks = new string('*', height - 2);

        Console.WriteLine('&' + middleAsteriks + '&' + equal + '&' + middleAsteriks + '&');

        for (int i = height / 2; i > 1; i--)
        {
            dot = new string('.', (height / 2) - i + 1);
            string asteriks = new string('*', (height / 2) + i - 2);

            Console.WriteLine(dot + '&' + asteriks + '&' + middleDot + '&' + asteriks + '&' + dot);
        }

        string dot1 = new string('.', height / 2);
        Console.WriteLine(dot1 + ampersand + middleDot + ampersand + dot1);
    }
}