import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();

        int[] fruits = getInts();

        solution(info[1], fruits);

        printRes();
    }

    private static void solution(int l, int[] fruits) {
        Arrays.sort(fruits);

        int i = 0;
        while (i < fruits.length && fruits[i] <= l) {
            int delta = 0;
            while (i < fruits.length && fruits[i] <= l) {
                i++;
                delta++;
            }

            l += delta;
        }

        res.append(l);
    }

    // 11
    //

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