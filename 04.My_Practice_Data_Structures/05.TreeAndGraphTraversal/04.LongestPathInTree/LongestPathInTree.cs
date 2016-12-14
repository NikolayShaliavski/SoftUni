using System;
using System.Collections.Generic;
using System.Linq;

class Node
{
    public Node(int value)
    {
        this.Value = value;
        this.Children = new List<Node>();
    }

    public int Value { get; set; }

    public List<Node> Children { get; set; }

    public bool hasParent { get; set; }

    public int maxPath { get; set; }

    public int secondPath { get; set; }

    public override string ToString()
    {
        return this.Value.ToString();
    }
}

class LongestPathInTree
{
    private static Dictionary<int, Node> tree = new Dictionary<int, Node>();
    private static int bestPath;

    static void Main(string[] args)
    {
        var nodesCount = int.Parse(Console.ReadLine());  
        var edgesCount = int.Parse(Console.ReadLine());

        for (int i = 0; i < edgesCount; i++)
        {
            var edge = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
            var parent = edge[0];
            var child = edge[1];

            if (!tree.ContainsKey(parent))
            {
                tree[parent] = new Node(parent);
            }

            if (!tree.ContainsKey(child))
            {
                tree[child] = new Node(child);               
                
            }
            tree[child].hasParent = true;
            tree[parent].Children.Add(tree[child]);
        }
        Node root = tree.Single(n => !n.Value.hasParent).Value;
        Dfs(root);
        Console.WriteLine(bestPath);
    }

    private static void Dfs(Node node)
    {
        //if (tree[node.Value].Children.Count == 0)
        //{
        //    return;
        //}
        int maxPath = int.MinValue;
        int secondPath = int.MinValue;

        foreach (var child in node.Children)
        {
            Dfs(child);

            if (maxPath < child.maxPath + child.Value)
            {
                secondPath = maxPath;
                maxPath = child.maxPath + child.Value;
            }
        }
        if (maxPath > int.MinValue)
        {
            node.maxPath = maxPath;
        }
        if (secondPath > int.MinValue)
        {
            node.secondPath = secondPath;
        }

        if (bestPath < node.maxPath + node.Value + node.secondPath)
        {
            bestPath = node.maxPath + node.Value + node.secondPath;
        }
    }
}