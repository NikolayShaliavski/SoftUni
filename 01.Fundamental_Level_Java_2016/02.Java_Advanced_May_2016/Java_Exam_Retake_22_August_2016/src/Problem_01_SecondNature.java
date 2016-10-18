import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Problem_01_SecondNature {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> buckets = new Stack<>();
        LinkedList<Integer> flowers = new LinkedList<>();
        List<Integer> secondNature = new ArrayList<>();

        int[] flowersArr = Arrays.stream(reader.readLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        for (int flower : flowersArr) {
            flowers.add(flower);
        }
        int[] bucketsArr = Arrays.stream(reader.readLine().split(" ")).
                mapToInt(Integer::parseInt).toArray();
        for (int bucket : bucketsArr) {
            buckets.push(bucket);
        }

        while (!buckets.isEmpty() && !flowers.isEmpty()) {
            int currentFlower = flowers.peek();
            int currentBucket = buckets.pop();

            int remainingWater = currentBucket - currentFlower;
            if (remainingWater == 0) {
                secondNature.add(currentFlower);
                flowers.removeFirst();
            } else if (remainingWater > 0) {
                flowers.removeFirst();
                if (buckets.size() > 0) {
                    int bucketToFill = buckets.pop();
                    buckets.push(bucketToFill + remainingWater);
                } else {
                    buckets.push(remainingWater);
                }
            } else {
                int lastFlower = flowers.removeFirst();
                flowers.addFirst(lastFlower - currentBucket);
            }
        }

        List<Integer> remainingItems = new ArrayList<>();
        while (!flowers.isEmpty()) {
            remainingItems.add(flowers.removeFirst());
        }
        while (!buckets.isEmpty()) {
            remainingItems.add(buckets.pop());
        }
        String firstLine = buildOutput(remainingItems);
        String secondLine = "";
        if (secondNature.size() > 0) {
            secondLine = buildOutput(secondNature);
        } else {
            secondLine = "None";
        }
        System.out.println(firstLine);
        System.out.println(secondLine);
    }

    private static String buildOutput(List<Integer> items) {
        StringBuilder builder = new StringBuilder();
        for (Integer item : items) {
            builder.append(item).append(" ");
        }
        return builder.toString().trim();
    }
}
