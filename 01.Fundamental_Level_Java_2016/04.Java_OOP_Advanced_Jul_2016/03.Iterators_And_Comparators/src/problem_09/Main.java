package problem_09;

import problem_09.contracts.MyLinkedList;
import problem_09.models.MyLinkedListImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Nikolay Shalyavski on 26.7.2016 г..
 */
public class Main {

    public static void main(String[] args) throws IOException {

        MyLinkedList<Integer> myLinkedList = new MyLinkedListImpl<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLines = Integer.valueOf(reader.readLine());

        for (int i = 0; i < numberOfLines; i++) {
            String[] params = reader.readLine().split("[\\s]+");
            String command = params[0];
            int value = Integer.valueOf(params[1]);
            addRemoveValue(command, value, myLinkedList);
        }



        System.out.println(myLinkedList.getSize());
        for (Integer integer : myLinkedList) {
            System.out.print(integer + " ");
        }
        System.out.println();
    }

    private static void addRemoveValue(String command, int value, MyLinkedList<Integer> myLinkedList) {
        if (command.equals("Add")) {
            myLinkedList.add(value);
        } else if (command.equals("Remove")) {
            myLinkedList.remove(value);
        }
    }
}
