using System;
using System.Collections.Generic;
using System.Linq;

class BitLock
{
    static void  Check(int[] numbers, int position, List<int> checkedNums)
    {
        int mask = 1;
        int count = 0;
        int current = 0;
        for (int i = 0; i < numbers.Length; i++)
        {
            current = numbers[i] & mask << position;
            if ((current >> position) == 1)
            {
                count++;
            }
        }
        checkedNums.Add(count);
    }

    static void Rotation(int[] numbers, int position, int rotations, string direction)
    {
        int mask = 1;
        int current = 0;
        int number = numbers[position];

        for (int i = 0; i < rotations; i++)
        {
            if (direction == "right")
            {
                current = number & mask;
                number >>= 1;
                number = number ^ (current << 11);
            }
            else
            {
                current = number & (mask << 11);
                number = number & ~(mask << 11);
                number <<= 1;
                number = number ^ (current >> 11);
            }
        }
        numbers[position] = number;
    }

    static void Main()
    {
        int[] numbers = Console.ReadLine().Split().Select(int.Parse).ToArray();    

        List<int> checkedNums = new List<int>();

        string line = Console.ReadLine();

        while (line != "end")
        {
            if (line[0] == 'c')
            {
                string[] command = line.Split();
                int position = int.Parse(command[1]);
                Check(numbers, position, checkedNums);
            }
            else
            {
                string[] command = line.Split();
                int position = int.Parse(command[0]);
                int rotations = int.Parse(command[2]);
                string direction = command[1];
                Rotation(numbers, position, rotations, direction);
            }
            line = Console.ReadLine();
        }

        foreach (int i in checkedNums)
        {
            Console.WriteLine(i);
        }

        foreach (int i in numbers)
        {
            Console.Write(i + " ");
        }
        Console.WriteLine();
    }
}
