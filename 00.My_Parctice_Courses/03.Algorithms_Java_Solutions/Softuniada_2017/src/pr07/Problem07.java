package pr07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Problem07 {

    static List<Integer>[] graph;
    static int n;
    static int orders;
    static BufferedReader bf;

    public static void main(String[] args) throws IOException {
        readInput();

        for (int i = 0; i < orders; i++) {
            String[] tokens = bf.readLine().split(" ");
            int source = Integer.valueOf(tokens[0]) - 1;
            int dest = Integer.valueOf(tokens[1]) - 1;

            boolean found = false;
            boolean[] used = new boolean[n];
            List<Integer> queue = new LinkedList<>();
            queue.add(source);

            while (queue.size() > 0) {
                int currNode = queue.remove(0);
                if (currNode == dest) {
                    found = true;
                    break;
                }
                if (!used[currNode]) {
                    used[currNode] = true;
                    List<Integer> children = graph[currNode];

                    for (Integer child : children) {
                        if (!used[child]) {
                            queue.add(child);
                        }
                    }
                }
            }
            if (found) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static void readInput() throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));
        String[] line = bf.readLine().split(" ");
        n = Integer.valueOf(line[0]);
        orders = Integer.valueOf(line[1]);
        List<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            line = bf.readLine().split(" ");
            int x1 = Integer.valueOf(line[0]);
            int y1 = Integer.valueOf(line[1]);
            int x2 = Integer.valueOf(line[2]);
            int y2 = Integer.valueOf(line[3]);

//            int width = 0;
//            if (x1 >= 0 && x2 >= 0) {
//                width = x2 - x1;
//            } else if (x1 <= 0 && x2 <= 0) {
//                width = Math.abs(x2 - x1);
//            } else {
//                width += x2;
//                width += x1 * -1;
//            }
//            int height = 0;
//            if (y1 >= 0 && y2 >= 0) {
//                height = y1 - y2;
//            } else if (y1 <= 0 && y2 <= 0) {
//                height = Math.abs(y1 - y2);
//            } else {
//                height += y1;
//                height += y2 * -1;
//            }
            rectangles.add(new Rectangle(i, x1, y1, x2, y2));
        }

        graph = new List[n];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle first = rectangles.get(i);

            for (int j = 0; j < rectangles.size(); j++) {
                Rectangle second = rectangles.get(j);
                if (i != j) {
                    if (first.containsPoint(second.x1, second.y1) ||
                            first.containsPoint(second.x2, second.y2) ||
                            first.containsPoint(second.x2, second.y1) ||
                            first.containsPoint(second.x1, second.y2)) {

                        //graph[i].add(j);
                        graph[j].add(i);
                    }
                }

            }
        }
    }
}

class Rectangle {

    int id;
    int x1;
    int y1;
    int x2;
    int y2;

    public Rectangle(int id, int x1, int y1, int x2, int y2) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    boolean containsPoint(int x, int y) {
//        return (this.x1 <= x && this.y1 >= y) ||
//                (this.x2 >= x && this.y2 <= y);
        return this.x1 <= x && this.x2 >= x && this.y1 >= y && this.y2 <= y;
    }
}
