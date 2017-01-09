using System;
using System.Collections.Generic;
using System.Linq;

public class GraphConnectedComponents
{
    private static List<int>[] graph;
    private static bool[] visited;

    public static void Main()
    {
        ReadGraph();

        FindGraphConnectedComponents();
    }

    private static void ReadGraph()
    {
        var n = int.Parse(Console.ReadLine());
        graph = new List<int>[n];
        visited = new bool[n];

        for (int i = 0; i < n; i++)
        {
            graph[i] = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).
                Select(int.Parse).ToList();
        }
    }

    private static void FindGraphConnectedComponents()
    {
        for (int startNode = 0; startNode < graph.Length; startNode++)
        {
            if (!visited[startNode])
            {
                Console.Write("Connected component:");
                Dfs(startNode);
                Console.WriteLine();
            }
        }
    }

    private static void Dfs(int node)
    {
        if (!visited[node])
        {
            visited[node] = true;
            foreach (var child in graph[node])
            {
                Dfs(child);
            }
            Console.Write(" " + node);
        }
    }
}
