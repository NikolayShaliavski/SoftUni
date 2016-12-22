package pr04_Salaries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Salaries {

    private static List<Integer>[] hierarchy;
    private static boolean[] visited;
    private static long[] salaries;

    public static void main(String[] args) throws IOException {
        readGraph();

        visited = new boolean[hierarchy.length];
        salaries = new long[hierarchy.length];

        for (int i = 0; i < hierarchy.length; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        long allSalaries = Arrays.stream(salaries).sum();
        System.out.println(allSalaries);
    }

    private static void dfs(int node) {
        if (!visited[node]) {
            visited[node] = true;

            salaries[node] = 1;
            long newSalary = 0;
            for (Integer employee : hierarchy[node]) {
                dfs(employee);
                newSalary += salaries[employee];
            }
            if (newSalary > 0) {
                salaries[node] = newSalary;
            }
        }
    }

    private static void readGraph() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int employeesCount = Integer.valueOf(bf.readLine());
        hierarchy = new List[employeesCount];

        for (int i = 0; i < employeesCount; i++) {
            String line = bf.readLine();
            hierarchy[i] = new ArrayList<>();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'Y') {
                    hierarchy[i].add(j);
                }
            }
        }
    }
}
