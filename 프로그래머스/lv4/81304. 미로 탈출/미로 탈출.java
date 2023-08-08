import java.util.*;
import java.util.stream.*;

class Solution {

    class Edge {
        int src, dest, cost;

        public Edge(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }
    }

    int n, start, end;

    Set<Integer> trapSet = new HashSet<>();
    List<Edge>[] edges;
    Map<Integer, Integer> trapIdx = new HashMap<>();
    int maxStates;
    Map<Integer, Edge> hashedEdge = new HashMap<>();
    int[] traps;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        this.n = n;
        this.start = start;
        this.end = end;
        this.trapSet = Arrays.stream(traps).boxed().collect(Collectors.toSet());
        edges = IntStream.rangeClosed(0, n + 1).mapToObj(ArrayList::new).toArray(List[]::new);
        maxStates = 1 << traps.length;
        this.traps = traps;

        for (int[] road : roads) {
            int src = road[0];
            int dest = road[1];
            int cost = road[2];
            int key = cantorPair(src, dest);

            if (!hashedEdge.containsKey(key)) {
                Edge edge = new Edge(src, dest, cost);

                edges[src].add(edge);
                if (this.trapSet.contains(src) || this.trapSet.contains(dest)) {
                    edges[dest].add(edge);
                }
            } else if (hashedEdge.get(key).getCost() > cost) {
                hashedEdge.get(key).cost = cost;
            }
        }

        for (int i = 0; i < traps.length; i++) {
            int trap = traps[i];
            trapIdx.put(trap, i);
        }

        return dijkstra();
    }

    class NodeAndState {
        int nodeNum;
        int state;
        int dist;

        public NodeAndState(int nodeNum, int state, int dist) {
            this.nodeNum = nodeNum;
            this.state = state;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            NodeAndState that = (NodeAndState) o;
            return nodeNum == that.nodeNum && state == that.state;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nodeNum, state);
        }

        @Override
        public String toString() {
            return "NodeAndState{" +
                    "nodeNum=" + nodeNum +
                    ", state=" + state +
                    '}';
        }
    }

    private int dijkstra() {
        boolean[][] visited = new boolean[n + 1][maxStates];
        long[][] distance = new long[n + 1][maxStates];
        for (long[] d : distance) {
            for (int i = 0; i < maxStates; i++) {
                d[i] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<NodeAndState> Q = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));

        addQ(Q, distance, start, 0, 0);

        while (!Q.isEmpty()) {
            NodeAndState ns = Q.poll();
            int node = ns.nodeNum;
            int curState = ns.state;

            if (visited[node][curState]) {
                continue;
            }

            List<int[]> nextEdges = getNext(curState, node);

            visited[node][curState] = true;

            for (int[] edge : nextEdges) {
                int dest = edge[1];
                int cost = edge[2];
                int nextState = curState;

                if (trapSet.contains(dest)) {
                    nextState = changeState(curState, dest);
                }

                if (!visited[dest][nextState] && distance[dest][nextState] > distance[node][curState] + cost) {
                    addQ(Q, distance, dest, nextState, distance[node][curState] + cost);
                }
            }
        }

        return (int) Arrays.stream(distance[end]).min().orElseThrow();
    }

    void addQ(PriorityQueue<NodeAndState> Q, long[][] distance, int node, int state, long dist) {
        NodeAndState next = new NodeAndState(node, state, (int) dist);
        distance[node][state] = dist;
        Q.add(next);
    }

    private List<int[]> getNext(int state, Integer node) {
        Set<Integer> onTraps = new HashSet<>();

        int idx = 0;
        while (state > 0) {
            if ((state & 1) == 1) {
                int trap = traps[idx];
                onTraps.add(trap);
            }
            state >>= 1;
            idx++;
        }

        return edges[node].stream().map(edge -> {
            if (onTraps.containsAll(Arrays.asList(edge.src, edge.dest)) || (!onTraps.contains(edge.src) && !onTraps.contains(edge.dest))) {
                return new int[]{edge.src, edge.dest, edge.cost};
            }
            return new int[]{edge.dest, edge.src, edge.cost};
        }).filter(e->e[0] == node).collect(Collectors.toList());
    }

    private int changeState(int state, int node) {
        Integer idx = trapIdx.get(node);
        int mask = 1 << idx;

        return state ^ mask;
    }

    int cantorPair(int a, int b) {
        return (a + b) * (a + b + 1) / 2 + b;
    }
}