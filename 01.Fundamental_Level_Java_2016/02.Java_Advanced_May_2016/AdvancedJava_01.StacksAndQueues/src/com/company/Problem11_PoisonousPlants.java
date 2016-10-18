package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Problem11_PoisonousPlants {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfPlants = Integer.parseInt(reader.readLine());
        Queue<Integer> garden = new ArrayDeque<>();
        Queue<Integer> plantsAlive = new ArrayDeque<>();

        String[] plants = reader.readLine().split(" ");

        for (int i = 0; i < plants.length; i++) {
            garden.add(Integer.parseInt(plants[i]));
        }
        boolean plantsDie = true;
        int days = 0;
        while (plantsDie) {

            plantsDie = false;
            int gardenSize = garden.size();
            plantsAlive.add(garden.peek());
            for (int i = 0; i < gardenSize - 1; i++) {
                int leftPlant = garden.poll();
                int rightPlant = garden.peek();

                if (rightPlant > leftPlant) {
                    plantsDie = true;
                } else {
                    plantsAlive.add(rightPlant);
                }
            }
            garden.clear();
            garden.addAll(plantsAlive);
            plantsAlive.clear();
            days++;
        }
        System.out.println(days - 1);
    }
}
