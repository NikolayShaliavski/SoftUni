package problem_09;

import problem_09.collections.AddCollectionImpl;
import problem_09.collections.AddRemoveCollectionImpl;
import problem_09.collections.MyListImpl;
import problem_09.interfaces.AddCollection;
import problem_09.interfaces.AddRemoveCollection;
import problem_09.interfaces.MyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements = reader.readLine().split("\\s+");
        int timesToRemove = Integer.valueOf(reader.readLine());

        AddCollection addCollection = new AddCollectionImpl();
        AddRemoveCollection addRemoveCollection = new AddRemoveCollectionImpl();
        MyList myList = new MyListImpl();

        StringBuilder builder = new StringBuilder();
        for (String element : elements) {
            builder.append(addCollection.add(element) + " ");
        }
        String firstOut = builder.toString().trim();
        builder.delete(0, builder.length());
        for (String element : elements) {
            builder.append(addRemoveCollection.add(element) + " ");
        }
        String secondOut = builder.toString().trim();
        builder.delete(0, builder.length());
        for (String element : elements) {
            builder.append(myList.add(element) + " ");
        }
        String thirdOut = builder.toString().trim();
        builder.delete(0, builder.length());

        for (int i = 0; i < timesToRemove; i++) {
            builder.append(addRemoveCollection.remove() + " ");
        }
        String fourthOut = builder.toString().trim();
        builder.delete(0, builder.length());
        for (int i = 0; i < timesToRemove; i++) {
            builder.append(myList.remove() + " ");
        }
        String fifthOut = builder.toString().trim();
        System.out.println(firstOut);
        System.out.println(secondOut);
        System.out.println(thirdOut);
        System.out.println(fourthOut);
        System.out.println(fifthOut);
    }
}
