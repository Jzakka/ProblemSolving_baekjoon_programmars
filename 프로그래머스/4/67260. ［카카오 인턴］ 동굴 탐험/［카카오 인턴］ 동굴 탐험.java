import java.util.*;
import java.util.stream.IntStream;

class Solution {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[] prerequisiteCnt;

    public boolean solution(int n, int[][] path, int[][] orders) {
        prerequisiteCnt = new int[n];
        IntStream.range(0, n).forEach(i -> graph.put(i, new ArrayList<>()));

        for (int[] edge : path) {
            int src = edge[0];
            int dest = edge[1];

            graph.get(src).add(dest);
            graph.get(dest).add(src);
        }

        Queue<Integer> Q_ = new LinkedList<>();
        boolean[] visited_ = new boolean[n];
        Q_.add(0);
        while (!Q_.isEmpty()) {
            int qLen = Q_.size();
            for (int i = 0; i < qLen; i++) {
                Integer cur = Q_.poll();
                visited_[cur] = true;

                for (Integer child : graph.get(cur)) {
                    if (!visited_[child]) {
                        prerequisiteCnt[child]++;
                        Q_.add(child);
                    }
                }
            }
        }

        for (int[] order : orders) {
            int src = order[0];
            int dest = order[1];

            graph.get(src).add(dest);
            prerequisiteCnt[dest]++;
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> Q = new LinkedList<>();
        if (prerequisiteCnt[0] == 0) {
            Q.add(0);
        }

        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                Integer cur = Q.poll();

                visited[cur] = true;

                for (Integer child : graph.get(cur)) {
                    if (!visited[child] && --prerequisiteCnt[child] == 0) {
                        Q.add(child);
                    }
                }
            }
        }

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
}