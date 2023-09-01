import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] wires = new int[n][];

        for (int i = 0; i < n; i++) {
            wires[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        solution(wires);

        printRes();
    }

    private static void solution(int[][] wires) {
        Arrays.sort(wires, Comparator.comparingInt(wire -> wire[0]));
        int minA = Integer.MAX_VALUE;
        int maxA = Integer.MIN_VALUE;
        for (int[] wire : wires) {
            minA = Math.min(minA, wire[0]);
            maxA = Math.max(maxA, wire[0]);
        }


        TreeSet<int[]> orderedByBNum = new TreeSet<>(Comparator.comparingInt(wire -> wire[1]));
        orderedByBNum.add(new int[]{0, 0});
        int[] DP = new int[501];
        DP[0] = 0;

        int ans = 0;
        for (int[] wire : wires) {
            for (int[] checkedWire : orderedByBNum) {
                int bNum = checkedWire[1];
                if (bNum > wire[1]) {
                    break;
                }
                DP[wire[0]] = Math.max(DP[wire[0]], DP[checkedWire[0]] + 1);
            }
            orderedByBNum.add(wire);
            ans = Math.max(DP[wire[0]], ans);
        }

        int noCnt = 0;
        for (int i = minA; i <= maxA; i++) {
            if (DP[i] == 0) {
                noCnt++;
            }
        }

        res.append((maxA - minA + 1) - ans - noCnt);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}