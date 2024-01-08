import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] works = new int[n][];

        for (int i = 0; i < n; i++) {
            works[i] = getInts();
        }
        solution(works);

        printRes();
    }

    private static void solution(int[][] works) {
        Arrays.sort(works, Comparator.comparingInt(w -> -w[1]));

        int last = Integer.MAX_VALUE;
        for (int[] work : works) {
            int time = work[0];
            int until = work[1];

            last = Math.min(last, until);
            last -= time;
            if (last < 0) {
                res.append(-1);
                return;
            }
        }
        res.append(last);
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
58
3
5
1
2
0

37
0
0
0
0
7

17
20
0
3
0
0

13
12
0
3
0
0

7
6
5
0
0
0

49
7
0
2
0
0

32
7
0
3
0
0
 */