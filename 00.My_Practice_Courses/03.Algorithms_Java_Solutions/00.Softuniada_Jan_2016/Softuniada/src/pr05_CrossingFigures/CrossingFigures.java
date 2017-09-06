package pr05_CrossingFigures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class CrossingFigures {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int lines = Integer.valueOf(bf.readLine());

        for (int i = 0; i < lines; i++) {
            String firstLine = bf.readLine();
            String secondLine = bf.readLine();

            boolean firstRect = false;
            if (firstLine.startsWith("r")) {
                firstRect = true;
            }

            firstLine = firstLine.substring(firstLine.indexOf('(') + 1, firstLine.indexOf(')'));
            secondLine = secondLine.substring(secondLine.indexOf('(') + 1, secondLine.indexOf(')'));

            double[] rectTokens;
            double[] circleTokens;

            if (firstRect) {
                rectTokens = Arrays.stream(firstLine.split("[, ]+")).
                        mapToDouble(Double::parseDouble).toArray();
                circleTokens = Arrays.stream(secondLine.split("[, ]+")).
                        mapToDouble(Double::parseDouble).toArray();
            } else {
                circleTokens = Arrays.stream(firstLine.split("[, ]+")).
                        mapToDouble(Double::parseDouble).toArray();
                rectTokens = Arrays.stream(secondLine.split("[, ]+")).
                        mapToDouble(Double::parseDouble).toArray();
            }

            Circle circle = new Circle(circleTokens[0], circleTokens[1], circleTokens[2]);
            Rectangle rectangle = new Rectangle(rectTokens[0], rectTokens[1], rectTokens[2], rectTokens[3]);

            checkFigures(circle, rectangle);
        }
    }

    private static void checkFigures(Circle circle, Rectangle rectangle) {
        double circleUp = circle.y + circle.r;
        circleUp = Math.round(circleUp * 100.0) / 100.0;
        double circleDown = circle.y - circle.r;
        circleDown = Math.round(circleDown * 100.0) / 100.0;
        double circleLeft = circle.x - circle.r;
        circleLeft = Math.round(circleLeft * 100.0) / 100.0;
        double circleRight = circle.x + circle.r;
        circleRight = Math.round(circleRight * 100.0) / 100.0;

        double topLeftPow = (Math.pow(rectangle.aX - circle.x, 2) + Math.pow(rectangle.aY - circle.y, 2));
        //topLeftPow = Math.round(topLeftPow * 100.0) / 100.0;
        double topRightPow = (Math.pow(rectangle.bX - circle.x, 2) + Math.pow(rectangle.aY - circle.y, 2));
        //topRightPow = Math.round(topRightPow * 100.0) / 100.0;
        double downRightPow = (Math.pow(rectangle.bX - circle.x, 2) + Math.pow(rectangle.bY - circle.y, 2));
        //downRightPow = Math.round(downRightPow * 100.0) / 100.0;
        double downLeftPow = (Math.pow(rectangle.aX - circle.x, 2) + Math.pow(rectangle.bY - circle.y, 2));
        //downLeftPow = Math.round(downLeftPow * 100.0) / 100.0;
        /**
         * Rounding double value two
         * digits after decimal point
         */
        double radPow = Math.pow(circle.r, 2);
        //radPow = Math.round(radPow * 100.0) / 100.0;

        if (circleUp <= Math.round(rectangle.aY * 100.0) / 100.0 &&
                circleDown >= Math.round(rectangle.bY * 100.0) / 100.0 &&
                circleLeft >= Math.round(rectangle.aX * 100.0) / 100.0 &&
                circleRight <= Math.round(rectangle.bX * 100.0) / 100.0) {
            System.out.println("Circle inside rectangle");
            //Rectangle is inside circle if all it's corners are inside
        } else if (topLeftPow - radPow <= 0.01 &&
                topRightPow - radPow <= 0.01 &&
                downRightPow - radPow <= 0.01 &&
                downLeftPow - radPow <= 0.01) {
            System.out.println("Rectangle inside circle");
        } else if (topLeftPow > radPow &&
                topRightPow > radPow &&
                downRightPow > radPow &&
                downLeftPow > radPow &&
                (circleDown - rectangle.aY > 0.01 || circleUp - rectangle.bY < -0.01 ||
                        circleRight - rectangle.aX < -0.01 || circleLeft - rectangle.bX > 0.01)) {
            System.out.println("Rectangle and circle do not cross");
        } else {
            System.out.println("Rectangle and circle cross");
        }
    }
}

class Circle {

    double x;
    double y;
    double r;

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}

class Rectangle {

    double aX;
    double aY;
    double bX;
    double bY;

    public Rectangle(double aX, double aY, double bX, double bY) {
        this.aX = aX;
        this.aY = aY;
        this.bX = bX;
        this.bY = bY;
    }
}
