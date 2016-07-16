package problem_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P04_BeerCounter {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            int beersBought = Integer.valueOf(tokens[0]);
            int beersDrunk = Integer.valueOf(tokens[1]);
            BeerCounter.buyBeer(beersBought);
            BeerCounter.drinkBeer(beersDrunk);

            line = reader.readLine();
        }
        System.out.printf("%d %d%n", BeerCounter.getBeerInStock(),
                BeerCounter.getBeersDrunkCount());
    }
}

class BeerCounter {

    private static int beerInStock = 0;
    private static int beersDrunkCount = 0;

    public static int getBeerInStock() {
        return beerInStock;
    }

    public static int getBeersDrunkCount() {
        return beersDrunkCount;
    }

    public static void buyBeer(int bottles) {
        beerInStock += bottles;
    }

    public static void drinkBeer(int bottles) {
        beersDrunkCount += bottles;
        beerInStock -= bottles;
    }
}