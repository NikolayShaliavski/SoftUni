using System;
using System.Collections.Generic;
using System.Linq;

public class TopologicalSorter
{
    private Dictionary<string, List<string>> graph;
    private LinkedList<string> sortedNodes;
    private HashSet<string> visitedNodes;
    private HashSet<string> cycleNodes;

    public TopologicalSorter(Dictionary<string, List<string>> graph)
    {
        this.graph = graph;
        this.sortedNodes = new LinkedList<string>();
        this.visitedNodes = new HashSet<string>();
        this.cycleNodes = new HashSet<string>();
    }

    //public ICollection<string> TopSort()
    //{  
    //    var predecessorsCount = FindPredecessors();

    //    var removedNodes = new List<string>();
    //    while (true)
    //    {
    //        var nodeToRemove = graph.Keys.FirstOrDefault(node => predecessorsCount[node] == 0);

    //        if (nodeToRemove == null)
    //        {
    //            break;
    //        }
    //        foreach (var child in graph[nodeToRemove])
    //        {
    //            predecessorsCount[child]--;
    //        }
    //        graph.Remove(nodeToRemove);
    //        removedNodes.Add(nodeToRemove);
    //    }

    //    if (graph.Count > 0)
    //    {
    //        throw new InvalidOperationException("A cycle detected in the graph");
    //    }
    //    return removedNodes;  
    //}

    public ICollection<string> TopSort()
    {
        foreach (var node in graph.Keys)
        {
            TopSortDFS(node);
        }
        return sortedNodes;
    }

    private void TopSortDFS(string node)
    {
        if (cycleNodes.Contains(node))
        {
            throw new InvalidOperationException("A cycle detected in the graph");
        }

        if (!visitedNodes.Contains(node))
        {
            visitedNodes.Add(node);
            cycleNodes.Add(node);
            if (graph.ContainsKey(node))
            {
                foreach (var child in graph[node])
                {
                    TopSortDFS(child);
                }
            }
            sortedNodes.AddFirst(node);
            cycleNodes.Remove(node);
        }
    }

    private IDictionary<string, int> FindPredecessors()
    {
        var predecessorsCount = new Dictionary<string, int>();
        foreach (var node in this.graph)
        {
            if (!predecessorsCount.ContainsKey(node.Key))
            {
                predecessorsCount[node.Key] = 0;
            }
            foreach (var child in node.Value)
            {
                if (!predecessorsCount.ContainsKey(child))
                {
                    predecessorsCount[child] = 0;
                }
                predecessorsCount[child]++;
            }
        }
        return predecessorsCount;
    }
}
