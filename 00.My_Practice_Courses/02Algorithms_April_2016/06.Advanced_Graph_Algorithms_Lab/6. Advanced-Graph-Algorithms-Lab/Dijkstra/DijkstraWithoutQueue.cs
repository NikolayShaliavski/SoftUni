namespace Dijkstra
{
    using System.Collections.Generic;

    public static class DijkstraWithoutQueue
    {
        public static List<int> DijkstraAlgorithm(int[,] graph, int sourceNode, int destinationNode)
        {
            int n = graph.GetLength(0);//number of verticies
            //init distance array
            var distances = new int[n];
            
            for (int i = 0; i < distances.Length; i++)
            {
                distances[i] = int.MaxValue;
            }
            distances[sourceNode] = 0;//distance from start to startNode is ZERO

            var used = new bool[n];
            int?[] previous = new int?[n];//int? -> because we need nullable type here

            while (true)
            {
                int minDistance = int.MaxValue;
                int minNode = 0;
                for (int node = 0; node < n; node++)
                {
                    //search for nearest (with minimum distance) from start non-visited node
                    if (!used[node] && distances[node] < minDistance)
                    {
                        minDistance = distances[node];
                        minNode = node;
                    }
                }
                if (minDistance == int.MaxValue)
                {
                    //No min-distance node found --> algorithm finished
                    break;
                }
                //set nearest node as visited
                used[minNode] = true; 

                for (int i = 0; i < n; i++)
                {
                    //search for children of minNode
                    if (graph[minNode, i] > 0)
                    {
                        int newDistance = distances[minNode] + graph[minNode, i];
                        //
                        //there are two cases
                        //--> child may be non-visited yet -> it's distance is int.MaxValue; in prev[] parent(previous node) is null
                        //--> child may be already visited, and path to it is throw another parent(and distance is already calculated)
                        //if newDistance(throw this parent) to child is better than already existed 
                        //we set newDistance and in prev[] change the parent(previous node)
                        if (newDistance < distances[i])
                        {
                            distances[i] = newDistance;
                            previous[i] = minNode;
                        }
                    }
                }               
            }
            if (distances[destinationNode] == int.MaxValue)//there isn't path(edge) to destination node
            {
                return null;
            }

            //Reconstruct the path
            List<int> path = new List<int>();
            int? currentNode = destinationNode;
            while (currentNode != null)
            {
                path.Add(currentNode.Value);
                currentNode = previous[currentNode.Value];
            }
            path.Reverse();
            return path;
        }
    }
}
