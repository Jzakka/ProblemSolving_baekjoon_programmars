import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] cupNoodles = new int[n][];

        for (int i = 0; i < n; i++) {
            cupNoodles[i] = getInts();
        }

        solution(cupNoodles);

        printRes();
    }

    private static void solution(int[][] cupNoodles) {
        int n = cupNoodles.length;
        Map<Integer, List> cupNoodleMap = new HashMap<>();
        for (int[] cupNoodle : cupNoodles) {
            int deadline = cupNoodle[0];
            int count = cupNoodle[1];

            if (!cupNoodleMap.containsKey(deadline)) {
                cupNoodleMap.put(deadline, new ArrayList());
            }

            cupNoodleMap.get(deadline).add(count);
        }

        PriorityQueue<Integer> PQ = new PriorityQueue<>((a, b) -> b - a);

        long ans = 0;
        for (int i = n; i >= 1; i--) {
            if(cupNoodleMap.containsKey(i)){
                PQ.addAll(cupNoodleMap.get(i));
            }

            if (!PQ.isEmpty()) {
                ans += PQ.poll();
            }
        }

        res.append(ans);
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