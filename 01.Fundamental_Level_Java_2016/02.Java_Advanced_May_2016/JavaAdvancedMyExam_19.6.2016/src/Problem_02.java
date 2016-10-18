import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Problem_02 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int dimention = Integer.valueOf(reader.readLine());

        int[][][] cube = new int[dimention][dimention][dimention];
        BigInteger sum = new BigInteger("0");

        String line = reader.readLine();
        while (!line.equals("Analyze")) {
            String[] coordinates = line.split("[\\s]+");
            int x = Integer.valueOf(coordinates[0]);
            int y = Integer.valueOf(coordinates[1]);
            int z = Integer.valueOf(coordinates[2]);
            int value = Integer.valueOf(coordinates[3]);

            boolean isInCube = checkCoordinates(x, y, z, dimention);
            if (isInCube) {
                cube[x][y][z] = value;
            }
            line = reader.readLine();
        }
        int freeCells = 0;
        for (int x = 0; x < cube.length; x++) {
            for (int y = 0; y < cube.length; y++) {
                for (int z = 0; z < cube.length; z++) {
                    sum = sum.add(BigInteger.valueOf(cube[x][y][z]));
                    if (cube[x][y][z] == 0) {
                        freeCells++;
                    }
                }
            }
        }
        System.out.println(sum);
        System.out.println(freeCells);
    }

    private static boolean checkCoordinates(int x, int y, int z, int dimention) {
        if (x >= 0 && x < dimention && y >= 0 && y < dimention && z >= 0 && z < dimention) {
            return true;
        }
        return false;
    }
}
