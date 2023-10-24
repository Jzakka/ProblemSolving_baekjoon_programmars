import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();

        solution(info[0], info[1]);

        printRes();
    }

    private static void solution(int a, int b) {
        int cnt = 0;

        while (b > a) {
            if ((b & 1) == 0) {
                b >>= 1;
            } else {
                if (b % 10 != 1) {
                    break;
                } else {
                    b = (b - 1) / 10;
                }
            }
//            System.out.println(b);
            cnt++;
        }

        if (b == a) {
            res.append(cnt+1);
        } else {
            res.append(-1);
        }
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