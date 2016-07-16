import java.util.Scanner;

public class CountAllWords {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        //"[^\\w\\d]+" -> this is pattern, that matches all non-word characters -> "+" matches all characters, if they are repeating
        String[] text = scn.nextLine().split("[^\\w\\d]+");
        System.out.println(text.length);
    }
}
