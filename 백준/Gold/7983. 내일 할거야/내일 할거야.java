import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] subjects = new int[n][];

        for (int i = 0; i < n; i++) {
            subjects[i] = getInts();
        }

        solution(subjects);

        printRes();
    }

    private static void solution(int[][] subjects) {
        Arrays.sort(subjects, Comparator.comparingInt(s -> -s[1]));

        int lastDay = Integer.MAX_VALUE;

        for (int[] subject : subjects) {
            lastDay = Math.min(lastDay, subject[1]);
            lastDay -= subject[0];
        }

        res.append(lastDay);
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

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}