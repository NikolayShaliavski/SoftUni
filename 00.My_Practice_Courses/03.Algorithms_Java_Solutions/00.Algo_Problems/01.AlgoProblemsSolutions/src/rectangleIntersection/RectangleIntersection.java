package rectangleIntersection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RectangleIntersection {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int rectsCount = Integer.valueOf(bf.readLine());

        List<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < rectsCount; i++) {
            String[] rectTokens = bf.readLine().split("[\\s]+");
            Integer x1 = Integer.valueOf(rectTokens[0]);
            Integer x2 = Integer.valueOf(rectTokens[1]);
            Integer y2 = Integer.valueOf(rectTokens[2]);
            Integer y1 = Integer.valueOf(rectTokens[3]);

            rectangles.add(new Rectangle(x1, y1, x2, y2));
        }

        int[] xCoords = new int[rectsCount * 2];
        int counter = 0;
        for (int i = 0; i < xCoords.length; i += 2) {
            xCoords[i] = rectangles.get(counter).x1;
            xCoords[i + 1] = rectangles.get(counter).x2;
            counter++;
        }
        Arrays.sort(xCoords);
        List<Rectangle> overlapped = new ArrayList<>();
        for (int i = 0; i < xCoords.length - 1; i++) {
            int firstXCoord = xCoords[i];
            int secXCoord = xCoords[i + 1];
            for (Rectangle rectangle : rectangles) {
                if (rectangle.x1 < firstXCoord && rectangle.x2 > secXCoord) {

                    overlapped.add(rectangle);
                }
            }
        }
        System.out.println();
    }
}

class Rectangle {
    Integer x1;
    Integer y1;
    Integer x2;
    Integer y2;

    public Rectangle(Integer x1, Integer y1, Integer x2, Integer y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

//    @Override
//    public int compareTo(Rectangle other) {
//        int result = this.x1.compareTo(other.x1);
//        if (result == 0) {
//            result = other.x2.compareTo(this.x2);
//        }
//        if (result == 0) {
//            result = other.y1.compareTo(this.y1);
//        }
//        if (result == 0) {
//            result = this.y2.compareTo(other.y2);
//        }
//        return result;
//    }

    @Override
    public String toString() {
        return String.format("%d %d %d %d", this.x1, this.y1, this.x2, this.y2);
    }
}