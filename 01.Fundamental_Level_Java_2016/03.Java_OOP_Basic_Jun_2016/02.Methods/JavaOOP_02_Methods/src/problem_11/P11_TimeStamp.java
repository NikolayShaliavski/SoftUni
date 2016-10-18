package problem_11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class P11_TimeStamp {

    public static void main(String[] args) throws IOException {
        TimeStampList timeStampList = new TimeStampList();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            if (tokens[0].equals("Add")) {
                String name = tokens[1];
                timeStampList.addName(name);
            } else if (tokens[0].equals("Set")) {
                int index = Integer.valueOf(tokens[1]);
                String name = tokens[2];
                timeStampList.setNameAtGivenIndex(name, index);
            }
            line = reader.readLine();
        }
        List<String> names = timeStampList.getNames();
        List<Date> times = timeStampList.getModifiedTime();
        System.out.printf("Initial time -> %s%n", timeStampList.getInitTime().toString());
        for (int i = 0; i < names.size(); i++) {
            System.out.printf("Index %d: (%s) -> %s%n", i,
                    names.get(i),
                    times.get(i).toString());
        }
    }
}

class TimeStampList {

    List<String> names;
    List<Date> modifiedTimes;
    Date initTime;
    Date modifiedTime;

    public TimeStampList() {
        this.names = new LinkedList<>();
        this.modifiedTimes = new LinkedList<>();
        this.initTime = new Date();
    }

    public List<String> getNames() {
        return this.names;
    }

    public List<Date> getModifiedTime() {
        return this.modifiedTimes;
    }

    public Date getInitTime() {
        return this.initTime;
    }

    public void addName(String name) {
        this.names.add(name);
        this.modifiedTime = new Date();
        this.modifiedTimes.add(this.modifiedTime);
    }

    public void setNameAtGivenIndex(String name, int index) {
        this.names.set(index, name);
        this.modifiedTime = new Date();
        this.modifiedTimes.set(index, this.modifiedTime);
    }
}