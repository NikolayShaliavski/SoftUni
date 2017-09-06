using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;

class PlayWithTrees
{
    static Dictionary<int, Tree<int>> nodesByValue = new Dictionary<int, Tree<int>>();
    static List<Tree<int>> maxPath = new List<Tree<int>>();

    static void Main(string[] args)
    {
        var nodesCount = int.Parse(Console.ReadLine());

        for (int i = 0; i < nodesCount - 1; i++)
        {
            var edge = Console.ReadLine().Split(' ').Select(int.Parse).ToArray();
            int parentNodeValue = edge[0];
            int childNodeValue = edge[1];

            var parentNode = GetTreeNodeByValue(parentNodeValue);
            var childNode = GetTreeNodeByValue(childNodeValue);

            parentNode.Children.Add(childNode);
            childNode.Parent = parentNode;
        }
        int pathSum = int.Parse(Console.ReadLine());
        int subtreeSum = int.Parse(Console.ReadLine());

        Console.WriteLine("Root node: {0}", FindRoot().Value);
        Console.WriteLine();
        Console.WriteLine("Leaf nodes: {0}", string.Join(", ", FindLeafNodes()));
        Console.WriteLine();
        Console.WriteLine("Middle nodes: {0}", string.Join(", ", FindMiddleNodes()));
        Console.WriteLine();
        var longestPath = FindLongestPath();
        Console.WriteLine("Longest path: {0} (length = {1})", string.Join(" -> ", longestPath), longestPath.Count());
        Console.WriteLine();
        Console.WriteLine("Paths of sum {0}:", pathSum);
        FindPathsOfGivenSum(pathSum);
        Console.WriteLine();
        Console.WriteLine("Subtrees of sum {0}:", subtreeSum);
        FindSubtrees(subtreeSum);

    }

    static void FindSubtrees(int subtreeSum)
    {
        foreach (var node in nodesByValue)
        {
            List<int> subTree = new List<int>();
            TraverseTreeForSubtrees(node.Value, subTree, subtreeSum);
        }
    }

    static void TraverseTreeForSubtrees(Tree<int> node, List<int> subTree, int subtreeSum)
    {
        subTree.Add(node.Value);
        foreach (var child in node.Children)
        {
            TraverseTreeForSubtrees(child, subTree, subtreeSum);
        }
        var currentSubtreeSum = subTree.Sum();
        if (currentSubtreeSum == subtreeSum)
        {
            Console.WriteLine(string.Join(" + ", subTree));
            subTree.Clear();
        }
    }

    static void FindPathsOfGivenSum(int pathSum)
    {
        var root = FindRoot();
        List<int> path = new List<int>();
        TraverseTreeForPaths(root, path, pathSum);
    }

    static void TraverseTreeForPaths(Tree<int> node, List<int> path, int pathSum)
    {
        path.Add(node.Value);
        if (node.Children.Count == 0)
        {
            var sum = path.Sum();
            if (sum == pathSum)
            {
                Console.WriteLine(string.Join(" -> ", path));
            }
        }
        foreach (var child in node.Children)
        {
            TraverseTreeForPaths(child, path, pathSum);
            path.RemoveAt(path.Count - 1);
        }
    }

    static Tree<int> FindRoot()
    {
        var root = nodesByValue.Values.FirstOrDefault(n => n.Parent == null);
        return root;
    }

    static IEnumerable<int> FindLeafNodes()
    {
        IEnumerable<int> leafs = new List<int>();

        leafs = nodesByValue.Where(
            n => n.Value.Children.Count == 0).
            Select(n => n.Key).
            ToList();
        return leafs.OrderBy(l => l);
    }
    static IEnumerable<int> FindMiddleNodes()
    {
        IEnumerable<int> middleNodes = new List<int>();

        middleNodes = nodesByValue.Where(
            n => n.Value.Parent != null && n.Value.Children.Count > 0).
            Select(n => n.Key).
            ToList();
        return middleNodes.OrderBy(n => n);
    }

    static IEnumerable<int> FindLongestPath()
    {
        List<Tree<int>> currPath = new List<Tree<int>>();
        var root = FindRoot();
        TraverseTree(root, currPath);
        return maxPath.Select(n => n.Value);
    }

    static void TraverseTree(Tree<int> node, List<Tree<int>> currPath)
    {
        currPath.Add(node);
        if (node.Children.Count == 0)
        {
            if (maxPath.Count < currPath.Count)
            {
                maxPath = currPath.ToList();
            }
            return;
        }
        foreach (var child in node.Children)
        {
            TraverseTree(child, currPath);
            currPath.RemoveAt(currPath.Count - 1);
        }
    }

    static Tree<int> GetTreeNodeByValue(int value)
    {
        if (!nodesByValue.ContainsKey(value))
        {
            nodesByValue[value] = new Tree<int>(value);
        }
        return nodesByValue[value];
    }
}

