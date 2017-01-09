package pr07_StarClusters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.*;

public class StarClusters {

    private static Set<Cluster> clusters;
    private static List<Star> newStars;

    public static void main(String[] args) throws IOException {
        readInput();

        //addNewStars();

        for (Cluster cluster : clusters) {
            cluster.calcCenter();
            BigDecimal center_x =
                    new BigDecimal(cluster.center_x + "").setScale(0, BigDecimal.ROUND_HALF_EVEN);
            BigDecimal center_y =
                    new BigDecimal(cluster.center_y + "").setScale(0, BigDecimal.ROUND_HALF_EVEN);

            System.out.printf("%s (%s, %s) -> %d stars%n",
                    cluster.name, center_x, center_y, cluster.stars.size());
        }
    }

    private static void addNewStars() {

        for (Star star : newStars) {
            double distance = Double.MAX_VALUE;
            Cluster cluster = null;
            for (Cluster currCluster : clusters) {
                double currDistance =
                        Math.pow(currCluster.center_x - star.x, 2) + Math.pow(currCluster.center_y - star.y, 2);
                if (currDistance <= distance) {
                    distance = currDistance;
                    cluster = currCluster;
                }
            }
            cluster.addStar(star);
        }
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bf.readLine());

        clusters = new TreeSet<>();
        //newStars = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] tokens = bf.readLine().split(" \\(");
            String clusterName = tokens[0];
            int[] starTokens = Arrays.stream(tokens[1].split("[\\s,\\)]+")).
                    mapToInt(Integer::parseInt).toArray();

            Cluster cluster = new Cluster(clusterName);
            Star star = new Star(starTokens[0], starTokens[1]);
            cluster.addStar(star);
            cluster.center_x = star.x;
            cluster.center_y = star.y;
            clusters.add(cluster);
        }
        String line = bf.readLine();
        while (!line.equals("end")) {

            String[] tokens = line.split(" \\(");
            tokens[0] = tokens[0].replace("(", "");
            for (String token : tokens) {
                int[] starTokens = Arrays.stream(token.split("[\\s,\\)]+")).
                        mapToInt(Integer::parseInt).toArray();
                Star star = new Star(starTokens[0], starTokens[1]);

                double distance = Double.MAX_VALUE;
                Cluster cluster = null;
                for (Cluster currCluster : clusters) {
                    double currDistance =
                            Math.pow(currCluster.center_x - star.x, 2) +
                                    Math.pow(currCluster.center_y - star.y, 2);
                    if (currDistance <= distance) {
                        distance = currDistance;
                        cluster = currCluster;
                    }
                }
                cluster.addStar(star);

                //newStars.add(newStar);
            }
            line = bf.readLine();
        }
    }
}
class Star {
    int x;
    int y;

    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
class Cluster implements Comparable<Cluster> {
    String name;
    List<Star> stars;
    double center_x;
    double center_y;

    public Cluster(String name) {
        this.name = name;
        this.stars = new ArrayList<>();
    }

    void addStar(Star star) {
        this.stars.add(star);
        this.calcCenter();
    }

    void calcCenter() {

        int allStarsX = stars.stream().mapToInt(star -> star.x).sum();
        this.center_x = (double) allStarsX / stars.size();
        int allStarsY = stars.stream().mapToInt(star -> star.y).sum();
        this.center_y = (double) allStarsY / stars.size();
    }

    @Override
    public int compareTo(Cluster other) {
        return this.name.compareTo(other.name);
    }
}
