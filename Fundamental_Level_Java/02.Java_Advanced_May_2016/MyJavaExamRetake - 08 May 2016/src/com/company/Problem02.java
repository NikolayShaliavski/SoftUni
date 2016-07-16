package com.company;
import java.math.BigDecimal;
import java.util.Scanner;

public class Problem02 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] dimentions = scan.nextLine().split(" ");

        String[] prices = scan.nextLine().split(" ");
        double lukanka = Double.parseDouble(prices[0]);
        double rakija = Double.parseDouble(prices[1]);

        String line = scan.nextLine();
        int counter = 0;
        BigDecimal amount = new BigDecimal(0);

        while (!line.equals("Royal Close")) {
            counter++;
            String[] position = line.split(" ");
            int row = Integer.parseInt(position[0]);
            int col = Integer.parseInt(position[1]);
            double price = 0D;
            if (row % 2 != 0) {
                price = (row + 1) * (col + 1) * rakija;
            } else {
                price = (row + 1) * (col + 1) * lukanka;
            }
            amount = amount.add(new BigDecimal(price));

            if (row < col) {
                for (int i = row - 1; i >= 0; i--) {
                    if (i % 2 != 0) {
                        price = (i + 1) * (col + 1) * rakija;
                    } else {
                        price = (i + 1) * (col + 1) * lukanka;
                    }
                    amount = amount.add(new BigDecimal(price));

                }
                for (int i = col - 1; i > 0; i--) {
                    price = (i + 1) * lukanka;
                    amount = amount.add(new BigDecimal(price));

                }

            } else {
                for (int i = col - 1; i >= 0; i--) {
                    if (row % 2 == 0) {
                        price = (row + 1) * (i + 1) * lukanka;
                        amount = amount.add(new BigDecimal(price));

                    } else {
                        price = (row + 1) * (i + 1) * rakija;
                        amount = amount.add(new BigDecimal(price));

                    }
                }
                for (int i = row - 1; i > 0; i--) {
                    if (i % 2 != 0) {
                        price = (i + 1) * rakija;
                        amount = amount.add(new BigDecimal(price));

                    } else{
                        price = (i + 1) * lukanka;
                        amount = amount.add(new BigDecimal(price));

                    }
                }
            }

            line = scan.nextLine();
        }
        System.out.printf("%.6f\n", amount);
        System.out.println(counter);
    }
}
