import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int[] info = getInts();
            int n = info[0];
            int m = info[1];
            int[][] students = new int[m][];

            for (int i = 0; i < m; i++) {
                students[i] = getInts();
            }

            solution(n, students);
        }

        printRes();
    }

    private static void solution(int n, int[][] students) {
        List<int[]>[] map = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> map[i] = new ArrayList<>());

        for (int[] student : students) {
            map[student[0]].add(student);
        }

        PriorityQueue<int[]> PQ = new PriorityQueue<>((s1, s2) -> {
            if (s1[1] == s2[1]) {
                return s1[0] - s2[0];
            }
            return s1[1] - s2[1];
        });

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            while (!PQ.isEmpty() && PQ.peek()[1] < i) {
                PQ.poll();
            }
            PQ.addAll(map[i]);

            if (!PQ.isEmpty()) {
                PQ.poll();
                ans++;
            }
        }

        res.append(ans).append("\n");
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