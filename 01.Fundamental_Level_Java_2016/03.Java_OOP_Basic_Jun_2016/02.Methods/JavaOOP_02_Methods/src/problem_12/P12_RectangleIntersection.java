package problem_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class P12_RectangleIntersection {

    //private static List<Rectangle> rectangleList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] numberOfLines = reader.readLine().split(" ");
        int numberOfRectangles = Integer.valueOf(numberOfLines[0]);
        int numberOfIntersects = Integer.valueOf(numberOfLines[1]);

        Map<String, Rectangle> rectangles = new HashMap<>();

        for (int i = 0; i < numberOfRectangles; i++) {
            String[] tokens = reader.readLine().split(" ");
            String id = tokens[0];
            double width = Double.parseDouble(tokens[1]);
            double height = Double.parseDouble(tokens[2]);
            double x = Double.parseDouble(tokens[3]);
            double y = Double.parseDouble(tokens[4]);
            Rectangle rectangle = new Rectangle(id, x, y, width, height);
            //rectangleList.add(rectangle);
            rectangles.put(id, rectangle);
        }
        for (int i = 0; i < numberOfIntersects; i++) {
            String[] tokens = reader.readLine().split(" ");
            Rectangle firstRect = rectangles.get(tokens[0]); //getRectangleFromList(tokens[0]);
            Rectangle secondRect = rectangles.get(tokens[1]); //getRectangleFromList(tokens[1]);
            System.out.println(firstRect.intersect(secondRect));
        }
    }

//    public static Rectangle getRectangleFromList(String id) {
//        for (Rectangle rectangle : rectangleList) {
//            if (rectangle.getId().equals(id)) {
//                return rectangle;
//            }
//        }
//        return null;
//    }
}

class Rectangle {

    private String id;
    private double x;
    private double y;
    private double width;
    private double height;

    public Rectangle(String id, double x, double y, double width, double height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public boolean intersect(Rectangle rect) {
        double x1 = this.getX();
        double y1 = this.getY();
        double x2 = this.getX() + this.getWidth();
        double y2 = this.getY() + this.getHeight();

        double x3 = rect.getX();
        double y3 = rect.getY();
        double x4 = rect.getX() + rect.getWidth();
        double y4 = rect.getY() + rect.getHeight();

        double leftX = Math.max(x1, x3);
        double rightX = Math.min(x2, x4);
        double topY = Math.max(y1, y3);
        double botY = Math.min(y2, y4);

        if (rightX > leftX && botY > topY) {
            return true;
        }
//        return false;
//        double tw = this.width;
//        double th = this.height;
//        double rw = r.width;
//        double rh = r.height;
//        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
//            return false;
//        }
//        double tx = this.x;
//        double ty = this.y;
//        double rx = r.x;
//        double ry = r.y;
//        rw += rx;
//        rh += ry;
//        tw += tx;
//        th += ty;
//        //      overflow || intersect
//        return ((rw < rx || rw > tx) &&
//                (rh < ry || rh > ty) &&
//                (tw < tx || tw > rx) &&
//                (th < ty || th > ry));
        return false;
    }
}