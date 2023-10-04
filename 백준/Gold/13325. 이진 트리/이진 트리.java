import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        br.readLine();

        int[] edges = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(edges);

        printRes();
    }

    private static void solution(int[] edges) {
        int maxDist = Math.max(getDist(edges, 0, 0, 0), getDist(edges, 1, 0, 0));

        int leftAdded = adjust(edges, 0, 0, maxDist);
        int rightAdded = adjust(edges, 1, 0, maxDist);

        int ans = Arrays.stream(edges).sum() + leftAdded + rightAdded;
        res.append(ans);
    }

    private static int adjust(int[] edges, int current, int distance, final int maxDist) {
        distance += edges[current];
        if (!(current * 2 + 2 < edges.length && current * 2 + 3 < edges.length)) { //leafNode
            return maxDist - distance;
        }

        int leftAdded = adjust(edges, current * 2 + 2, distance, maxDist);
        int rightAdded = adjust(edges, current * 2 + 3, distance, maxDist);

        if (leftAdded < rightAdded) {
            edges[current * 2 + 3] += rightAdded - leftAdded;
            return leftAdded;
        } else {
            edges[current * 2 + 2] += leftAdded - rightAdded;
            return rightAdded;
        }
    }

    private static int getDist(int[] edges, int current, int distance, int maxDist) {
        distance += edges[current];
        if (!(current * 2 + 2 < edges.length && current * 2 + 3 < edges.length)) { //leafNode
            maxDist = Math.max(maxDist, distance);
            return maxDist;
        }

        maxDist = Math.max(
                getDist(edges, current * 2 + 2, distance, maxDist),
                getDist(edges, current * 2 + 3, distance, maxDist)
        );

        return maxDist;
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}