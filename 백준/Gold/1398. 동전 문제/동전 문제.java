import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] map = new int[100];


    public static void main(String[] args) throws Exception {
        Arrays.fill(map, Integer.MAX_VALUE);
        map[0] = 0;
        map[5] = 5;
        map[10] = 1;
        map[15] = 6;
        map[20] = 2;
        for (int i = 25; i < 100; i += 5) {
            map[i] = Math.min(map[i - 10], map[i - 25]) + 1;
        }

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String price = br.readLine();

            solution(price);
        }

        printRes();
    }

    private static void solution(String price) {
        if ((price.length() & 1) != 0) {
            price = "0" + price;
        }

        int cnt = 0;
        for (int s = price.length() - 2; s >= 0; s -= 2) {
            int e = s + 2;
            int slice = Integer.parseInt(price.substring(s, e));

            int oneDigit = slice % 10;
            if (oneDigit < 5) {
                cnt += oneDigit;
                slice -= oneDigit;
            } else if (5 < oneDigit) {
                cnt += oneDigit - 5;
                slice -= oneDigit - 5;
            }

            cnt += map[slice];
        }

        res.append(cnt).append("\n");
    }
    /*
    1
    76540123
     */

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