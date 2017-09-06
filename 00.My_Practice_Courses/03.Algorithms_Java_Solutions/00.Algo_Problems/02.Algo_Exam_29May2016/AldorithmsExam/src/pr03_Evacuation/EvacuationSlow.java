package pr03_Evacuation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Solution is slow because it uses Floyd-Warshall algorithm
 * and calculates best distances from each node to all other nodes
 * not only to exits
 */
public class EvacuationSlow {

    static Double[][] rooms;
    static Set<Integer> exits;
    static Double timeToEvacuate;
    static int slowestRoom;
    static Double slowestTime;
    static Map<Integer, Double> unsafeRooms;

    public static void main(String[] args) throws IOException {
        readInput();

        floydWarshallAlgorithm();

        checkForUnsafeRooms();

        if (unsafeRooms.size() > 0) {
            System.out.println("Unsafe");
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Integer, Double> room : unsafeRooms.entrySet()) {
                String time;
                if (room.getValue() == Double.POSITIVE_INFINITY) {
                    time = "unreachable";
                } else {
                    time = convertTime(room.getValue().intValue());
                }
                sb.append(String.format("%d (%s), ", room.getKey(), time));
            }
            System.out.println(sb.substring(0, sb.length() - 2));
        } else {
            System.out.println("Safe");
            String time = convertTime(slowestTime.intValue());
            System.out.printf("%d (%s)%n", slowestRoom, time);
        }
        //printRooms();
    }

    private static String convertTime(int time) {
        StringBuilder sb = new StringBuilder();

        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time % 60;

        if (hours < 10) {
            sb.append("0").append(hours).append(":");
        } else {
            sb.append(hours).append(":");
        }
        if (minutes < 10) {
            sb.append("0").append(minutes).append(":");
        } else {
            sb.append(minutes).append(":");
        }
        if (seconds < 10) {
            sb.append("0").append(seconds);
        } else {
            sb.append(seconds);
        }
        return sb.toString();
    }

    private static void checkForUnsafeRooms() {
        int roomsCount = rooms.length;
        unsafeRooms = new TreeMap<>();
        slowestTime = Double.MIN_VALUE;
        slowestRoom = 0;

        for (int room = 0; room < roomsCount; room++) {
            if (exits.contains(room)) {
                continue;
            }
            Double roomBestTime = Double.MAX_VALUE;
            boolean unreachable = true;
            for (Integer exit : exits) {
                if (rooms[room][exit] != Double.POSITIVE_INFINITY) {
                    unreachable = false;
                }
                if (rooms[room][exit] < roomBestTime) {
                    roomBestTime = rooms[room][exit];
                }
                if (rooms[room][exit] == slowestTime && room < exit) {
                    slowestRoom = room;
                }
            }
            if (unreachable) {
                unsafeRooms.put(room, Double.POSITIVE_INFINITY);
                continue;
            }
            if (roomBestTime != Double.MAX_VALUE && roomBestTime > timeToEvacuate) {
                unsafeRooms.put(room, roomBestTime);
            }
            if (roomBestTime != Double.MAX_VALUE && roomBestTime > slowestTime) {
                slowestTime = roomBestTime;
                slowestRoom = room;
            }
        }
    }

    private static void floydWarshallAlgorithm() {
        int roomsCount = rooms.length;

        for (int k = 0; k < roomsCount; k++) {
            for (int i = 0; i < roomsCount; i++) {
                for (int j = 0; j < roomsCount; j++) {
                    if (rooms[k][i] + rooms[k][j] < rooms[i][j]) {
                        rooms[i][j] = rooms[k][i] + rooms[k][j];
                    }
                }
            }
        }
    }

    private static void printRooms() {

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if (rooms[i][j] == Double.POSITIVE_INFINITY) {
                    System.out.printf("%5.0f", -1.0);
                } else {
                    System.out.printf("%5.0f", rooms[i][j]);
                }
            }
            System.out.println();
        }
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int numberOfRooms = Integer.valueOf(bf.readLine());
        rooms = new Double[numberOfRooms][numberOfRooms];

        for (int i = 0; i < numberOfRooms; i++) {
            for (int j = 0; j < numberOfRooms; j++) {
                if (i == j) {
                    rooms[i][j] = 0.0;
                } else {
                    rooms[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        exits = new HashSet<>();
        String[] exitRooms = bf.readLine().split("[\\s]+");
        for (String exitRoom : exitRooms) {
            exits.add(Integer.valueOf(exitRoom));
        }
        int connections = Integer.valueOf(bf.readLine());
        for (int i = 0; i < connections; i++) {
            String[] tokens = bf.readLine().split("[\\s]+");
            int source = Integer.valueOf(tokens[0]);
            int destination = Integer.valueOf(tokens[1]);

            double time = parseTime(tokens[2]);
            rooms[source][destination] = time;
            rooms[destination][source] = time;
        }
        timeToEvacuate = parseTime(bf.readLine());
    }

    private static double parseTime(String token) {
        String[] timeTokens = token.split("[\\:]");
        int[] timeArr = new int[2];
        for (int i = 0; i < timeArr.length; i++) {
            String timeValue = timeTokens[i];
            if (timeValue.equals("00")) {
                timeArr[i] = 0;
            } else if (timeValue.startsWith("0")) {
                timeArr[i] = Integer.valueOf(timeValue.charAt(1) + "");
            } else {
                timeArr[i] = Integer.valueOf(timeValue);
            }
        }
        return timeArr[0] * 60.0 + timeArr[1];
    }
}
