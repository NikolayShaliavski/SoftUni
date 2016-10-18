package com.company;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class Problem06_TruckTour {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int numberOfStations = Integer.parseInt(scan.nextLine());
        Queue<Integer> petrolInStation = new ArrayDeque<>();
        Queue<Integer> kilometres = new ArrayDeque<>();
        int index = 0;

        for (int i = 0; i < numberOfStations; i++) {
            String[] currentStation = scan.nextLine().split("\\s+");
            petrolInStation.add(Integer.parseInt(currentStation[0]));
            kilometres.add(Integer.parseInt(currentStation[1]));
        }

        for (int i = 0; i < numberOfStations; i++) {
            long tanker = 0;
            boolean fullCircle = true;
            int petrol;
            int distance;

            for (int j = 0; j < numberOfStations; j++) {
                petrol = petrolInStation.poll();
                distance = kilometres.poll();
                tanker += petrol;
                tanker -= distance;
                petrolInStation.add(petrol);
                kilometres.add(distance);
                if (tanker < 0) {
                    fullCircle = false;
                }
            }
            if (fullCircle) {
                System.out.println(index);
                break;
            } else {
                petrol = petrolInStation.poll();
                distance = kilometres.poll();
                petrolInStation.add(petrol);
                kilometres.add(distance);
                index++;
            }
        }
    }
}
