using System;
using System.Collections.Generic;
using System.Text;

class WiggleWiggle
{
    static void Main()
    {
        string input = Console.ReadLine();

        string[] inputArray = input.Split(' ');
        long number1 = 0;
        long number2 = 0;
        List<long> listNums = new List<long>();

        for (int i = 0, j = 1; i <= inputArray.Length - 2; i += 2, j += 2)
        {
            number1 = long.Parse(inputArray[i]);
            number2 = long.Parse(inputArray[j]);

            long mask = 1;
            long bit1 = 0;
            long bit2 = 0;

            for (int position = 0; position <= 62; position += 2)
            {
                mask <<= position;

                bit1 = number1 & mask;
                bit2 = number2 & mask;

                bit1 >>= position;
                bit2 >>= position;

                if (bit1 == 0)
                {
                    number2 &= ~mask;
                }
                if (bit1 == 1)
                {
                    number2 |= mask;
                }
                if (bit2 == 0)
                {
                    number1 &= ~mask;
                }
                if (bit2 == 1)
                {
                    number1 |= mask;
                }
                mask = 1;
            }
            listNums.Add(number1);
            listNums.Add(number2);
        }

        StringBuilder builder = new StringBuilder();
        long numberReversed = 0;
        string listNumber = string.Empty;

        for (int i = 0; i < listNums.Count; i++)
        {
            listNumber = Convert.ToString(listNums[i], 2).PadLeft(63, '0');
            char[] bitNums = listNumber.ToCharArray();

            for (int j = 0; j < bitNums.Length; j++)
            {
                if (bitNums[j] == '0')
                {
                    bitNums[j] = '1';
                }
                else
                {
                    bitNums[j] = '0';
                }

            }

            foreach (char bit in bitNums)
            {
                builder.Append(bit);
            }
            listNumber = builder.ToString();
            numberReversed = Convert.ToInt64(listNumber, 2);
            Console.WriteLine(numberReversed + " " + listNumber);

            builder.Clear();
            numberReversed = 0;
        }
    }
}
