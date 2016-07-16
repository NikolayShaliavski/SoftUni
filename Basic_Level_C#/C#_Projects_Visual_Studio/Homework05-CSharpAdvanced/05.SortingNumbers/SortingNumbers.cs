using System;

class SortingNumbers
{
    static void Main()
    {
        int input = int.Parse(Console.ReadLine());
        int[] numbers = new int[input];

        for (int i = 0; i < input; i++)
        {
            numbers[i] = int.Parse(Console.ReadLine());
        }

        Array.Sort(numbers);

        foreach (int num in numbers)
        {
            Console.WriteLine(num);
        }
    }
}
