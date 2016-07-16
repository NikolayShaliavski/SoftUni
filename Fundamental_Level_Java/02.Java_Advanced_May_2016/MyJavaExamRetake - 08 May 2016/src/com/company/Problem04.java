package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Problem04 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        Pattern pattern = Pattern.compile("^ *([A-Za-z]+) *; *(-*[0-9]*) *; *(-*[0-9]*\\.*[0-9]*) *; *([A-Za-z]+) *$");
        ArrayList<String> employees = new ArrayList<>();
        HashMap<String, Double> teams = new HashMap<>();
        HashMap<String, TreeMap<String, ArrayList<Double>>> names = new HashMap<>();

        while (!line.equals("Pishi kuf i da si hodim")) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String name = matcher.group(1);
                int workHours = Integer.parseInt(matcher.group(2));
                double payment = Double.parseDouble(matcher.group(3));
                String team = matcher.group(4);

                if (!employees.contains(name)) {
                    employees.add(name);

                    if (!names.containsKey(team)) {
                        names.put(team, new TreeMap<>());
                    }
                    names.get(team).put(name, new ArrayList<>());
                    names.get(team).get(name).add((double) workHours);
                    names.get(team).get(name).add(payment);
                }
            }
            line = scan.nextLine();
        }
        for (String team : names.keySet()) {
            TreeMap<String, ArrayList<Double>> currentTeam = names.get(team);
            double total = 0D;
            for (ArrayList<Double> list : currentTeam.values()) {
                double hours = list.get(0).intValue();
                double payment = list.get(1);
                double monthly = (hours * payment) / 24;
                total += monthly * 30;
            }
            teams.put(team, total);
        }
        ArrayList<String> sortedTeams = new ArrayList<>();
        teams.entrySet().stream().sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())).forEach(k1 -> sortedTeams.add(k1.getKey()));

        for (String sortedTeam : sortedTeams) {
            System.out.println("Team - " + sortedTeam);
            TreeMap<String, ArrayList<Double>> currentTeam = names.get(sortedTeam);
            currentTeam.entrySet().stream().sorted((k1, k2) -> {
                int result = k2.getValue().get(0).compareTo(k1.getValue().get(0));

                if (result == 0) {
                    Double daily1 = (k2.getValue().get(0).intValue() * k2.getValue().get(1)) / 24;
                    //daily1 = daily1.divide(new BigDecimal("24"));
                    Double daily2 = (k1.getValue().get(0).intValue() * k1.getValue().get(1)) / 24;
                    //daily2 = daily2.divide(new BigDecimal("24"));
                    result = daily1.compareTo(daily2);
                }
                return result;
            }).forEach(k1 -> {
                double toPrint = (k1.getValue().get(0).intValue() * k1.getValue().get(1)) / 24;
                System.out.printf("$$$%s - %d - %.6f\n", k1.getKey(), k1.getValue().get(0).intValue(), toPrint);
            });
        }
    }
}
