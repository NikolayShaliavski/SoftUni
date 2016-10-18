import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RectangleTest {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] lines = reader.readLine().split("[\\s]+");
        int rects = Integer.parseInt(lines[0]);
        int checks = Integer.parseInt(lines[1]);

        Map<String, Rectangle> allRects = new HashMap<>();

        for (int i = 0; i < rects; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            String name = tokens[0];
            int width = Integer.parseInt(tokens[1]);
            int height = Integer.parseInt(tokens[2]);
            int x = Integer.parseInt(tokens[3]);
            int y = Integer.parseInt(tokens[4]);
            Rectangle rectangle = new Rectangle(x, y, width, height);
            allRects.put(name, rectangle);
        }

        for (int i = 0; i < checks; i++) {
            String[] tokens = reader.readLine().split("[\\s]+");
            String firstName = tokens[0];
            String secondName = tokens[1];
            System.out.println(allRects.get(firstName).intersects(allRects.get(secondName)));
        }
    }

}
