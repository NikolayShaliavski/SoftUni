using System;
using System.Collections.Generic;

namespace _02.BFSAlgorithm
{
    class BFSAlgorithm
    {
        static List<int>[] graph = new List<int>[]
        {
            new List<int>() { 3, 6 },
            new List<int>() { 3, 4, 5, 6 },
            new List<int>() { 8 },
            new List<int>() { 0, 1, 5 },
            new List<int>() { 1, 6 },
            new List<int>() { 1, 3 },
            new List<int>() { 0, 1, 4 },
            new List<int>() { },
            new List<int>() { 2 }
        };

        private static bool[] visited;

        static void Main(string[] args)
        {
            visited = new bool[graph.Length];

            for (int i = 0; i < graph.Length; i++)
            {
                if (!visited[i])
                {
                    Console.Write("Connected components:");
                    BFSTraversal(i);
                    Console.WriteLine();
                }
            }
        }

        private static void BFSTraversal(int node)
        {
            Queue<int> components = new Queue<int>();
            visited[node] = true;
            components.Enqueue(node);

            while (components.Count > 0)
            {
                int component = components.Peek();
                foreach (var child in graph[component])
                {
                    if (!visited[child])
                    {
                        visited[child] = true;
                        components.Enqueue(child);
                    }
                }
                Console.Write(" " + components.Dequeue());
            }
        }
    }
}
