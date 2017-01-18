package pr04_Snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SnakeProblem {

    static BufferedReader bf;
    static int n;
    static char[][][] cube;
    static Snake snake;
    static int points;

    public static void main(String[] args) throws IOException {
        readInput();

        points = 0;
        String direction = bf.readLine();
        int steps;


        while (true) {
            String line = bf.readLine();
            String[] tokens = line.split("[\\s]+");
            steps = Integer.valueOf(tokens[2]);

            boolean moveSuccess = moveSnake(direction, steps);

            if (!moveSuccess) {
                System.out.println("Points collected: " + points);
                System.out.println("The snake dies.");
                return;
            }

            direction = tokens[0];
            if (direction.equals("end")) {
                break;
            }
        }
        System.out.println("Points collected: " + points);
    }

    private static boolean moveSnake(String direction, int steps) {
        switch (direction) {
            case "down":
                for (int i = 0; i < steps; i++) {
                    snake.x++;
                    if (isInside()) {
                        if (meetApple()) {
                            cube[snake.x][snake.y][snake.z] = 'o';
                            points++;
                        }
                    }
                }
                break;
            case "up":
                for (int i = 0; i < steps; i++) {
                    snake.x--;
                    if (isInside()) {
                        if (meetApple()) {
                            cube[snake.x][snake.y][snake.z] = 'o';
                            points++;
                        }
                    }
                }
                break;
            case "forward":
                for (int i = 0; i < steps; i++) {
                    snake.y--;
                    if (isInside()) {
                        if (meetApple()) {
                            cube[snake.x][snake.y][snake.z] = 'o';
                            points++;
                        }
                    }
                }
                break;
            case "backward":
                for (int i = 0; i < steps; i++) {
                    snake.y++;
                    if (isInside()) {
                        if (meetApple()) {
                            cube[snake.x][snake.y][snake.z] = 'o';
                            points++;
                        }
                    }
                }
                break;
            case "left":
                for (int i = 0; i < steps; i++) {
                    snake.z--;
                    if (isInside()) {
                        if (meetApple()) {
                            cube[snake.x][snake.y][snake.z] = 'o';
                            points++;
                        }
                    }
                }
                break;
            case "right":
                for (int i = 0; i < steps; i++) {
                    snake.z++;
                    if (isInside()) {
                        if (meetApple()) {
                            cube[snake.x][snake.y][snake.z] = 'o';
                            points++;
                        }
                    }
                }
                break;
        }

        if (isInside()) {
            return true;
        }
        return false;
    }

    private static boolean meetApple() {
        return cube[snake.x][snake.y][snake.z] == 'a';
    }

    private static boolean isInside() {

        if (snake.x >= 0 && snake.x < n &&
                snake.y >= 0 && snake.y < n &&
                snake.z >= 0 && snake.z < n) {
            return true;
        }

        return false;
    }

    private static void readInput() throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.valueOf(bf.readLine());
        
        cube = new char[n][n][n];

        for (int y = 0; y < n; y++) {
            String[] tokens = bf.readLine().split("[\\|]");

            for (int x = 0; x < tokens.length; x++) {
                String currToken = tokens[x].trim();

                for (int z = 0; z < currToken.length(); z++) {
                    if (currToken.charAt(z) == 's') {
                        snake = new Snake(x, y, z);
                        cube[x][y][z] = 's';
                    } else {
                        cube[x][y][z] = currToken.charAt(z);
                    }
                }
            }
        }
    }
}

class Snake {
    int x;
    int y;
    int z;

    public Snake(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
