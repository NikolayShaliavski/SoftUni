package pr08_PackagingFigures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PackagingFigures {

    static List<Figure> figures;

    public static void main(String[] args) throws IOException {
        figures = new ArrayList<>();
        readInput();

        Figure best = figures.get(0);
        for (int i = 0; i < figures.size(); i++) {
            Figure other = figures.get(i);
            findNestedFigures(other);
            if (other.bestDepth > best.bestDepth ||
                    (other.bestDepth == best.bestDepth && other.name.compareTo(best.name) < 0)) {
                best = other;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (best != null) {

            sb.append(best.name).append(" < ");
            best = best.bestPrev;
        }
        String bestNestedNames = sb.substring(0, sb.length() - 3);
        System.out.println(bestNestedNames);
    }

    private static void findNestedFigures(Figure figure) {

        if (figure.bestDepth > 0) {
            return;
        }
        Figure prev = null;
        figure.bestDepth = 1;

        for (int i = 0; i < figures.size(); i++) {
            Figure other = figures.get(i);
            if (figure != other && other.isInside(figure)) {
                findNestedFigures(other);

                if (prev == null ||
                        other.bestDepth > prev.bestDepth ||
                        (other.bestDepth == prev.bestDepth && other.name.compareTo(prev.name) < 0)) {
                    prev = other;
                    figure.bestDepth = other.bestDepth + 1;
                }
            }
        }

        if (prev != null) {
            figure.bestPrev = prev;
        }
    }

    private static void readInput() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String line = bf.readLine();

        while (!line.equals("End")) {
            String[] tokens = line.split("[\\s]+");
            String figType = tokens[0];
            String name = tokens[1];
            Figure figure = null;
            int[] coordinates;
            switch (figType) {
                case "rectangle":
                    coordinates = new int[4];
                    for (int i = 2; i < tokens.length; i++) {
                        coordinates[i - 2] = Integer.valueOf(tokens[i]);
                    }
                    figure = new Rectangle(name,
                            coordinates[0],
                            coordinates[1],
                            coordinates[2],
                            coordinates[3]);
                    break;
                case "square":
                    coordinates = new int[3];
                    for (int i = 2; i < tokens.length; i++) {
                        coordinates[i - 2] = Integer.valueOf(tokens[i]);
                    }
                    figure = new Square(name,
                            coordinates[0],
                            coordinates[1],
                            coordinates[2]);
                    break;
                case "circle":
                    coordinates = new int[3];
                    for (int i = 2; i < tokens.length; i++) {
                        coordinates[i - 2] = Integer.valueOf(tokens[i]);
                    }
                    figure = new Circle(name,
                            coordinates[0],
                            coordinates[1],
                            coordinates[2]);
                    break;
            }
            figures.add(figure);

            line = bf.readLine();
        }
    }
}

abstract class Figure {

    String name;
    int bestDepth;
    Figure bestPrev;

    Figure(String name) {
        this.name = name;
        bestDepth = 0;
    }

    abstract boolean isInside(Figure other);
}

class Rectangle extends Figure {

    int aX;
    int aY;
    int bX;
    int bY;

    Rectangle(String name, int aX, int aY, int bX, int bY) {
        super(name);
        this.aX = aX;
        this.aY = aY;
        this.bX = bX;
        this.bY = bY;
    }

    @Override
    boolean isInside(Figure other) {
        boolean isInside;
        if (other instanceof Circle) {
            int center_x = ((Circle) other).center_x;
            int center_y = ((Circle) other).center_y;
            double radPow = Math.pow(((Circle) other).r, 2);

            isInside = (Math.pow(center_x - this.aX, 2) + Math.pow(center_y - this.aY, 2) <= radPow) &&//up left
                    (Math.pow(center_x - this.bX, 2) + Math.pow(center_y - this.aY, 2) <= radPow) &&//up right
                    (Math.pow(center_x - this.bX, 2) + Math.pow(center_y - this.bY, 2) <= radPow) &&//down right
                    (Math.pow(center_x - this.aX, 2) + Math.pow(center_y - this.bY, 2) <= radPow);//down left
        } else if (other instanceof Square) {
            int sqrt_side = ((Square) other).side;
            int sqrt_aX = ((Square) other).aX;
            int sqrt_aY = ((Square) other).aY;
            int sqrt_bX = sqrt_aX + sqrt_side;
            int sqrt_bY = sqrt_aY - sqrt_side;

            isInside = (this.aX >= sqrt_aX) &&
                    (this.aY <= sqrt_aY) &&
                    (this.bX <= sqrt_bX) &&
                    (this.bY >= sqrt_bY);
        } else {
            int rect_aX = ((Rectangle) other).aX;
            int rect_aY = ((Rectangle) other).aY;
            int rect_bX = ((Rectangle) other).bX;
            int rect_bY = ((Rectangle) other).bY;

            isInside = this.aX >= rect_aX &&
                    this.aY <= rect_aY &&
                    this.bX <= rect_bX &&
                    this.bY >= rect_bY;
        }
        return isInside;
    }
}

class Square extends Figure {

    int aX;
    int aY;
    int side;

    Square(String name, int aX, int aY, int side) {
        super(name);
        this.aX = aX;
        this.aY = aY;
        this.side = side;
    }

    @Override
    boolean isInside(Figure other) {
        boolean isInside;

        int bX = this.aX + this.side;
        int bY = this.aY - this.side;

        if (other instanceof Circle) {
            int center_x = ((Circle) other).center_x;
            int center_y = ((Circle) other).center_y;
            double radPow = Math.pow(((Circle) other).r, 2);

            isInside = (Math.pow(center_x - this.aX, 2) + Math.pow(center_y - this.aY, 2) <= radPow) &&//up left
                    (Math.pow(center_x - bX, 2) + Math.pow(center_y - this.aY, 2) <= radPow) &&//up right
                    (Math.pow(center_x - bX, 2) + Math.pow(center_y - bY, 2) <= radPow) &&//down right
                    (Math.pow(center_x - this.aX, 2) + Math.pow(center_y - bY, 2) <= radPow);//down left
        } else if (other instanceof Rectangle) {
            int rect_aX = ((Rectangle) other).aX;
            int rect_aY = ((Rectangle) other).aY;
            int rect_bX = ((Rectangle) other).bX;
            int rect_bY = ((Rectangle) other).bY;

            isInside = (this.aX >= rect_aX) &&
                    (this.aY <= rect_aY) &&
                    (bX <= rect_bX) &&
                    (bY >= rect_bY);
        } else {
            int sqrt_side = ((Square) other).side;
            int sqrt_aX = ((Square) other).aX;
            int sqrt_aY = ((Square) other).aY;
            int sqrt_bX = sqrt_aX + sqrt_side;
            int sqrt_bY = sqrt_aY - sqrt_side;

            isInside = (this.aX >= sqrt_aX) &&
                    (this.aY <= sqrt_aY) &&
                    (bX <= sqrt_bX) &&
                    (bY >= sqrt_bY);
        }
        return isInside;
    }
}

class Circle extends Figure {

    int center_x;
    int center_y;
    int r;

    Circle(String name, int center_x, int center_y, int r) {
        super(name);
        this.center_x = center_x;
        this.center_y = center_y;
        this.r = r;
    }

    @Override
    boolean isInside(Figure other) {
        int up = this.center_y + this.r;
        int down = this.center_y - this.r;
        int left = this.center_x - this.r;
        int right = this.center_x + this.r;

        boolean isInside;

        if (other instanceof Rectangle) {
            int rect_aX = ((Rectangle) other).aX;
            int rect_aY = ((Rectangle) other).aY;
            int rect_bX = ((Rectangle) other).bX;
            int rect_bY = ((Rectangle) other).bY;

            isInside = (up <= rect_aY) &&
                    (down >= rect_bY) &&
                    (left >= rect_aX) &&
                    (right <= rect_bX);
        } else if (other instanceof Square ) {
            int sqrt_side = ((Square) other).side;
            int sqrt_aX = ((Square) other).aX;
            int sqrt_aY = ((Square) other).aY;
            int sqrt_bX = sqrt_aX + sqrt_side;
            int sqrt_bY = sqrt_aY - sqrt_side;

            isInside = (up <= sqrt_aY) &&
                    (down >= sqrt_bY) &&
                    (left >= sqrt_aX) &&
                    (right <= sqrt_bX);
        } else {
            int other_x = ((Circle) other).center_x;
            int other_y = ((Circle) other).center_y;
            int other_r = ((Circle) other).r;

            double distance =
                    Math.sqrt(Math.pow(this.center_x - other_x, 2) + Math.pow(this.center_y - other_y, 2));

            isInside = other_r >= (this.r + distance);
        }
        return isInside;
    }
}
