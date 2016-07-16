using System;

class DoubleDowns
{
    static void Main()
    {
        int n = int.Parse(Console.ReadLine());

        int number1 = int.Parse(Console.ReadLine());

        int vertical = 0;
        int right = 0;
        int left = 0;

        for (int i = 0; i < n - 1; i++)
        {
            int number2 = int.Parse(Console.ReadLine());
       
            int current1 = 0;
            int current2 = 0;

            for (int c = 0; c < 32; c++)
            {
                current1 = (number1 >> c) & 1;
                current2 = (number2 >> c) & 1;

                if ((current1 & current2) == 1)
                {
                    vertical++;
                }
            }

            for (int c = 0; c < 31; c++)
            {
                current1 = (number1 >> (c + 1)) & 1;
                current2 = (number2 >> c) & 1;

                if ((current1 & current2) == 1)
                {
                    right++;
                }
            }

            for (int c = 0; c < 31; c++)
            {
                current1 = (number1 >> c) & 1;
                current2 = (number2 >> (c + 1)) & 1;

                if ((current1 & current2) == 1)
                {
                    left++;
                }
            }
            number1 = number2;
        }
        Console.WriteLine(right);
        Console.WriteLine(left);
        Console.WriteLine(vertical);
    }
}
