package problem_14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P14_DrawingTool {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String figure = reader.readLine();
        int width = 0;
        int height = 0;
        if (figure.equals("Square")) {
            width = Integer.valueOf(reader.readLine());
            height = width;
        } else if (figure.equals("Rectangle")) {
            width = Integer.valueOf(reader.readLine());
            height = Integer.valueOf(reader.readLine());
        }
//        CorDraw corDraw = new CorDraw(width, height);
//        corDraw.draw();

        Square square = new Square(5, 5);
        CorDraw corDraw = new CorDraw(square);
    }
}
abstract class Shape {

    abstract void draw();

}

class Square extends Shape{
    private int width;
    private int height;

    public Square(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private int getWidth() {
        return width;
    }

    private int getHeight() {
        return height;
    }

    @Override
    public void draw() {
        for (int i = 0; i < this.getHeight(); i++) {
            System.out.print("|");
            for (int j = 0; j < this.getWidth(); j++) {
                if (i > 0 && i < this.getHeight() - 1) {
                    System.out.print(" ");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println("|");
        }
    }
}
class Rectangle extends Shape{

    @Override
    void draw() {

    }
}
class CorDraw {

    private Shape shape;

    public CorDraw(Shape shape) {
        this.shape = shape;
    }

    public void draw() {
        this.shape.draw();
    }
}