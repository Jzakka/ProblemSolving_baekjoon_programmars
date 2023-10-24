import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int[] info = getInts();
            int candies = info[0];
            int m = info[1];
            int[] boxSizes = new int[m];

            for (int i = 0; i < m; i++) {
                int[] size = getInts();
                boxSizes[i] = size[0] * size[1];
            }

            solution(candies, boxSizes);
        }

        printRes();
    }

    private static void solution(int candies, int[] boxSizes) {
        Arrays.sort(boxSizes);

        int boxCount = 0;
        for (int i = boxSizes.length - 1 ;i >= 0 && candies > 0 ; i--, boxCount++) {
            candies -= boxSizes[i];
        }

        res.append(boxCount).append("\n");
    }


    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static long[] getLongs() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}