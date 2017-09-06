package pr03_SumTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SumTime {

    private static final String PATTERN = "[^0]+";
    private static int[] intervalSum = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] firstInterval = bf.readLine().split("::");
        String[] secondInterval = bf.readLine().split("::");

        if (firstInterval.length == 1) {
            addInterval(firstInterval[0]);
        } else {
            addInterval(firstInterval[0], firstInterval[1]);
        }

        if (secondInterval.length == 1) {
            addInterval(secondInterval[0]);
        } else {
            addInterval(secondInterval[0], secondInterval[1]);
        }
        StringBuilder sb = new StringBuilder();

        if (intervalSum[0] > 0) {
            sb.append(intervalSum[0]).append("::");
        }
        sb.append(intervalSum[1]).append(":");
        if (intervalSum[2] < 10) {
            sb.append("0");
        }
        sb.append(intervalSum[2]);
        System.out.println(sb.toString());
    }

    private static void addInterval(String daysStr, String singleDayStr) {
        int days = 0;
        if (daysStr.startsWith("0")) {
            String formattedDays = removeTrailingZeros(daysStr);
            days = Integer.valueOf(formattedDays);
        } else {
            days = Integer.valueOf(daysStr);
        }
        String[] singleDay = singleDayStr.split(":");
        int hours = 0;
        int minutes = 0;

        if (singleDay[0].startsWith("0")) {
            String formattedHours = removeTrailingZeros(singleDay[0]);
            hours = Integer.valueOf(formattedHours);
        } else {
            hours = Integer.valueOf(singleDay[0]);
        }

        if (singleDay[1].startsWith("0")) {
            String formattedMinutes = removeTrailingZeros(singleDay[1]);
            minutes = Integer.valueOf(formattedMinutes);
        } else {
            minutes = Integer.valueOf(singleDay[1]);
        }

        int minutesSum = intervalSum[2] + minutes;
        if (minutesSum > 59) {
            intervalSum[2] = minutesSum % 60;
            intervalSum[1] += minutesSum / 60;
        } else {
            intervalSum[2] = minutesSum;
        }

        int hoursSum = intervalSum[1] + hours;
        if (hoursSum > 23) {
            intervalSum[1] = hoursSum % 24;
            intervalSum[0] += hoursSum / 24;
        } else {
            intervalSum[1] = hoursSum;
        }

        intervalSum[0] += days;
    }

    private static void addInterval(String singleDayStr) {
        String[] singleDay = singleDayStr.split(":");
        int hours = 0;
        int minutes = 0;

        if (singleDay[0].startsWith("0")) {
            String formattedHours = removeTrailingZeros(singleDay[0]);
            hours = Integer.valueOf(formattedHours);
        } else {
            hours = Integer.valueOf(singleDay[0]);
        }

        if (singleDay[1].startsWith("0")) {
            String formattedMinutes = removeTrailingZeros(singleDay[1]);
            minutes = Integer.valueOf(formattedMinutes);
        } else {
            minutes = Integer.valueOf(singleDay[1]);
        }

        int minutesSum = intervalSum[2] + minutes;
        if (minutesSum > 59) {
            intervalSum[2] = minutesSum % 60;
            intervalSum[1] += minutesSum / 60;
        } else {
            intervalSum[2] = minutesSum;
        }

        int hoursSum = intervalSum[1] + hours;
        if (hoursSum > 23) {
            intervalSum[1] = hoursSum % 24;
            intervalSum[0] += hoursSum / 24;
        } else {
            intervalSum[1] = hoursSum;
        }
    }

    private static String removeTrailingZeros(String line) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            return "0";
        }

        int startIndex = 0;
        while (line.charAt(startIndex) == '0') {
            startIndex++;
        }
        return line.substring(startIndex);
    }
}
