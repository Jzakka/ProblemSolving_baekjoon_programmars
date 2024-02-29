import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] select = new int[n + 1];
            int[] nums = getInts();
            for (int i = 0; i < nums.length; i++) {
                select[i + 1] = nums[i];
            }
            solution(select);

        }

        printRes();
    }

    private static void solution(int[] select) {
        int n = select.length;
        Set<Integer> isCycle = new HashSet<>();
        Set<Integer> notCycle = new HashSet<>();
        boolean[] visited = new boolean[n];

        for (int i = 1; i < n; i++) {
            if (!isCycle.contains(i) && !notCycle.contains(i)) {
                getCycle(visited, isCycle, notCycle, select, i);
            }
        }

        res.append(notCycle.size()).append("\n");
    }

    private static void getCycle(boolean[] visited, Set<Integer> isCycle, Set<Integer> notCycle, int[] select, int start) {
        Deque<Integer> stk = new ArrayDeque<>();
        stk.offerLast(start);
        visited[start] = true;

        int ret;
        while (!stk.isEmpty()) {
            Integer curNode = stk.peekLast();

            visited[curNode] = true;

            int nextNode = select[curNode];

            if (visited[nextNode]) {
                ret = nextNode;
                if (isCycle.contains(ret) || notCycle.contains(ret)) {
                    notCycle.addAll(stk);
                    stk.clear();
                } else {
                    while (!stk.isEmpty()) {
                        Integer node = stk.pollLast();
                        isCycle.add(node);
                        if (node == ret) {
                            break;
                        }
                    }
                    notCycle.addAll(stk);
                    stk.clear();
                }
            } else {
                stk.offerLast(nextNode);
            }
        }
    }

    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static long[] getLongs() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}