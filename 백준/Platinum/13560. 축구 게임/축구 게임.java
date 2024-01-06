import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] map = new int[100];


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] points = getInts();

        solution(points);

        printRes();
    }

    private static void solution(int[] points) {
        int[] objective = Arrays.stream(points)
                .boxed()
                .sorted((a, b) -> b - a)
                .mapToInt(Integer::intValue)
                .toArray();
        int[] origin = IntStream.range(0, points.length)
                .boxed()
                .sorted((a, b) -> b - a)
                .mapToInt(Integer::intValue)
                .toArray();

        int bank = 0;
        int n = origin.length;

        for (int i = 0; i < n; i++) {
            int originNum = origin[i];
            int objectNum = objective[i];

            if (originNum > objectNum) {
                bank += originNum - objectNum;
            } else if(originNum < objectNum) {
                int diff = objectNum - originNum;
                if (diff > bank) {
                    res.append(-1);
                    return;
                } else {
                    bank -= diff;
                }
            }
        }

        if (bank != 0) {
            res.append(-1);
        } else {
            res.append(1);
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