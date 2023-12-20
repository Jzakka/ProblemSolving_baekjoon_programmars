import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] schedules = new int[n][];

        for (int i = 0; i < n; i++) {
            schedules[i] = getInts();
        }

        solution(schedules);

        printRes();
    }

    private static void solution(int[][] schedules) {
        Arrays.sort(schedules, Comparator.comparingInt(s -> -s[1]));

        int last = Integer.MAX_VALUE;
        for (int[] schedule : schedules) {
            last = Math.min(last, schedule[1]) - schedule[0];
        }
        if (last < 0) {
            res.append(-1);
        } else {
            res.append(last);
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

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
8
4
7 0
6 1
5 2
4 3
 */