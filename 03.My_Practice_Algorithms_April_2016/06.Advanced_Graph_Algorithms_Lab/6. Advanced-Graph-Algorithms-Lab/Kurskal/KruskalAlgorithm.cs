namespace Kurskal
{
    using System.Collections.Generic;

    public class KruskalAlgorithm
    {
        private static int[] parent;

        public static List<Edge> Kruskal(int numberOfVertices, List<Edge> edges)
        {
            edges.Sort();

            parent = new int[numberOfVertices];
            for (int i = 0; i < numberOfVertices; i++)
            {
                parent[i] = i;
            }

            var spanningTree = new List<Edge>();
            foreach (var edge in edges)
            {
                int rootStartNode = FindRoot(edge.StartNode);
                int rootEndNode = FindRoot(edge.EndNode);

                if (rootStartNode != rootEndNode)
                {
                    spanningTree.Add(edge);
                    parent[rootStartNode] = edge.EndNode;
                }
            }
            return spanningTree;
        }

        public static int FindRoot(int node)
        {
            int root = node;
            while (parent[root] != root)
            {
                root = parent[root];
            }

            while (node != root)
            {
                var oldParent = parent[node];
                parent[node] = root;
                node = oldParent;
            }
            return root;
        }
    }
}
