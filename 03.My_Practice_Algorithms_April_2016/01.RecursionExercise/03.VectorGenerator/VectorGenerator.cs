using System;

namespace _03.VectorGenerator
{
    class VectorGenerator
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());

            int[] vector = new int[n];

            GenerateVector(vector, 0);
            Console.WriteLine(counter);
        }

        private static int counter;

        static void GenerateVector(int[] vector, int index)
        {
            if (index == vector.Length)
            {
                Print(vector);
                return;
            }
            counter++;
            for (int i = 1; i <= 3; i++)
            {
                vector[index] = i;
                GenerateVector(vector, index + 1);
            }
        }

        static void Print(int[] vector)
        {
            for (int i = 0; i < vector.Length; i++)
            {
                Console.Write(vector[i] + " ");
            }
            Console.WriteLine();
        }
    }
}
