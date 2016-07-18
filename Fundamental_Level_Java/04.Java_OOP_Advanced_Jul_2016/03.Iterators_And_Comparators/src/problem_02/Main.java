package problem_02;

import problem_02.models.CustomList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] collectionArgs = reader.readLine().split("\\s+");
        CustomList<String> myListImpl = initMyList(collectionArgs);

        String command = reader.readLine();
        while (!command.equals("END")) {
            executeCommand(command, myListImpl);

            command = reader.readLine();
        }
    }

    private static void executeCommand(String command, CustomList<String> myListImpl) {
        try {
            switch (command) {
                case "Move":
                    System.out.println(myListImpl.move());
                    break;
                case "HasNext":
                    System.out.println(myListImpl.hasNext());
                    break;
                case "Print":
                    System.out.println(myListImpl.print());
                    break;
                case "PrintAll":
                    iterateMyList(myListImpl);
                    break;
            }
        } catch (UnsupportedOperationException ioex){
            System.out.println(ioex.getMessage());
        }
    }

    private static void iterateMyList(CustomList<String> myListImpl) {
        for (String item : myListImpl) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    private static CustomList<String> initMyList(String[] collectionArgs) {
        String[] newCollection = new String[collectionArgs.length - 1];

        for (int i = 0; i < newCollection.length; i++) {
            newCollection[i] = collectionArgs[i + 1];
        }

        return new CustomList(newCollection);
    }

}
