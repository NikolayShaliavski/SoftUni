package graphReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * With this reader we create directed graph
 */
public class GraphReader {

    public static List<List<Integer>> readGraph() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        List<List<Integer>> graph = new ArrayList<>();

        int vertexesCount = Integer.valueOf(bf.readLine());
        for (int i = 0; i < vertexesCount; i++) {
            int[] children =
                    Arrays.stream(bf.readLine().split(" ")).
                            mapToInt(Integer::parseInt).toArray();
            graph.add(new ArrayList<>());
            /**
             * If we want to make graph undirected in each iteration
             * over children for each child we have to add parent to it's list
             * graph.get(child).add(i) !!! if List is not initialized -> init it
             */
            for (int child : children) {
                graph.get(i).add(child);
            }
        }
        return graph;
    }
}
