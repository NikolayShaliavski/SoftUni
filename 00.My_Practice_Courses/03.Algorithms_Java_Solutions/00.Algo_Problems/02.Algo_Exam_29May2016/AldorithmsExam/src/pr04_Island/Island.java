package pr04_Island;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Island {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String[] line = bf.readLine().split(" ");
        int[] heights = new int[line.length];
        for (int i = 0; i < heights.length; i++) {
            heights[i] = Integer.valueOf(line[i]);
        }
        int maxArea = 0;
        int[] leftCount = new int[heights.length];
        Stack<Integer> columnsOnLeft = new Stack<>();
        columnsOnLeft.push(0);

        for (int i = 1; i < heights.length; i++) {
            int current = heights[i];
            int leftBigger = i;

            while (columnsOnLeft.size() > 0 &&
                    heights[columnsOnLeft.peek()] >= current) {
                int j = columnsOnLeft.pop();
                int rightCount = i - j;
                int area = (leftCount[j] + rightCount) * heights[j];

                if (area > maxArea) {
                    maxArea = area;
                }
            }

            leftBigger = columnsOnLeft.size() == 0 ? 0 : columnsOnLeft.peek() + 1;

            leftCount[i] = i - leftBigger;
            columnsOnLeft.push(i);
        }

        while (columnsOnLeft.size() > 0) {
            int j = columnsOnLeft.pop();
            int right = heights.length - j;
            int area = (leftCount[j] + right) * heights[j];
            if (area > maxArea) {
                maxArea = area;
            }
        }
        System.out.println(maxArea);
    }
}
