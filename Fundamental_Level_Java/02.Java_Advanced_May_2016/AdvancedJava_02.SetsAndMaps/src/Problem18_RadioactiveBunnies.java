import java.util.Scanner;

public class Problem18_RadioactiveBunnies {

    static boolean playerDie = false;
    static boolean leftLair = false;
    static int playerRow = 0;
    static int playerCol = 0;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] dimentions = scan.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimentions[0]);
        int cols = Integer.parseInt(dimentions[1]);

        char[][] lair = new char[rows][cols];

        for (int row = 0; row < lair.length; row++) {
            String currentRow = scan.nextLine();
            for (int col = 0; col < lair[row].length; col++) {
                lair[row][col] = currentRow.charAt(col);
            }
        }
        String commands = scan.nextLine();

        for (int i = 0; i < commands.length(); i++) {
            char command = commands.charAt(i);
            movePlayer(lair, command);
            spreadBunnies(lair);
            if (playerDie || leftLair) {
                break;
            }
        }
        printLair(lair);
        if (playerDie) {
            System.out.printf("dead: %d %d%n", playerRow, playerCol);
        } else {
            System.out.printf("won: %d %d%n", playerRow, playerCol);
        }
    }

    private static void movePlayer(char[][] lair, char command) {
        int currentRow = 0;
        int currentCol = 0;
        for (int row = 0; row < lair.length; row++) {
            for (int col = 0; col < lair[row].length; col++) {
                if (lair[row][col] == 'P') {
                    currentRow = row;
                    currentCol = col;
                }
            }
        }
        playerRow = currentRow;
        playerCol = currentCol;
        boolean moveUp = currentRow - 1 >= 0;
        boolean moveDown = currentRow + 1 < lair.length;
        boolean moveRight = currentCol + 1 < lair[0].length;
        boolean moveLeft = currentCol - 1 >= 0;

        switch (command) {
            case 'U':
                if (!moveUp) {
                    leftLair = true;
                    return;
                }
                if (moveUp) {
                    lair[currentRow][currentCol] = '.';
                    if (lair[currentRow - 1][currentCol] == 'B') {
                        playerDie = true;
                    } else {
                        lair[currentRow - 1][currentCol] = 'P';
                    }
                    playerRow = currentRow - 1;
                    playerCol = currentCol;
                }
                break;
            case 'D':
                if (!moveDown) {
                    leftLair = true;
                    return;
                }
                if (moveDown) {
                    lair[currentRow][currentCol] = '.';
                    if (lair[currentRow + 1][currentCol] == 'B') {
                        playerDie = true;
                    } else {
                        lair[currentRow + 1][currentCol] = 'P';
                    }
                    playerRow = currentRow + 1;
                    playerCol = currentCol;
                }
                break;
            case 'R':
                if (!moveRight) {
                   leftLair = true;
                    return;
                }
                if (moveRight) {
                    lair[currentRow][currentCol] = '.';
                    if (lair[currentRow][currentCol + 1] == 'B') {
                        playerDie = true;
                    } else {
                        lair[currentRow][currentCol + 1] = 'P';
                    }
                    playerRow = currentRow;
                    playerCol = currentCol + 1;
                }
                break;
            case 'L':
                if (!moveLeft) {
                    leftLair = true;
                    return;
                }
                if (moveLeft) {
                    lair[currentRow][currentCol] = '.';
                    if (lair[currentRow][currentCol - 1] == 'B') {
                        playerDie = true;
                    } else {
                        lair[currentRow][currentCol - 1] = 'P';
                    }
                    playerRow = currentRow;
                    playerCol = currentCol - 1;
                }
                break;
        }
    }

    private static void spreadBunnies(char[][] lair) {
        boolean bunnyUp;
        boolean bunnyDown;
        boolean bunnyRight;
        boolean bunnyLeft;

        int up = 0;
        int down = 0;
        int right = 0;
        int left = 0;

        for (int row = 0; row < lair.length; row++) {
            for (int col = 0; col < lair[row].length; col++) {
                if (lair[row][col] == 'B') {
                    up = row - 1;
                    down = row + 1;
                    right = col + 1;
                    left = col - 1;

                    bunnyUp = up >= 0;
                    bunnyDown = down < lair.length;
                    bunnyRight = right < lair[row].length;
                    bunnyLeft = left >= 0;

                    if (bunnyUp) {
                        if (lair[up][col] != 'B') {
                            playerHitted(lair, up, col);
                            lair[up][col] = 'S';
                        }
                    }
                    if (bunnyDown) {
                        if (lair[down][col] != 'B') {
                            playerHitted(lair, down, col);
                            lair[down][col] = 'S';
                        }
                    }
                    if (bunnyRight) {
                        if (lair[row][right] != 'B') {
                            playerHitted(lair, row, right);
                            lair[row][right] = 'S';
                        }
                    }
                    if (bunnyLeft) {
                        if (lair[row][left] != 'B') {
                            playerHitted(lair, row, left);
                            lair[row][left] = 'S';
                        }
                    }
                }
            }
        }

        for (int row = 0; row < lair.length; row++) {
            for (int col = 0; col < lair[row].length; col++) {
                if (lair[row][col] == 'S') {
                    lair[row][col] = 'B';
                }
            }
        }
    }

    private static void playerHitted(char[][] lair, int row, int col) {
        if (lair[row][col] == 'P' && !leftLair) {
            playerDie = true;
            playerRow = row;
            playerCol = col;
        }
    }
    private static void printLair(char[][] lair) {
        for (int row = 0; row < lair.length; row++) {
            for (int col = 0; col < lair[row].length; col++) {
                if (lair[row][col] == 'P') {
                    System.out.print(".");
                } else {
                    System.out.print(lair[row][col]);
                }
            }
            System.out.println();
        }
    }
}
