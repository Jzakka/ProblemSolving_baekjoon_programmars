import java.util.*;
import java.util.stream.IntStream;

class Solution {
    long[][] dist;
    List<int[]>[] adjacents;
    Map<Integer, Integer> trapIdx = new HashMap<>();
    int[] traps;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        this.traps = traps;
        dist = new long[n + 1][1 << traps.length];
        adjacents = new List[n + 1];

        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        IntStream.rangeClosed(1, n).forEach(i -> {
            adjacents[i] = new ArrayList<>();
        });

        for (int[] road : roads) {
            int src = road[0];
            int dest = road[1];
            int cost = road[2];

            int[] edge = {src, dest, cost};
            adjacents[src].add(edge);
            adjacents[dest].add(edge);
        }

        for (int i = 0; i < traps.length; i++) {
            int nodeNum = traps[i];
            trapIdx.put(nodeNum, i);
        }

        // {nodeNum, state, dist}
        PriorityQueue<long[]> PQ = new PriorityQueue<>(Comparator.comparingLong(a -> a[2]));
        dist[start][0] = 0;
        PQ.add(new long[]{start, 0, 0});

        while (!PQ.isEmpty()) {
            long[] cur = PQ.poll();
            int curNode = (int) cur[0];
            int curState = (int) cur[1];
            long curDist = cur[2];

            if (curDist > dist[curNode][curState]) {
                continue;
            }

            List<int[]> edges = getNexts(curNode, curState);

            for (int[] edge : edges) {
                int cost = edge[2];
                int dest = edge[1];
                int nextState = curState;

                if (trapIdx.containsKey(dest)) {
                    nextState = toggle(dest, curState);
                }

                if (dist[dest][nextState] > curDist + cost) {
                    dist[dest][nextState] = curDist + cost;
                    PQ.add(new long[]{dest, nextState, dist[dest][nextState]});
                }
            }
        }

        int result = (int) Arrays.stream(dist[end]).min().getAsLong();
        return result;
    }

    private List<int[]> getNexts(int curNode, int curState) {

        List<int[]> nexts = new ArrayList<>();
        for (int[] edge : adjacents[curNode]) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            if (src == curNode
                    && ((isActive(src, curState) && isActive(dest, curState))
                    || (!isActive(src, curState) && !isActive(dest, curState)))) {
                nexts.add(new int[]{src, dest, cost});
            } else if (dest == curNode &&
                    !((isActive(src, curState) && isActive(dest, curState))
                            || (!isActive(src, curState) && !isActive(dest, curState)))
            ) {
                nexts.add(new int[]{dest, src, cost});
            }
        }
        return nexts;
    }

    private int toggle(int trapNode, int state) {
        return state ^ (1 << trapIdx.get(trapNode));
    }

    private boolean isActive(int node, int curState) {
        if (!trapIdx.containsKey(node)) {
            return false;
        }
        Integer idx = trapIdx.get(node);
        return ((curState >> idx) & 1) == 1;
    }
}