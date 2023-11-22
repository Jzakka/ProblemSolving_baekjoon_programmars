import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
    int[] router;
    public int[] solution(int[][] edges, int[] target) {
        int n = edges.length + 1;
        List<Integer>[] graph = new List[n + 1];
        router = new int[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> graph[i] = new ArrayList<>());

        Set<Integer> leaves = new HashSet<>();
        leaves.addAll(IntStream.rangeClosed(1, n).filter(i->target[i-1] > 0).boxed().collect(Collectors.toList()));

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];

            leaves.remove(src);

            graph[src].add(dest);
        }

        IntStream.rangeClosed(1, n).forEach(i -> Collections.sort(graph[i]));

        int[] count = new int[n + 1];

        getCount(graph, leaves, target, count);

        Arrays.fill(router, 0);
        List<Integer> ans = new ArrayList<>();

        distribute(graph, leaves, target, count, ans);

        if (ans.isEmpty()) {
            return new int[]{-1};
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private void distribute(List<Integer>[] graph, Set<Integer> leaves, int[] target, int[] count, List<Integer> ans) {
        int counts = Arrays.stream(count).sum();

        int i = 0;
        while (counts > 0) {
            int leaf = selectLeaf(graph, i);

            if (available(target, count, 1, leaf)) {
                target[leaf - 1] -= 1;
                ans.add(1);
            } else if (available(target, count, 2, leaf)) {
                target[leaf - 1] -= 2;
                ans.add(2);
            } else if (available(target, count, 3, leaf)) {
                target[leaf - 1] -= 3;
                ans.add(3);
            } else {
                ans.clear();
                return;
            }
            --count[leaf];
            counts--;
            i++;
        }
    }

    private boolean available(int[] target, int[] count, int oneTwoThree, int leaf) {
        if (target[leaf - 1] == 0) {
            return false;
        }

        int remainCount = count[leaf] - 1;
        int remainTarget = target[leaf - 1] - oneTwoThree;

        return remainCount * 3 >= remainTarget;
    }

    private void getCount(List<Integer>[] graph, Set<Integer> leaves, int[] target, int[] count) {
        int leavesCnt = leaves.size();

        int[] acc = new int[graph.length];

        int i = 0;
        while (leavesCnt > 0) {
            int leaf = selectLeaf(graph, i);

            count[leaf]++;

            if (acc[leaf] >= target[leaf - 1]) {
                continue;
            }

            acc[leaf] += 3;
            if (acc[leaf] >= target[leaf - 1]) {
                leavesCnt--;
            }
            i++;
        }
    }

    private int selectLeaf(List<Integer>[] graph, int i) {
        int cur = 1;

        while (!graph[cur].isEmpty()) {
            int route = router[cur];
            if (++router[cur] == graph[cur].size()) {
                router[cur] = 0;
            }

            cur = graph[cur].get(route);
        }

        return cur;
    }
}