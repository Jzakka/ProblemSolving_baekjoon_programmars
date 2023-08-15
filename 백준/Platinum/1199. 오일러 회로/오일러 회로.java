import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static Scanner sc = new Scanner(System.in);

    private static Set[] edgeList;
    private static int[][] edgeMatrix;

    public static void main(String[] args) {
        int n = Integer.parseInt(sc.nextLine());
        edgeMatrix = new int[n][];
        edgeList = new Set[n];
        IntStream.range(0, n).forEach(i -> edgeList[i] = new HashSet());
        for (int i = 0; i < n; i++) {
            edgeMatrix[i] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < n; j++) {
                if (edgeMatrix[i][j] > 0) {
                    edgeList[i].add(j);
                }
            }
        }

        solution(n, edgeList);
    }

    private static void solution(int n, Set[] edgeList) {
        if (hasEulerCirculation(n)) {
            List<Integer> eulerPath = new ArrayList<>();
            getEulerCirculation(edgeList, eulerPath);
            StringBuilder res = new StringBuilder();
            eulerPath.stream().forEach(node -> res.append(node).append(" "));
            System.out.println(res);
            return;
        }
        System.out.println(-1);
    }

    private static void getEulerCirculation(Set<Integer>[] edgeList, List<Integer> eulerPath) {
        Stack<Integer> stk = new Stack<>();
        stk.push(0);
        while (!stk.isEmpty()) {
            Integer current = stk.peek();
            if (!edgeList[current].isEmpty()) {
                Integer next = edgeList[current].iterator().next();

                edgeMatrix[current][next]--;
                edgeMatrix[next][current]--;
                if (edgeMatrix[current][next] == 0) {
                    edgeList[current].remove(next);
                    edgeList[next].remove(current);
                }
                stk.push(next);
            } else {
                eulerPath.add(stk.pop() + 1);
            }
        }

        Collections.reverse(eulerPath);
    }

    private static boolean hasEulerCirculation(int n) {
        for (int i = 0; i < n; i++) {
            int degree = 0;
            for (int j = 0; j < n; j++) {
                degree += edgeMatrix[i][j];
            }
            if ((degree & 1) == 1) {
                return false;
            }
        }

        return true;
    }
}