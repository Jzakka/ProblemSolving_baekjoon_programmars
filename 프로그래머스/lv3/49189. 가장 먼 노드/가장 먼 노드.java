import java.util.*;
import java.util.stream.Collectors;

class Solution {
    List<Integer>[] edges;
    Map<Integer, Integer> visited = new HashMap<>();

    public int solution(int n, int[][] edge) {
        edges = new List[n + 1];

        for (int i = 0; i < n + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int[] e : edge) {
            int src = e[0];
            int dest = e[1];
            edges[src].add(dest);
            edges[dest].add(src);
        }

        List<Integer> farNodes = bfs();

        return farNodes.size();
    }

    private List<Integer> bfs() {
        Queue<Integer> Q = new LinkedList<>();
        visited.put(1, 0);
        Q.add(1);

        int maxLevel = 0;
        int level = 1;
        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                Integer node = Q.poll();

                List<Integer> nexts = getNexts(node);

                Q.addAll(nexts);
                int finalLevel = level;
                maxLevel = level;
                nexts.forEach(next -> visited.put(next, finalLevel));
            }
            level++;
        }

        int finalMaxLevel = maxLevel - 1;
        return visited.keySet().stream()
                .filter(key -> visited.get(key) == finalMaxLevel)
                .collect(Collectors.toList());
    }

    private List<Integer> getNexts(Integer node) {
        return edges[node].stream()
                .filter(next -> !visited.containsKey(next))
                .collect(Collectors.toList());
    }
}