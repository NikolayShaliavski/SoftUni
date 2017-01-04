package pr09_FastAndFurious;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class FastAndFurious {

    static Map<String, Town> towns;
    static Map<String, List<Edge>> edges;
    static Map<String, List<Record>> records;
    static Set<String> bustedCars;

    public static void main(String[] args) throws IOException {

        readInput();
        bustedCars = new TreeSet<>();

        for (Map.Entry<String, List<Record>> recEntry : records.entrySet()) {
            String car = recEntry.getKey();
            List<Record> carRecords = recEntry.getValue();
            Collections.sort(carRecords);

            for (int i = 0; i < carRecords.size() - 1; i++) {
                Record source = carRecords.get(i);
                Record destination = carRecords.get(i + 1);

                Double travelTime = findTravelTime(source, destination);
                Double allowedTime = findAllowedTime(source.town, destination.town);

                if (allowedTime != Double.MAX_VALUE && travelTime < allowedTime) {
                    bustedCars.add(car);
                    break;
                }
            }
        }
        for (String busted : bustedCars) {
            System.out.println(busted);
        }
    }

    private static Double findAllowedTime(String source, String destination) {

        for (Town town : towns.values()) {
            town.timeFromStart = Double.MAX_VALUE;
        }

        PriorityQueue<Town> townPriorities = new PriorityQueue<>();
        towns.get(source).timeFromStart = 0.0;
        townPriorities.add(towns.get(source));

        while (townPriorities.size() > 0) {
            Town currTown = townPriorities.poll();
            
            if (currTown.name.equals(destination)) {
                break;
            }

            for (Edge edge : edges.get(currTown.name)) {
                Town nextTown = towns.get(edge.destination.name);
                Double newTime = currTown.timeFromStart + edge.time;

                if (newTime < nextTown.timeFromStart) {
                    nextTown.timeFromStart = newTime;
                    townPriorities.add(nextTown);
                }
            }
        }
        return towns.get(destination).timeFromStart;
    }

    private static Double findTravelTime(Record source, Record destination) {
        Integer hours = destination.hours - source.hours;
        Double minutes;
        if (destination.minutes >= source.minutes) {
            minutes = (double) destination.minutes - source.minutes;
        } else {
            hours--;
            minutes = (double) destination.minutes;
            minutes += 60 - source.minutes;
        }

        Double seconds;
        if (destination.seconds >= source.seconds) {
            seconds = (double) destination.seconds - source.seconds;
        } else {
            minutes--;
            seconds = (double) destination.seconds;
            seconds += 60 - source.seconds;
        }
        seconds /= 3600;
        minutes /= 60;
        minutes += seconds;
        return hours + minutes;
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        towns = new HashMap<>();
        edges = new HashMap<>();
        records = new LinkedHashMap<>();

        String line = bf.readLine();
        line = bf.readLine();

        while (!line.equals("Records:")) {
            String[] tokens = line.split("[\\s]+");

            Double distance = Double.valueOf(tokens[2]);
            Double speed = Double.valueOf(tokens[3]);

            Town firstTown = new Town(tokens[0]);
            Town secondTown = new Town(tokens[1]);
            Edge firstEdge = new Edge(firstTown, distance, speed);
            Edge secondEdge = new Edge(secondTown, distance, speed);

            if (!towns.containsKey(firstTown.name)) {
                towns.put(firstTown.name, firstTown);
            }
            if (!towns.containsKey(secondTown.name)) {
                towns.put(secondTown.name, secondTown);
            }
            if (!edges.containsKey(firstTown.name)) {
                edges.put(firstTown.name, new ArrayList<>());
            }
            if (!edges.containsKey(secondTown.name)) {
                edges.put(secondTown.name, new ArrayList<>());
            }
            edges.get(firstTown.name).add(secondEdge);
            edges.get(secondTown.name).add(firstEdge);
            line = bf.readLine();
        }
        line = bf.readLine();
        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            String townName = tokens[0];
            String car = tokens[1];
            String[] timeTokens = tokens[2].split("\\:");
            Integer[] parsedTime = parseTime(timeTokens);

            Record record = new Record(townName, parsedTime[0], parsedTime[1], parsedTime[2]);
            if (!records.containsKey(car)) {
                records.put(car, new ArrayList<>());
            }
            records.get(car).add(record);
            line = bf.readLine();
        }
    }

    private static Integer[] parseTime(String[] timeTokens) {
        Integer[] time = new Integer[timeTokens.length];

        for (int i = 0; i < timeTokens.length; i++) {
            String timeToken = timeTokens[i];
            Integer parsed;
            if (timeToken.equals("00")) {
                parsed = 0;
            } else if (timeToken.startsWith("0")) {
                parsed = Integer.parseInt(timeToken.charAt(1) + "");
            } else {
                parsed = Integer.parseInt(timeToken);
            }
            time[i] = parsed;
        }
        return time;
    }
}

class Record implements Comparable<Record> {

    String town;
    Integer hours;
    Integer minutes;
    Integer seconds;

    public Record(String town, Integer hours, Integer minutes, Integer seconds) {
        this.town = town;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public int compareTo(Record other) {
        int result = this.hours.compareTo(other.hours);
        if (result == 0) {
            result = this.minutes.compareTo(other.minutes);
        }
        if (result == 0) {
            result = this.seconds.compareTo(other.seconds);
        }
        return result;
    }
}

class Town implements Comparable<Town> {

    String name;
    Double timeFromStart;

    public Town(String name) {
        this.name = name;
        this.timeFromStart = Double.MAX_VALUE;
    }

    @Override
    public int compareTo(Town other) {
        return this.timeFromStart.compareTo(other.timeFromStart);
    }
}

class Edge {

    Town destination;
    Double time;

    public Edge(Town destination, Double distance, Double speed) {
        this.destination = destination;
        this.time = distance / speed;
    }
}