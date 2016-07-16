using System;

class ProgrammerDNA
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());
        char letter = char.Parse(Console.ReadLine());
        int dotCount = 0;
        int letterCount = 0;

        for (int a = 0; a < n / 7; a++)
        {
            dotCount = 3;
            letterCount = 1;
            for (int b = 0; b < 4; b++)
            {
                Console.Write("{0}", new string('.', dotCount));

                for (int c = 0; c < letterCount; c++)
                {
                    if (letter == 'H')
                    {
                        letter = 'A';
                    }
                    Console.Write(letter++);
                }

                Console.WriteLine("{0}", new string('.', dotCount));
                dotCount--;
                letterCount += 2;
            }
            dotCount = 1;
            letterCount = 5;

            for (int b = 0; b < 3; b++)
            {
                Console.Write("{0}", new string('.', dotCount));

                for (int c = 0; c < letterCount; c++)
                {
                    if (letter == 'H')
                    {
                        letter = 'A';
                    }
                    Console.Write(letter++);
                }

                Console.WriteLine("{0}", new string('.', dotCount));
                dotCount++;
                letterCount -= 2;
            }
        }

        int moreRows = n % 7;
        dotCount = 3;
        letterCount = 1;

        if (moreRows <= 4)
        {
            for (int b = 0; b < moreRows; b++)
            {
                Console.Write("{0}", new string('.', dotCount));

                for (int c = 0; c < letterCount; c++)
                {
                    if (letter == 'H')
                    {
                        letter = 'A';
                    }
                    Console.Write(letter++);
                }

                Console.WriteLine("{0}", new string('.', dotCount));
                dotCount--;
                letterCount += 2;
            }
        }
        else
        {
            for (int b = 0; b < 4; b++)
            {
                Console.Write("{0}", new string('.', dotCount));

                for (int c = 0; c < letterCount; c++)
                {
                    if (letter == 'H')
                    {
                        letter = 'A';
                    }
                    Console.Write(letter++);
                }

                Console.WriteLine("{0}", new string('.', dotCount));
                dotCount--;
                letterCount += 2;
            }
            dotCount = 1;
            letterCount = 5;

            for (int b = 0; b < moreRows % 4; b++)
            {
                Console.Write("{0}", new string('.', dotCount));

                for (int c = 0; c < letterCount; c++)
                {
                    if (letter == 'H')
                    {
                        letter = 'A';
                    }
                    Console.Write(letter++);
                }

                Console.WriteLine("{0}", new string('.', dotCount));
                dotCount++;
                letterCount -= 2;
            }
        }
    }
}
