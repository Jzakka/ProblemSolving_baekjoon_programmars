import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MAX_DISTANCE = 1_000;

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] positions = new int[n + 2][];

            for (int i = 0; i < n + 2; i++) {
                positions[i] = getInts();
            }

            String ans = solution(n, positions);
            res.append(ans).append("\n");
        }

        bw.write(res.toString());
        bw.close();
    }

    private static String solution(int n, int[][] positions) {
        int len = positions.length;

        final int DESTINATION = len - 1;

        int[][] graph = new int[len][len];
        construct(positions, graph);

        boolean[] visited = new boolean[len];
        boolean ans = bfs(graph, visited,  DESTINATION);

        return ans ? "happy" : "sad";
    }

    // 10000
    private static void construct(int[][] positions, int[][] graph) {
        for (int i = 0; i < positions.length; i++) {
            for (int j = 0; j < positions.length; j++) {
                int distance = getDistance(positions[i], positions[j]);
                graph[i][j] = distance;
            }
        }
    }

    private static int getDistance(int[] position1, int[] position2) {
        return Math.abs(position1[0] - position2[0]) + Math.abs(position1[1] - position2[1]);
    }

    private static boolean bfs(int[][] graph, boolean[] visited, int destination) {
        Queue<Integer> Q = new LinkedList<>();
        Q.add(0);
        visited[0] = true;

        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                Integer current = Q.poll();

                List<Integer> nextNodes = getNext(graph, visited, current);

                for (int nextNode : nextNodes) {
                    if (nextNode==destination) {
                        return true;
                    }
                    visited[nextNode] = true;
                    Q.add(nextNode);
                }
            }
        }

        return false;
    }

    private static List<Integer> getNext(int[][] graph, boolean[] visited, Integer current) {
        List<Integer> nextNodes = new ArrayList<>();
        for (int adjacent = 0; adjacent < graph[current].length; adjacent++) {
            if (!visited[adjacent] && graph[current][adjacent] <= MAX_DISTANCE) {
                nextNodes.add(adjacent);
            }
        }
        return nextNodes;
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}