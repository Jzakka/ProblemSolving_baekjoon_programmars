import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int[] size = getInts();
            long[] bob = getLongs();
            long[] alice = getLongs();

            solution(size[2], bob, alice);
        }

        printRes();
    }

    private static void solution(int k, long[] bob, long[] alice) {
        // DP[i][j] :  (밥/앨리스)가 i번 카드까지 사용해서 j장으로 만들 수 있는 숫자
        List<Long>[][] bobDP = new List[bob.length][k + 1];
        List<Long>[][] aliceDP = new List[alice.length][k + 1];

        for (int i = 0; i < bobDP.length; i++) {
            for (int j = 0; j < bobDP[0].length; j++) {
                bobDP[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < aliceDP.length; i++) {
            for (int j = 0; j < aliceDP[0].length; j++) {
                aliceDP[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < bobDP.length; i++) {
            bobDP[i][1].add(bob[i]);
            if (i > 0) {
                bobDP[i][1].addAll(bobDP[i-1][1]);
                for (int j = 2; j < bobDP[0].length; j++) {
                    bobDP[i][j].addAll(bobDP[i - 1][j]);

                    for (Long num : bobDP[i - 1][j - 1]) {
                        bobDP[i][j].add(num + bob[i]);
                    }
                }
            }
        }

        for (int i = 0; i < aliceDP.length; i++) {
            aliceDP[i][1].add(alice[i]);
            if (i > 0) {
                aliceDP[i][1].addAll(aliceDP[i-1][1]);
                for (int j = 2; j < aliceDP[0].length; j++) {
                    aliceDP[i][j].addAll(aliceDP[i-1][j]);
                    for (Long num : aliceDP[i - 1][j - 1]) {
                        aliceDP[i][j].add(num + alice[i]);
                    }
                }
            }
        }

        TreeSet<Long> bobResult = new TreeSet<>();
        for (int i = 0; i < bobDP.length; i++) {
            bobResult.addAll(bobDP[i][k]);
        }

        TreeSet<Long> aliceResult = new TreeSet<>();
        for (int i = 0; i < aliceDP.length; i++) {
            aliceResult.addAll(aliceDP[i][k]);
        }

        Long[] bobSums = bobResult.stream().sorted().toArray(Long[]::new);
        Long[] aliceSums = aliceResult.stream().sorted().toArray(Long[]::new);

        int p1 = 0, p2 = 0;

        long maxAns =  Math.max(Math.abs(bobSums[0] - aliceSums[aliceSums.length - 1]),
                Math.abs(bobSums[bobSums.length - 1] - aliceSums[0])) ;

        long minAns = Long.MAX_VALUE;
        while (p1 < bobSums.length && p2 < aliceSums.length) {
            long diff = bobSums[p1] - aliceSums[p2];
            minAns = Math.min(Math.abs(diff), minAns);

            if (diff > 0) {
                p2++;
            } else if (diff < 0) {
                p1++;
            } else {
                break;
            }
        }

        res.append(minAns).append(" ").append(maxAns).append("\n");

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