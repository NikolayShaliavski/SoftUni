using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _05.TowerOfHanoi
{
    class TowerOfHanoi
    {
        private static Stack<int> source;
        private static Stack<int> destination = new Stack<int>();
        private static Stack<int> spare = new Stack<int>();
        private static int stepsTaken = 0;

        static void Main(string[] args)
        {
            var range = Enumerable.Range(1, 3);
            source = new Stack<int>(range.Reverse());
            PrintRods();
            MoveDisks(3, source, destination, spare);
        }

        private static void MoveDisks(int bottomDisk, Stack<int> source, Stack<int> destination, Stack<int> spare)
        {
            if (bottomDisk == 1)
            {
                stepsTaken++;
                destination.Push(source.Pop());
                Console.WriteLine($"Step #{stepsTaken}: Moved disk {bottomDisk}");
                PrintRods();
            } 
            else
            {
                MoveDisks(bottomDisk - 1, source, spare, destination);
                stepsTaken++;
                destination.Push(source.Pop());
                Console.WriteLine($"Step #{stepsTaken}: Moved disk {bottomDisk}");
                PrintRods();
                MoveDisks(bottomDisk - 1, spare, destination, source);
            }
        }

        private static void PrintRods()
        {
            Console.WriteLine("Source: {0}", string.Join(", ", source.Reverse()));
            Console.WriteLine("Destination: {0}", string.Join(", ", destination.Reverse()));
            Console.WriteLine("Spare: {0}", string.Join(", ", spare.Reverse()));
            Console.WriteLine();
        }
    }
}
