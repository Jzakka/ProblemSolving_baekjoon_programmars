import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        List<Integer>[] workTree = new List[n + 1];
        int[] workTimes = new int[n + 1];
        IntStream.rangeClosed(1, n).forEach(i->workTree[i] = new ArrayList<>());

        List<Integer> roots = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int[] info = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            workTimes[i] = info[0];

            if (info[1] == 0) {
                roots.add(i);
            }

            for (int j = 0; j < info[1]; j++) {
                workTree[info[j+2]].add(i);
            }
        }

        solution(workTimes, workTree, roots);

        printRes();
    }

    private static void solution(int[] workTimes, List<Integer>[] workTree, List<Integer> roots) {
        int[] DP = new int[workTree.length];

        for (Integer root : roots) {
            Queue<Integer> Q = new LinkedList<>();
            Q.add(root);
            DP[root] = workTimes[root];

            while (!Q.isEmpty()) {
                int qLen = Q.size();

                for (int i = 0; i < qLen; i++) {
                    int node = Q.poll();

                    for (Integer nextWork : workTree[node]) {
                        if (DP[nextWork] < DP[node] + workTimes[nextWork]) {
                            Q.add(nextWork);
                            DP[nextWork] = DP[node] + workTimes[nextWork];
                        }
                    }
                }
            }
        }

        res.append(Arrays.stream(DP).max().getAsInt());
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}