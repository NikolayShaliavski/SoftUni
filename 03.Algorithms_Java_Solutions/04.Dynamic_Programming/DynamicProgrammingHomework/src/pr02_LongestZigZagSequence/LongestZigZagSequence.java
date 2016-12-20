package pr02_LongestZigZagSequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class LongestZigZagSequence {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] sequence = {8, 3, 5, 7, 0, 8, 9, 10, 20, 20, 20, 12, 19, 11};
        //Arrays.stream(bf.readLine().split("[\\,]")).mapToInt(Integer::parseInt).toArray();

        List<Integer> lzs = findLzs(sequence);
    }

    private static List<Integer> findLzs(int[] sequence) {
        int[] diff = new int[sequence.length];


        for (int i = 0; i < sequence.length - 1; i++) {
            diff[i] = sequence[i] - sequence[i + 1];
        }
        diff[diff.length - 1] = sequence[sequence.length - 2] - sequence[sequence.length - 1];

        System.out.println();
        //List<Integer> lzs = retrieveLzs();
        return null;
    }

//copied form StackOverflow
//    public static int longestZigZag(int[] A){
//        int n = A.length;
//        int[][] Z = new int[n][2];
//        for(int i = 0; i < Z.length; i++){
//            Z[i] = new int[2];
//        }
//        Z[0][0] = 1;
//        Z[0][1] = 1;
//
//        int best = 1;
//
//        for(int i = 1; i < n; i++){
//            for(int j = i-1; j>= 0; j--){
//                if(A[j] < A[i]) Z[i][0] = Math.max(Z[j][1]+1,Z[i][0]);
//                if(A[j] > A[i]) Z[i][1] = Math.max(Z[j][0]+1, Z[i][1]);
//            }
//            best = Math.max(best, Math.max(Z[i][0],Z[i][1]));
//        }
//        return best;
//    }
}
