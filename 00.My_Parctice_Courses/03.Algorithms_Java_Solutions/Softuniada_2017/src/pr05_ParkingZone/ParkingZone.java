package pr05_ParkingZone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ParkingZone {

    static Zone[] zones;
    static List<ParkCell> parkCells;
    static Map<String, Boolean> notFree;
    static ParkCell market;
    static int k;

    public static void main(String[] args) throws IOException {
        readInput();
        ParkCell bestCell = null;
        List<ParkCell> queue = new LinkedList<>();
        market.steps = 0;
        int allParkCells = parkCells.size();
        int counter = 0;
        queue.add(market);

        while (queue.size() > 0) {
            ParkCell currCell = queue.remove(0);
            String coords = currCell.x + "," + currCell.y;
            if (notFree.containsKey(coords)) {
                for (ParkCell parkCell : parkCells) {
                    if (currCell.x == parkCell.x && currCell.y == parkCell.y) {
                        //parkCell.steps--;
                        calculateTime(parkCell);

                        if (bestCell == null) {
                            bestCell = parkCell;
                        } else if (parkCell.pricePerMinute < bestCell.pricePerMinute) {
                            bestCell = parkCell;
                        } else if (parkCell.pricePerMinute == bestCell.pricePerMinute) {
                            if (parkCell.time < bestCell.time) {
                                bestCell =  parkCell;
                            }
                        }
                    }
                }
                counter++;
            }
            if (counter == allParkCells) {
                break;
            }
            if (!notFree.containsKey(coords)) {
                if (currCell.x > 0) {
                    queue.add(new ParkCell(currCell.x - 1, currCell.y, currCell.steps + 1));//up
                }
                if (currCell.y < 17) {
                    queue.add(new ParkCell(currCell.x, currCell.y + 1, currCell.steps + 1));//right
                }
                if (currCell.x < 16) {
                    queue.add(new ParkCell(currCell.x + 1, currCell.y, currCell.steps + 1));//down
                }
                if (currCell.y > 0) {
                    queue.add(new ParkCell(currCell.x, currCell.y - 1, currCell.steps + 1));//left
                }
            }
        }
//        for (Zone zone : zones) {
//            for (ParkCell parkCell : zone.parkCells) {
//                long time = calculateTime(parkCell);
//                parkCell.price = time * zone.pricePerMinute;
//
//                if (bestCell == null) {
//                    bestCell = parkCell;
//                } else if (parkCell.pricePerMinute < bestCell.pricePerMinute) {
//                    bestCell = parkCell;
//                } else if (parkCell.pricePerMinute == bestCell.pricePerMinute) {
//                    if (parkCell.time < bestCell.time) {
//                        bestCell =  parkCell;
//                    }
//                }
//            }
//        }

        System.out.printf("Zone Type: %s; X: %d; Y: %d; Price: %.2f%n",
                bestCell.zone, bestCell.x, bestCell.y, bestCell.pricePerMinute);
    }

    private static void calculateTime(ParkCell parkCell) {
//        int xDist = Math.abs(parkCell.x - market.x);
//        int yDist = Math.abs(parkCell.y - market.y);

        int distance = parkCell.steps * 2;
        long timeInSeconds = distance * k;
        parkCell.time = timeInSeconds;

        double time = timeInSeconds / 60.0;
        long ceiledMinutes = (long) Math.ceil(time);

        parkCell.price = parkCell.pricePerMinute * ceiledMinutes;
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
        notFree = new HashMap<>();
        String[] cellTokens = bf.readLine().split("; ");

        for (int i = 0; i < cellTokens.length; i++) {
            String[] cellParams = cellTokens[i].split(", ");
            int x = Integer.valueOf(cellParams[0]);
            int y = Integer.valueOf(cellParams[1]);
            notFree.put((x + "," + y), true);

            ParkCell parkCell = new ParkCell(x, y);
            for (Zone zone : zones) {
                if (trySetZone(parkCell, zone)) {
                    break;
                }
            }
            parkCells.add(parkCell);
        }
        String[] marketTokens = bf.readLine().split(", ");
        int marketX = Integer.valueOf(marketTokens[0]);
        int marketY = Integer.valueOf(marketTokens[1]);
        market = new ParkCell(marketX, marketY);

        k = Integer.valueOf(bf.readLine());
    }

    private static boolean trySetZone(ParkCell parkCell, Zone zone) {
        if (parkCell.x >= zone.x1 && parkCell.x <= zone.x2 &&
                parkCell.y >= zone.y1 && parkCell.y <= zone.y2) {
            parkCell.zone = zone.name;
            parkCell.pricePerMinute = zone.pricePerMinute;
            return true;
        }
        return false;
    }
}

class Zone {

    String name;
    Integer x1;
    Integer y1;
    Integer x2;
    Integer y2;
    double pricePerMinute;

    public Zone(String name, int x1, int y1, int width, int height, double price) {
        this.name = name;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = this.x1 + width;
        this.y2 = this.y1 + height;
        this.pricePerMinute = price;
    }

//    @Override
//    public int compareTo(Zone other) {
//        int result = this.x1.compareTo(other.x1);
//        if (result == 0) {
//            result = this.y1.compareTo(other.y1);
//        }
//        return result;
//    }
}

class ParkCell {

    int x;
    int y;
    int steps;
    String zone;
    double pricePerMinute;
    double price;
    long time;

    public ParkCell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ParkCell(int x, int y, int steps) {
        this(x, y);
        this.steps = steps;
    }
}
