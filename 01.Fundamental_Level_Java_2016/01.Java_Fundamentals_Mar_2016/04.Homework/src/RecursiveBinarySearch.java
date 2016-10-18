import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RecursiveBinarySearch {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);

        int search = scn.nextInt();
        scn.nextLine();
        List<Integer> integers = Arrays.asList(scn.nextLine().split(" ")).stream().map(i -> Integer.parseInt(i)).collect(Collectors.toList());
        Collections.sort(integers);
        int start = 0;
        int end = integers.size() - 1;


        int result = binarySearch(integers, start, end, search);
        System.out.println(result);
    }

    private static int binarySearch(List<Integer> integers, int start, int end, int search) {
        int middle = (end + start) / 2;
        if (start > end) {
            return -1;
        }
        if (search == integers.get(middle)) {
            return middle;
        } else if (search < integers.get(middle)) {
            end = middle - 1;
            return binarySearch(integers, start, end, search);
        } else if (search > integers.get(middle)) {
            start = middle + 1;
            return binarySearch(integers, start, end, search);
        } else {
            return middle;
        }
    }
}
