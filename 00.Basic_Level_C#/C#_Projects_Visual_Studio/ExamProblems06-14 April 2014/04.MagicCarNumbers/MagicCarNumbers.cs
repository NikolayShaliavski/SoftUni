using System;
using System.Collections.Generic;

class MagicCarNumbers
{
    static void Main()
    {
        int magic = int.Parse(Console.ReadLine());

        List<int> first = new List<int>()
            {10, 20, 30, 50, 80, 110, 130, 160, 200, 240 };
        List<int> second = new List<int>()
            {10, 20, 30, 50, 80, 110, 130, 160, 200, 240 };

        int count = 0;
        int current = 0;

        for (int a = 0; a <= 9; a++)
        {
            for (int b = 0; b <= 9; b++)
            {
                for (int c = 0; c <= 9; c++)
                {
                    for (int d = 0; d <= 9; d++)
                    {
                        for (int e = 0; e < first.Count; e++)
                        {
                            for (int f = 0; f < second.Count; f++)
                            {
                                current = (a + b + c + d + first[e] + second[f] + 40);

                                if (a == b && b == c && c == d && current == magic)
                                {
                                    count++;
                                }
                                else if (a == b && b == c && current == magic)
                                {
                                    count++;
                                }
                                else if (b == c && c == d && current == magic)
                                {
                                    count++;
                                }
                                else if (a == b && c == d && current == magic)
                                {
                                    count++;
                                }
                                else if (a == c && b == d && current == magic)
                                {
                                    count++;
                                }
                                else if (a == d && b == c && current == magic)
                                {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        Console.WriteLine(count);
    }
}
