import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        solution(Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray());
        bw.write(res.toString());
        bw.flush();
        bw.close();
    }

    private static void solution(int[] ships) {
        if (ships.length == 1) {
            res.append(ships[0]);
            return;
        }
        Arrays.sort(ships);
        int shipLen = ships.length;
        int situations = 1 << shipLen;
        boolean[][] visited = new boolean[situations][2]; // {횃불이 건너편이 아닐 때 방문, 횃불이 건너편일 때 방문}
        int[][] distants = new int[situations][2];
        Arrays.stream(distants).forEach(arr -> arr[0] = arr[1] = Integer.MAX_VALUE);
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        PQ.add(new int[]{0,0,0}); // {노드번호, 누적 가중치, 횃불의 위치}
        distants[0][0] = 0;

        while (!PQ.isEmpty()) {
            int[] poll = PQ.poll();
            int situation = poll[0];
            int torch = poll[2];
            if (visited[situation][torch]) {
                continue;
            }

            visited[situation][torch] = true;
            int accDist = poll[1];

            int nextTorch = torch ^ 1;
            if (torch == 0) { // 횃불이 건너편이 아닐 때
                for (int j = 0; j < shipLen; j++) {
                    if (((situation >> j) & 1) == 0) {
                        for (int k = j+1; k < shipLen; k++) {
                            if (ships[j] < ships[k] && ((situation >> k) & 1) == 0) {
                                int nextSituation = situation | (1 << j) | (1 << k);
                                if (visited[nextSituation][nextTorch]) {
                                    continue;
                                }
                                int distance = ships[k];
                                distants[nextSituation][nextTorch] = Math.min(distants[nextSituation][nextTorch], accDist + distance);
                                PQ.add(new int[]{nextSituation, distants[nextSituation][nextTorch], nextTorch});
                            }
                        }
                    }
                }
            } else {
                for (int j = 0; j < shipLen; j++) {
                    if (((situation >> j) & 1) == 1) {
                        int nextSituation = situation ^ (1 << j);
                        if (visited[nextSituation][nextTorch]) {
                            continue;
                        }
                        int distance = ships[j];
                        distants[nextSituation][nextTorch] = Math.min(distants[nextSituation][nextTorch], accDist + distance);
                        PQ.add(new int[]{nextSituation, distants[nextSituation][nextTorch], nextTorch});
                    }
                }
            }
        }

        int minDist = distants[situations - 1][1];
        res.append(minDist == Integer.MAX_VALUE ? -1 : minDist);
    }
}