import java.util.Scanner;

public class CharacterTriangle {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int lines = input.nextInt();

        for (int i = 1; i <= lines; i++) {
            for (int j = 0; j < i; j++) {
                System.out.printf("%s ", (char)(j + 97));
            }
            System.out.println();
        }
        for (int i = lines - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.printf("%s ", (char)(j + 97));
            }
            System.out.println();
        }
        input.close();
    }
}
