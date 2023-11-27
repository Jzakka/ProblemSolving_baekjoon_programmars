import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int n = info[0];
        int c = info[1];
        int m = Integer.parseInt(br.readLine());

        int[][] villages = new int[m][];

        for (int i = 0; i < m; i++) {
            villages[i] = getInts();
        }

        solution(n, c, villages);

        printRes();
    }

    private static void solution(int n, int c, int[][] villages) {
        Arrays.sort(villages, (v1, v2)->{
            if (v1[1] == v2[1]) {
                return v2[0] - v1[0];
            }
            return v1[1] - v2[1];
        });

        int[] load = new int[n + 1];

        int ans = 0;

        for (int[] village : villages) {
            int from = village[0];
            int to = village[1];
            int boxes = village[2];

            int maxLoad = Arrays.stream(load, from, to).max().getAsInt();
            int availableLoad = Math.min(boxes, c - maxLoad);

            ans+= availableLoad;
            for (int i = from; i < to; i++) {
                load[i] += availableLoad;
            }
        }

        res.append(ans);
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

from to boxes
3    4  40
1    4  10
2    3  40

#1
day1        day2        day3        day4
1 4 10      2 3 30      -30         -10
                        3 4 30      -30
#2
day1        day2        day3        day4
-           2 3 40      -40
                        3 4 40      -40

from to boxes
1    2  10
1    3  20
1    4  30
2    3  10
2    4  20
3    4  20

day1    day2    day3    day4


from to boxes
1    2  30
1    6  40
2    5  70
3    4  40
5    6  60

scenario#1
day 1       day 2       day 3       day4    day5        day 6
1 2 30      -30         -           -       -30         -60
1 6 30      2 5 30                          5 6 30
            1 6 30                          1 6 30
무조건 트럭을 꽉꽉 채우는 게 좋을까? no

1번마을에서 6번마을로 박스를 옮기는 것의 이득과
그렇게 안하고 2번 마을부터 얻는 이득 중 어느것이 이득인지 어떻게 비교할까

day1        day2        day3        day5        day5        day6
                                                5 6 60      -60

 */