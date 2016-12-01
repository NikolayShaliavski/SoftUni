using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AvlTreeLab
{
    class Program
    {
        static void Main(string[] args)
        {
            AvlTree<int> avlTree = new AvlTree<int>();

            var numbers = Console.ReadLine().Split(' ').Select(i => int.Parse(i)).ToArray();
            var range = Console.ReadLine().Split(' ').Select(i => int.Parse(i)).ToArray();

            foreach (var num in numbers)
            {
                avlTree.Add(num);
            }
            var from = range[0];
            var to = range[1];
            List<int> rangeResult = avlTree.Range(from, to);
            if (rangeResult.Count == 0)
            {
                Console.WriteLine("(empty)");
                return;
            }
            foreach (var number in rangeResult)
            {
                Console.Write(number + " ");
            }
            Console.WriteLine();

            //avlTree.Add(4);
            //avlTree.Add(5);           
            //avlTree.Add(6);
            //avlTree.Add(1);
            //avlTree.Add(2);
            //avlTree.Add(3);
            //avlTree.Add(7);
            //avlTree.Add(8);
            //avlTree.Add(9);
            //avlTree.Add(10);

            //avlTree.Print();
        }
    }
}
