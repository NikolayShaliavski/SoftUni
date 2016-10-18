package problem_10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class P10_DateModifier {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstDate = reader.readLine();
        String secondDate = reader.readLine();

        DateModifier dateModifier = new DateModifier();
        System.out.println(dateModifier.calculateDifference(firstDate, secondDate));
    }

}

class DateModifier {

    private long difference;

    public DateModifier() {

    }

    public long calculateDifference(String startDate, String endDate) {
        String[] tokens01 = startDate.split("[\\s]+");
        String[] tokens02 = endDate.split("[\\s]+");
        Calendar calendar01 = Calendar.getInstance();
        Calendar calendar02 = Calendar.getInstance();

        calendar01.set(Integer.valueOf(tokens01[0]),
                Integer.valueOf(tokens01[1]),
                Integer.valueOf(tokens01[2]));

        calendar02.set(Integer.valueOf(tokens02[0]),
                Integer.valueOf(tokens02[1]),
                Integer.valueOf(tokens02[2]));

        Date date01 = calendar01.getTime();
        Date date02 = calendar02.getTime();

        this.difference = Math.abs(TimeUnit.DAYS.convert((
                        date01.getTime() - date02.getTime()),
                TimeUnit.MILLISECONDS));
        return this.difference;
    }
}