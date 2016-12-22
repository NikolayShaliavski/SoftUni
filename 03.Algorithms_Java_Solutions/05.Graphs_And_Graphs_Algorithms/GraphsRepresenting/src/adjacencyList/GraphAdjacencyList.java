package adjacencyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Representing a graph in memory using adjacency List
 *
 * Hardcoded:
 * List<Integer>[] graph = new List<Integer>[]{
 * new ArrayList<Integer> {3, 6},
 * new ArrayList<Integer> {2, 3, 4, 5, 6},
 * new ArrayList<Integer> {1, 4, 5},
 * new ArrayList<Integer> {0, 1, 5},
 * new ArrayList<Integer> {1, 2, 6},
 * new ArrayList<Integer> {1, 2, 3},
 * new ArrayList<Integer> {0, 1, 4}
 * };
 * Graph vertexes are mapped to indexes in the array
 * Note that here we can use also List<List<int>> (better because we can add new vertexes)
 *
 * Can use also Map<T, List<T>> if vertexes cannot be mapped to indexes (Strings for example)
 */
public class GraphAdjacencyList {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //read number of vertexes in graph
        int vertexesCount = Integer.valueOf(bf.readLine());

        List<Integer>[] graph = new List[vertexesCount];

        for (int i = 0; i < vertexesCount; i++) {
            //read row with children of vertex at index i
            int[] children = Arrays.stream(
                            bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[i] = new ArrayList<>();
            for (int child : children) {
                graph[i].add(child);
            }
        }
        printGraph(graph);
    }

    private static void printGraph(List<Integer>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.print(String.format("Vertex: %d; Children: ", i));
            List<Integer> children = graph[i];
            for (Integer child : children) {
                System.out.print(child + " ");
            }
            System.out.println();
        }
    }
}
