package pr04_StarsInCube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class StarsInCube {

    private static char[][][] cube;
    private static Map<Character, Integer> stars;
    //don't use lambda for simple counters
    //like allSars = stars.entrySet().stream().mapToInt(star -> star.getValue()).sum();
    //this is slow and some tests may not pass
    private static int starsCounter;

    public static void main(String[] args) throws IOException {
        buildCube();
        starsCounter = 0;
        countStars();

        System.out.println(starsCounter);
        for (Map.Entry<Character, Integer> star : stars.entrySet()) {
            System.out.printf("%s -> %d%n", star.getKey(), star.getValue());
        }
    }

    private static void countStars() {
        stars = new TreeMap<>();

        for (int x = 1; x < cube.length - 1; x++) {
            for (int y = 1; y < cube[x].length - 1; y++) {
                for (int z = 1; z < cube[x][y].length - 1; z++) {
                    char cell = cube[x][y][z];
                    if (cell == cube[x - 1][y][z] &&  //up
                            cell == cube[x + 1][y][z] &&  //down
                            cell == cube[x][y - 1][z] &&  //left
                            cell == cube[x][y + 1][z] &&  //right
                            cell == cube[x][y][z - 1] &&  //front
                            cell == cube[x][y][z + 1]) {  //behind
                        if (!stars.containsKey(cell)) {
                            stars.put(cell, 0);
                        }
                        int cellStars = stars.get(cell);
                        stars.put(cell, cellStars + 1);
                        starsCounter++;
                    }
                }
            }
        }
    }

    private static void buildCube() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bf.readLine());

        cube = new char[n][n][n];

        for (int x = 0; x < n; x++) {
            String[] tokens = bf.readLine().trim().split("[\\|]");
            for (int y = 0; y < n; y++) {
                String[] line = tokens[y].trim().split("[\\s]+");
                for (int z = 0; z < n; z++) {
                    char cell = line[z].charAt(0);
                    cube[x][y][z] = cell;
                }
            }
        }
    }
}
