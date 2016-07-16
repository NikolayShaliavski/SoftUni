import java.util.Scanner;

public class CollectResources {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        String[] resorces = console.nextLine().split("[\\s]+");

        int numberOfPaths = console.nextInt();
        console.nextLine();
        int result = 0;

        for (int i = 0; i < numberOfPaths; i++) {
            int collected = 0;
            boolean[] toCheck = new boolean[resorces.length];
            String[] indexes = console.nextLine().split("[\\s]+");

            int index = Integer.parseInt(indexes[0]);
            int step = Integer.parseInt(indexes[1]) % resorces.length;

            while (!toCheck[index]) {
                String[] currentResource = resorces[index].split("_");
                int countOfResources = 0;
                if (currentResource.length == 1) {
                    countOfResources = 1;
                } else {
                    countOfResources = Integer.parseInt(currentResource[1]);
                }

                switch (currentResource[0]) {
                    case "stone":
                    case "gold":
                    case "wood":
                    case "food":
                        collected += countOfResources;
                        toCheck[index] = true;
                }
                if (index + step < resorces.length) {
                    index += step;
                } else {
                    index = step - (resorces.length - index);
                }
            }
            if (collected > result) {
                result = collected;
            }
        }
        System.out.println(result);
    }
}
