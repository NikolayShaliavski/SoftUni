using QuadTree.Tests;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace QuadTree.Core
{
    class QuadTreeMain
    {
        static void Main(string[] args)
        {
            var tree = new QuadTree<TestBox>(200, 200);

            var shepard = new TestBox(10, 20);
            var random = new Random();

            tree.Insert(shepard);
            for (int i = 0; i < 30; i++)
            {
                var x = random.Next(0, 190);
                var y = random.Next(0, 190);
                var obj = new TestBox(x, y);
                tree.Insert(obj);
            }

            Console.WriteLine();
        }
    }
}
