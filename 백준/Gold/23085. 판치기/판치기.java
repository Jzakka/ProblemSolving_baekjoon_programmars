import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int n = info[0];
        int k = info[1];

        String init = br.readLine();

        solution(init, k);

        printRes();
    }

    private static void solution(String initStr, int k) {
        int n = initStr.length();
        int T = 0;

        for (int i = 0; i < n; i++) {
            if (initStr.charAt(i) == 'T') {
                T++;
            }
        }
        if (T == n) {
            res.append(0);
            return;
        }

        Queue<Integer> tailQ = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        tailQ.add(T);
        visited[T] = true;

        int ans = 0;
        while (!tailQ.isEmpty()) {
            int qLen = tailQ.size();

            for (int i = 0; i < qLen; i++) {
                Integer tail = tailQ.poll();
                Integer head = n - tail;


                for (int j = 0; j <= Math.min(head, k); j++) {
                    if (tail - (k - j) < 0) {
                        continue;
                    }
                    int nextTail = tail - k + 2 * j;

                    if (nextTail == n) {
                        res.append(ans + 1);
                        return;
                    }
                    if ( nextTail >= 0 && !visited[nextTail]) {
                        tailQ.add(nextTail);
                        visited[nextTail] = true;
                    }
                }
            }

            ans++;
        }

        res.append(-1);
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