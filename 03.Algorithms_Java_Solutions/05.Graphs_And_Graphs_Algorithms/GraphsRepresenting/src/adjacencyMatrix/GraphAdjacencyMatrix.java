package adjacencyMatrix;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Representing a graph in memory using adjacency matrix
 *
 * Hardcoded:
 * int[][] graph = new[][] {
 *   0  1  2  3  4  5  6
 * { 0, 0, 0, 1, 0, 0, 1 }, // node 0
 * { 0, 0, 1, 1, 1, 1, 1 }, // node 1
 * { 0, 1, 0, 0, 1, 1, 0 }, // node 2
 * { 1, 1, 0, 0, 0, 1, 0 }, // node 3
 * { 0, 1, 1, 0, 0, 0, 1 }, // node 4
 * { 0, 1, 1, 1, 0, 0, 0 }, // node 5
 * { 1, 1, 0, 0, 1, 0, 0 }, // node 6
 * };
 * We can use also boolean matrix.
 * Matrix uses more memory than adjacency List, but
 * if we use int matrix we can store in cells weight of edges !!!!!!!!!,
 * not only 0 or 1 (is there edge between V1 and V2)
 */
public class GraphAdjacencyMatrix {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //read number of vertexes in graph
        int vertexesCount = Integer.valueOf(bf.readLine());

        //create matrix with dimensions == count of vertexes
        int[][] graph = new int[vertexesCount][vertexesCount];

        for (int i = 0; i < vertexesCount; i++) {
            //read row with children of vertex at index i
            int[] children = Arrays.stream(
                    bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            //set that there is an edge between vertex 'i' and vertex 'child'
            //(vertex is parent of child)
            for (int child : children) {
                graph[i][child] = 1;
            }
        }
        printGraph(graph);
    }

    private static void printGraph(int[][] graph) {
        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {
                System.out.print(graph[row][col] + " ");
            }
            System.out.println();
        }
    }
}
