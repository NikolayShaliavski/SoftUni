using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _04.OrderedSet
{
    class OrderedSetMain
    {
        static void Main(string[] args)
        {
            //var testSet = new OrderedSet<int>();
            //testSet.Add(17);
            //testSet.Add(9);
            //testSet.Add(30);
            //testSet.Add(5);
            //testSet.Add(11);
            //testSet.Add(28);
            //testSet.Add(34);
            //testSet.Add(27);
            //testSet.Add(29);
            //testSet.Add(32);
            //testSet.Add(35);
            //testSet.Add(33);
            //testSet.Add(31);

            //testSet.Remove(17);
            //testSet.Remove(27);
            //testSet.Add(4);
            //testSet.Add(6);
            //testSet.Remove(11);
            //Console.WriteLine(testSet.Remove(30));
            //Console.WriteLine(testSet.Remove(100));
            //testSet.Add(17);
            //testSet.Print();
            //Console.WriteLine();
            //foreach (var node in testSet)
            //{
            //    Console.WriteLine(node);
            //}
            //Console.WriteLine();
            //Console.WriteLine(testSet.Count);


            var test2 = new OrderedSet<int>();

            test2.Add(17);
            test2.Add(40);
            test2.Add(12);
            test2.Add(10);
            //test2.Add(9);
            //test2.Add(8);
            test2.Add(30);
            test2.Add(28);
            //test2.Add(25);
            test2.Add(13);
            //test2.Add(14);
            //test2.Add(15);

            //test2.Remove(17);
            //test2.Remove(12);

            Console.WriteLine(test2.Count);
            test2.Remove(17);
            test2.Remove(40);
            test2.Remove(12);
            test2.Remove(10);
            //test2.Remove(30);
            //test2.Remove(28);
            //test2.Remove(13);

            Console.WriteLine(test2.Count);
            test2.Print();
            //Console.WriteLine(test2.Contains(25));

            //foreach (var node in test2)
            //{
            //    Console.WriteLine(node);
            //}
        }
    }
}
