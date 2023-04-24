import java.util.*;

class Solution {
    Map<Integer, ArrayList<Integer>> edge = new HashMap<>();
    int[][] counts;
    int N;
    Set<Integer> visited = new HashSet<>();

    public int solution(int n, int[][] wires) {
        N = n;
        for (int i = 1; i <= n; i++) {
            edge.put(i, new ArrayList<>());
        }
        counts = new int[n + 1][n + 1];
        for (int[] wire : wires) {
            edge.get(wire[0]).add(wire[1]);
            edge.get(wire[1]).add(wire[0]);
        }

        count(1);

        int diff = Integer.MAX_VALUE;
        for (int[] wire : wires) {
            int src = wire[0];
            int dest = wire[1];

            diff = Math.min(diff, Math.abs(counts[src][dest] - counts[dest][src]));
        }

        return diff;
    }

    private int count(int node) {
        visited.add(node);
        if (edge.get(node).size() == 1) {
            int parent = edge.get(node).get(0);
            counts[node][parent] = 1;
            counts[parent][node] = N - 1;
        }

        int branch = 1;

        for (int adjacent : edge.get(node)) {
            if (!visited.contains(adjacent)) {
                int nodeCnt = count(adjacent);
                counts[adjacent][node] = nodeCnt;
                counts[node][adjacent] = N - nodeCnt;
                branch += nodeCnt;
            }
        }

        return branch;
    }
}