using System;
using System.Collections.Generic;
using System.Linq;

class FindTheRoot
{
    static void Main(string[] args)
    {
        var nodesCount = int.Parse(Console.ReadLine());
        var edges = int.Parse(Console.ReadLine());

        bool[] hasParent = new bool[nodesCount];

        for (int i = 0; i < edges; i++)
        {
            var edge = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
            var child = edge[1];

            hasParent[child] = true;
        }

        var roots = new List<int>();
        for (int i = 0; i < hasParent.Length; i++)
        {
            if (!hasParent[i])
            {
                roots.Add(i);
            }
        }

        if (roots.Count == 0)
        {
            Console.WriteLine("No root!");
        }
        else if (roots.Count > 1)
        {
            Console.WriteLine("Multiple root nodes!");
        }
        else
        {
            Console.WriteLine(roots[0]);
        }
    }
}
