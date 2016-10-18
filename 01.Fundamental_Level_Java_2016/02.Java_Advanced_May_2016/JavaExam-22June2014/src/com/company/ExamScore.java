package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExamScore {


    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String line;
        TreeMap<Integer, List<String>> students = new TreeMap<>();
        HashMap<Integer, Double> grades = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            line = scan.nextLine();
        }

        line = scan.nextLine();
        while (line.startsWith("|")) {
            Pattern pattern = Pattern.compile("([A-Za-z]+\\s*[A-Za-z]+)\\s*\\|*\\s*(\\d+)\\s*\\|*\\s*([\\d]\\.[\\d]+)");
            Matcher matcher = pattern.matcher(line);

            matcher.find();
            String name = matcher.group(1);
            int score = Integer.parseInt(matcher.group(2));
            double grade = Double.parseDouble(matcher.group(3));

            if (!students.containsKey(score)) {
                students.put(score, new ArrayList<String>());
            }
            students.get(score).add(name);
            if (!grades.containsKey(score)) {
                grades.put(score, grade);
            } else {
                grades.put(score, grades.get(score) + grade);
            }
            line = scan.nextLine();
        }
        for (Map.Entry score : students.entrySet()) {
            // for every score we get list of students with this score, cast it to a list, because map.Entry is Object!!!!!!!!
            List<String> sorted = new ArrayList<String>((List)score.getValue());
            System.out.printf("%d -> ", score.getKey());
            //sorting alphabetically and printing students for each score
            System.out.print(sorted.stream().sorted().collect(Collectors.toList()) + "; avg=");
            double grade = grades.get(score.getKey()).doubleValue();
            double average = grade / sorted.size();
            //only test 7 isn't correct -> because of rounding -> my output is 5.44, not 5.45, but in authors code is used same format -> %.2f
            System.out.printf("%.2f\n", average);
        }
    }
}
