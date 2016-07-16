package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        HashMap<String, TreeMap<String, ArrayList<String>>> report = new HashMap<>();
        TreeMap<String, Integer> sorted = new TreeMap<>();
        Pattern pattern = Pattern.compile("\\[\"(.*)\"\\].*?\\[\"(Critical|Warning)\"\\].*?\\[\"(.*)\"\\]");
        Matcher matcher;
        while (!line.equals("END")) {
            matcher = pattern.matcher(line);

            while (matcher.find()) {
                String projectName = matcher.group(1);
                String errorType = matcher.group(2);
                String error = matcher.group(3);

                if (!report.containsKey(projectName)) {
                    report.put(projectName, new TreeMap<>());
                    report.get(projectName).put("Critical", new ArrayList<>());
                    report.get(projectName).put("Warning", new ArrayList<>());
                }
                report.get(projectName).get(errorType).add(error);
                if (!sorted.containsKey(projectName)) {
                    sorted.put(projectName, 1);
                } else {
                    sorted.put(projectName, sorted.get(projectName).intValue() + 1);
                }
            }
            line = scan.nextLine();
        }
        ArrayList<String> finalSorted = new ArrayList<>();
        ArrayList<Integer> countErrors = new ArrayList<>();
        sorted.entrySet().stream().sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())).forEach(k1 -> countErrors.add(k1.getValue()));
        sorted.entrySet().stream().sorted((k1, k2) -> k2.getValue().compareTo(k1.getValue())).forEach(k1 -> finalSorted.add(k1.getKey()));

        for (int i = 0; i < finalSorted.size(); i++) {
            System.out.println(finalSorted.get(i) + ":");
            System.out.println("Total Errors: " + countErrors.get(i));
            System.out.println("Critical: " + report.get(finalSorted.get(i)).get("Critical").size());
            System.out.println("Warnings: " + report.get(finalSorted.get(i)).get("Warning").size());
            System.out.println("Critical Messages:");
            if (report.get(finalSorted.get(i)).get("Critical").size() == 0) {
                System.out.println("--->None");
            } else {
                List<String> errorMessages = report.get(finalSorted.get(i)).get("Critical");
                Collections.sort(errorMessages);
                Collections.sort(errorMessages, new Comparator<String>() {

                    @Override
                    public int compare(String o1, String o2) {
                        if(o1.length() != o2.length()) {
                            return o1.length() - o2.length();
                        }else{
                            return o1.compareTo(o2);
                        }
                    }
                });
                for (String errorMessage : errorMessages) {
                    System.out.println("--->" + errorMessage);
                }
            }
            System.out.println("Warning Messages:");
            if (report.get(finalSorted.get(i)).get("Warning").size() == 0) {
                System.out.println("--->None");
            } else {
                List<String> errorMessages = report.get(finalSorted.get(i)).get("Warning");
                //Collections.sort(errorMessages);
                Collections.sort(errorMessages, new Comparator<String>() {

                    @Override
                    public int compare(String o1, String o2) {
                        if(o1.length() != o2.length()) {
                            return o1.length() - o2.length();
                        }else{
                            return o1.compareTo(o2);
                        }
                    }
                });
                for (String errorMessage : errorMessages) {
                    System.out.println("--->" + errorMessage);
                }
            }
            if (i < finalSorted.size() - 1) {
                System.out.println();
            }
        }
    }
}
