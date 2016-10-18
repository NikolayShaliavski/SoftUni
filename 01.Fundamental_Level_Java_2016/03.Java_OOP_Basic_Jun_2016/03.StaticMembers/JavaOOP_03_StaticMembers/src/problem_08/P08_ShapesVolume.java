package problem_08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P08_ShapesVolume {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = reader.readLine();

        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            String shape = tokens[0];
            if (shape.equals("TrianglePrism")) {
                double side = Double.valueOf(tokens[1]);
                double height = Double.valueOf(tokens[2]);
                double length = Double.valueOf(tokens[3]);

                double volume = VolumeCalculator.calculateTrianglePrismVolume(new TrianglePrism(side, height, length));
                System.out.printf("%.3f%n", volume);
            } else if (shape.equals("Cylinder")) {
                double radius = Double.valueOf(tokens[1]);
                double height = Double.valueOf(tokens[2]);

                double volume = VolumeCalculator.calculateCylinderVolume(new Cylinder(radius, height));
                System.out.printf("%.3f%n", volume);
            } else if (shape.equals("Cube")) {
                double side = Double.valueOf(tokens[1]);

                double volume = VolumeCalculator.calculateCubeVolume(new Cube(side));
                System.out.printf("%.3f%n", volume);
            }
            line = reader.readLine();
        }
    }
}
class VolumeCalculator {

    private static final double DEFAULT_PI = Math.PI;

    public static double calculateTrianglePrismVolume(TrianglePrism trianglePrism) {
        return (trianglePrism.getSide() * trianglePrism.getHeight() * trianglePrism.getLength()) / 2;
    }

    public static double calculateCubeVolume(Cube cube) {
        return Math.pow(cube.getSide(), 3);
    }

    public static double calculateCylinderVolume(Cylinder cylinder) {
        return Math.pow(cylinder.getRadius(), 2) * cylinder.getHeight() * DEFAULT_PI;
    }
}
class TrianglePrism {

    private double side;
    private double height;
    private double length;

    public TrianglePrism(double side, double height, double length) {
        this.side = side;
        this.height = height;
        this.length = length;
    }

    public double getSide() {
        return this.side;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }
}
class Cube {

    private double side;

    public Cube(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }
}
class Cylinder {

    private double radius;
    private double height;

    public Cylinder(double radius, double height) {
        this.radius = radius;
        this.height = height;
    }

    public double getRadius() {
        return radius;
    }

    public double getHeight() {
        return height;
    }
}