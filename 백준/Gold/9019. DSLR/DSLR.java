import java.io.*;
import java.util.*;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static IntUnaryOperator[] ops = {Main::D, Main::S, Main::L, Main::R};
    private static char[] opLetter = {'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            int[] ab = getInts();
            solution(ab[0], ab[1]);
        }

        printRes();
    }

    private static void solution(int a, int b) {
        Queue<Integer> Q = new LinkedList<>();
        boolean[] visited = new boolean[10000];
        int[] parent = new int[10000];
        char[] prevOp = new char[10000];
        Q.add(a);
        visited[a] = true;

        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                Integer num = Q.poll();
                if (num == b) {
                    res.append(trace(parent, prevOp, a, b)).append("\n");
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    IntUnaryOperator op = ops[d];

                    int next = op.applyAsInt(num);
                    if (!visited[next]) {
                        parent[next] = num;
                        prevOp[next] = opLetter[d];

                        visited[next] = true;
                        Q.add(next);
                    }
                }
            }
        }
    }

    private static String trace(int[] parent, char[] prevOp, int src, int current) {
        Deque<Character> history = new ArrayDeque<>();
        while (src != current) {
            history.offerFirst(prevOp[current]);
            current = parent[current];
        }
        StringBuilder sb = new StringBuilder();
        while (!history.isEmpty()) {
            sb.append(history.pollFirst());
        }
        return sb.toString();
    }

    private static int D(int x){
        return 2 * x % 10000;
    }

    private static int S(int x) {
        return x == 0 ? 9999 : x - 1;
    }

    private static int L(int x) {
        int d1 = x / 1000;
        return (x * 10 + d1) % 10000;
    }

    private static int R(int x) {
        int d4 = x % 10;
        return (x / 10 + d4 * 1000);
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