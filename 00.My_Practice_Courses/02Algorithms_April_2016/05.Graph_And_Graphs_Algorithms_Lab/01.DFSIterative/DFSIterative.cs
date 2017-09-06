using System;
using System.Collections.Generic;

namespace _01.DFSIterative
{
    public class DFSIterative
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
                    Console.Write("Connected component:");
                    DFSIterativeTraverse(i);
                    Console.WriteLine();
                }

            }

            Console.WriteLine();
        }

        //iterative solution with Stack generates different output compared to recursive solution
        private static void DFSIterativeTraverse(int node)
        {
            Stack<int> components = new Stack<int>();
            components.Push(node);
            visited[node] = true;

            while (components.Count > 0)
            {
                int currentComponent = components.Peek();
                foreach (var child in graph[currentComponent])
                {
                    if (!visited[child])
                    {
                        visited[child] = true;
                        components.Push(child);
                    }
                }
                Console.Write(" " + components.Pop());
            }
        }
    }
}
