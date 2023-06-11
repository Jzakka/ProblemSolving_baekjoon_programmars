import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    public int solution(int n, int[][] costs) {
        int[] parents = IntStream.range(0, n).toArray();

        int costSum = 0;
        LinkedHashSet<Cost> costSet = Arrays.stream(costs)
                .map(arr -> new Cost(arr[0], arr[1], arr[2]))
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        while (!costSet.isEmpty()) {
            Cost cost = costSet.stream().findFirst().get();
            costSet.remove(cost);
            if (isNotCycle(parents, cost)) {
                costSum += cost.cost;
                union(parents, cost);
            }
        }

        return costSum;
    }

    private boolean isNotCycle(int[] parents, Cost cost) {
        return parents[cost.src] != parents[cost.dest];
    }

    private void union(int[] parents, Cost cost) {
        int parent = Math.min(cost.src, cost.dest);
        int child = Math.max(cost.src, cost.dest);

        int oldParent = parents[child];
        int newParent = parents[parent];

        for (int i = 0; i < parents.length; i++) {
            if (parents[i] == oldParent) {
                parents[i] = newParent;
            }
        }
    }

    public static class Cost implements Comparable<Cost>{
        int src;
        int dest;
        int cost;

        public Cost(int src, int dest, int cost) {
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public int compareTo(Cost o) {
            return cost - o.cost;
        }
    }
}