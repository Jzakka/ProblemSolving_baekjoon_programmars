import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int[][] prerequisites = new int[info[1]][];
        for (int i = 0; i < info[1]; i++) {
            prerequisites[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(n, prerequisites);

        printRes();
    }

    private static void solution(int n, int[][] prerequisites) {
        // 선수과목을 들을 때마다 과목의 DP값을 하나씩 감소시킬 거임
        int[] DP = new int[n + 1];

        Set<Integer> notRoots = Arrays.stream(prerequisites)
                .mapToInt(row -> row[1])
                .boxed()
                .collect(Collectors.toSet());

        int[] roots = IntStream.rangeClosed(1, n).filter(i -> !notRoots.contains(i)).toArray();

        List<Integer>[] nextSubjects = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> nextSubjects[i] = new ArrayList<>());

        for (int[] prerequisite : prerequisites) {
            int src = prerequisite[0];
            int dest = prerequisite[1];

            nextSubjects[src].add(dest);
            DP[dest]++;
        }

        int[] ans = new int[n + 1];

        Queue<Integer> Q = new LinkedList<>();
        Arrays.stream(roots).forEach(Q::add);

        int semester = 1;
        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                Integer subject = Q.poll();
                ans[subject] = semester;

                for (Integer nextSubject : nextSubjects[subject]) {
                    if (--DP[nextSubject] == 0) {
                        Q.add(nextSubject);
                    }
                }
            }
            semester++;
        }

        IntStream.rangeClosed(1, n).forEach(i -> res.append(ans[i]).append(" "));
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}