package pr05_ParkingZone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParkingZone {

    static Zone[] zones;
    static List<ParkCell> parkCells;
    static ParkCell market;
    static int k;

    public static void main(String[] args) throws IOException {
        readInput();
        ParkCell bestCell = null;
        for (Zone zone : zones) {
            for (ParkCell parkCell : zone.parkCells) {
                long time = calculateTime(parkCell);
                parkCell.price = time * zone.price;

                if (bestCell == null) {
                    bestCell = parkCell;
                } else if (parkCell.price < bestCell.price) {
                    bestCell = parkCell;
                } else if (parkCell.price == bestCell.price) {
                    if (parkCell.time < bestCell.time) {
                        bestCell =  parkCell;
                    }
                }
            }
        }

        System.out.printf("Zone Type: %s; X: %d; Y: %d; Price: %.2f%n",
                bestCell.zone, bestCell.x, bestCell.y, bestCell.price);
    }

    private static long calculateTime(ParkCell parkCell) {
        int xDist = Math.abs(parkCell.x - market.x);
        int yDist = Math.abs(parkCell.y - market.y);

        int distance = (xDist + yDist - 1) * 2;
        long timeInSeconds = distance * k;
        parkCell.time = timeInSeconds;

        double time = timeInSeconds / 60.0;
        long ceiledMinutes = (long) Math.ceil(time);

        return ceiledMinutes;
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int zonesCount = Integer.valueOf(bf.readLine());

        zones = new Zone[zonesCount];
        for (int i = 0; i < zonesCount; i++) {
            String[] tokens = bf.readLine().split("\\:");
            String zoneName = tokens[0];
            String[] zoneTokens = tokens[1].trim().split("[\\s\\,]+");
            int[] zoneParams = new int[zoneTokens.length - 1];
            for (int j = 0; j < zoneParams.length; j++) {
                zoneParams[j] = Integer.valueOf(zoneTokens[j]);
            }
            double zonePrice = Double.valueOf(zoneTokens[4]);

            Zone zone =
                    new Zone(zoneName, zoneParams[0], zoneParams[1],
                            zoneParams[2], zoneParams[3], zonePrice);

            zones[i] = zone;
        }
        parkCells = new ArrayList<>();
        String[] cellTokens = bf.readLine().split("; ");

        for (int i = 0; i < cellTokens.length; i++) {
            String[] cellParams = cellTokens[i].split(", ");
            int x = Integer.valueOf(cellParams[0]);
            int y = Integer.valueOf(cellParams[1]);

            ParkCell parkCell = new ParkCell(x, y);
            for (Zone zone : zones) {
                if (zone.tryAddParkCell(parkCell)) {
                    break;
                }
            }
        }
        String[] marketTokens = bf.readLine().split(", ");
        int marketX = Integer.valueOf(marketTokens[0]);
        int marketY = Integer.valueOf(marketTokens[1]);
        market = new ParkCell(marketX, marketY);

        k = Integer.valueOf(bf.readLine());
    }
}

class Zone {

    String name;
    int x1;
    int y1;
    int x2;
    int y2;
    double price;
    List<ParkCell> parkCells;

    public Zone(String name, int x1, int y1, int width, int height, double price) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = this.x1 + width;
        this.y2 = this.y1 + height;
        this.price = price;
        this.parkCells = new ArrayList<>();
    }

    void addParkCell(ParkCell parkCell) {
        this.parkCells.add(parkCell);
    }

    boolean tryAddParkCell(ParkCell parkCell) {
        if (parkCell.x >= this.x1 && parkCell.x <= this.x2
                && parkCell.y >= this.y1 && parkCell.y <= this.y2) {
            this.addParkCell(parkCell);
            parkCell.zone = this.name;
            return true;
        }

        return false;
    }
}

class ParkCell {

    int x;
    int y;
    String zone;
    double price;
    long time;

    public ParkCell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}