using System;

class MatrixOfNumbers
{
    static void Main()
    {
        int input = int.Parse(Console.ReadLine());

        for (int i = 1; i <= input; i++)
        {
            for (int j = i; j < input + i; j++)
            {
                Console.Write(j + " ");
            }
            Console.WriteLine();
        }
    }
}
