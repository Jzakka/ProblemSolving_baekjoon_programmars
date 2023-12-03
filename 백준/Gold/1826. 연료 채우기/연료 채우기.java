import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] suffixSumGas = new int[1_000_001];

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] stations = new int[n][];

        for (int i = 0; i < n; i++) {
            stations[i] = getInts();
        }
        int[] info = getInts();
        int dest = info[0];
        int initGas = info[1];

        solution(stations, dest, initGas);

        printRes();
    }

    private static void solution(int[][] stations, int dest, int initGas) {
        int curPos = initGas;

        Arrays.sort(stations, Comparator.comparingInt(s -> s[0]));

        int ans = 0;

        PriorityQueue<Integer> PQ = new PriorityQueue<>((s1, s2) -> s2 - s1);

        int stationIdx = 0;
        while (curPos < dest) {
            while (stationIdx < stations.length && stations[stationIdx][0] <= curPos) {
                PQ.add(stations[stationIdx++][1]);
            }

            if (PQ.isEmpty()) {
                ans = -1;
                break;
            }
            if (stationIdx < stations.length) {
                while (!PQ.isEmpty() && curPos < stations[stationIdx][0]) {
                    curPos += PQ.poll();
                    ans++;
                }
            } else { // 이제 마을까지만 가면 됨
                while (!PQ.isEmpty() && curPos < dest) {
                    curPos += PQ.poll();
                    ans++;
                }
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
1
10 1
25 2
 */