using System;
using System.Linq;

class BitSwapper
{
    static void Main()
    {
        uint[] numbers = new uint[4];
        for (int i = 0; i < 4; i++)
        {
            numbers[i] = uint.Parse(Console.ReadLine());
        }

        string command1 = Console.ReadLine();      

        while (command1 != "End")
        {
            int[] first = command1.Split().Select(x => int.Parse(x)).ToArray();
            int[] second = Console.ReadLine().Split().Select(x => int.Parse(x)).ToArray();

            int position1 = first[1] * 4;
            int position2 = second[1] * 4;
            uint mask = 15;

            uint current1 = numbers[first[0]] & (mask << position1);
            uint current2 = numbers[second[0]] & (mask << position2);

            current1 >>= position1;
            current2 >>= position2;

            numbers[first[0]] &= ~(mask << position1);          //setting the group of 4 bits to zero
            numbers[second[0]] &= ~(mask << position2);

            numbers[first[0]] |= current2 << position1;
            numbers[second[0]] |= current1 << position2;

            command1 = Console.ReadLine();
        }

        foreach (uint num in numbers)
        {
            Console.WriteLine(num);
        }
    }
}
