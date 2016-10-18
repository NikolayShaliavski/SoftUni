package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GUnit {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        TreeMap<String, TreeMap<String, ArrayList<String>>> report = new TreeMap<>();

        while (!line.equals("It's testing time!")) {
            Pattern pattern = Pattern.compile("^([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+) \\| ([A-Z][A-Za-z0-9]+)$");
            Matcher matcher = pattern.matcher(line);

            if (matcher.find()) {
                String className = matcher.group(1);
                String methodName = matcher.group(2);
                String unitTest = matcher.group(3);

                if (!report.containsKey(className)) {
                    report.put(className, new TreeMap<>());
                }
                if (!report.get(className).containsKey(methodName)) {
                    report.get(className).put(methodName, new ArrayList<>());
                }
                if (!report.get(className).get(methodName).contains(unitTest)) {
                    report.get(className).get(methodName).add(unitTest);//if unit test already exists, we ignore the line
                }
            }
            line = scan.nextLine();
        }
        ArrayList<String> sortedClasses = new ArrayList<>();//list to save classes after sorting
        report.entrySet().stream().sorted((k1, k2) -> {
            Integer result = getTests(k2.getValue()).compareTo(getTests(k1.getValue()));//sorting classes by number of unit tests (getTests method)

            if (result == 0) {//if 2 classes have same amount of tests, sort them by number of their methods (getMethods method)
                result = getMethods(k1.getValue()).compareTo(getMethods(k2.getValue()));
            }
            return result;//we don't need to sort them alphabetically, if the second sort returns zero, because they are in TreeMap (already sorted)
        }).forEach(k1 -> sortedClasses.add(k1.getKey()));
        for (String sortedClass : sortedClasses) {//iterating over ArrayList of sorted classes
            System.out.println(sortedClass + ":");
            TreeMap<String, ArrayList<String>> sortedMethods = report.get(sortedClass);//get TreeMap with methods and tests for every class
            ArrayList<String> methods = new ArrayList<>();//ArrayList to save methods after sorting
            sortedMethods.entrySet().stream().sorted((k1, k2) -> {
                Integer size1 = k2.getValue().size();
                Integer size2 = k1.getValue().size();
                return size1.compareTo(size2);//sorting methods by number of their unit tests
            }).forEach(k1 -> methods.add(k1.getKey()));
            for (String method : methods) {//iterating over ArrayList of sorted methods
                System.out.println("##" + method);
                ArrayList<String> tests = sortedMethods.get(method);//get unit tests for every method
                tests.stream().sorted((k1, k2) -> {//sorting tests
                    Integer length1 = k1.length();
                    Integer length2 = k2.length();
                    Integer result = length1.compareTo(length2);//first by they length
                    if (result == 0) {
                        result = k1.compareTo(k2);//second alphabetically, because they are not sorted yet
                    }
                    return result;
                }).forEach(k1 -> System.out.printf("####%s\n", k1));
            }
        }
    }

    public static Integer getTests(TreeMap<String, ArrayList<String>> classes) {//method to get number of tests for every class
        Integer numberOfTests = 0;//we have to return Integer class (not int) to use compareTo() method
        for (ArrayList currentClass : classes.values()) {
            int currentMethod = currentClass.size();
            numberOfTests += currentMethod;
        }
        return numberOfTests;
    }

    public static Integer getMethods(TreeMap<String, ArrayList<String>> methods) {//method to get number of methods for every class
        return methods.keySet().size();//again return Integer class
    }
}
