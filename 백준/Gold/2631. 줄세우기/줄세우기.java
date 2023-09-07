import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] children = new int[n];

        for (int i = 0; i < n; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        solution(children);
        printRes();
    }

    private static void solution(int[] children) {
        int[] DP = new int[children.length + 1];

        int maxVal = 0;
        for (int i = 0; i < children.length; i++) {
            int number = children[i];
            int maxCnt = IntStream.range(0, number).map(seq -> DP[seq]).max().getAsInt();
            DP[number] = maxCnt + 1;
            maxVal = Math.max(maxVal, DP[number]);
        }

        res.append(children.length - maxVal);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}