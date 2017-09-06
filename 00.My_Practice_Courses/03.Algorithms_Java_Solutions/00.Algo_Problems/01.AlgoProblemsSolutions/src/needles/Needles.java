package needles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Needles {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] sequencesSize = bf.readLine().split(" ");
        int arrSize = Integer.valueOf(sequencesSize[0]);
        int needlesSize = Integer.valueOf(sequencesSize[1]);

        int[] arr = new int[arrSize];
        int[] needles = new int[needlesSize];

        String[] arrStrings = bf.readLine().split(" ");
        for (int i = 0; i < arrSize; i++) {
            arr[i] = Integer.valueOf(arrStrings[i]);
        }
        String[] needlesStrings = bf.readLine().split(" ");
        for (int i = 0; i < needlesSize; i++) {
            needles[i] = Integer.valueOf(needlesStrings[i]);
        }

        for (int i = 0; i < needles.length; i++) {
            int needle = needles[i];
            for (int j = 0; j < arr.length; j++) {
                int element = arr[j];
                if (needle <= element) {
                    int index = j - 1;
                    while (index >= 0 && arr[index] == 0) {
                        index--;
                    }
                    System.out.print(index + 1 + " ");
                    break;
                }
                if (j == arr.length - 1) {
                    int index = j;
                    while (index >= 0 && arr[index] == 0) {
                        index--;
                    }
                    System.out.print(index + 1 + " ");
                }
            }
        }
    }
}
