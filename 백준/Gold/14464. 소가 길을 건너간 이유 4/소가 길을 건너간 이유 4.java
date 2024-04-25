import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    public static int match(int[] chicken, int[][] cow) {
        Arrays.sort(chicken);
        Arrays.sort(cow, Comparator.<int[]>comparingInt(arr -> arr[0])
                .thenComparingInt(arr -> arr[1]));
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.<int[]>comparingInt(arr -> arr[1])
                .thenComparingInt(arr -> arr[0]));
        int i = 0;
        int j = 0;

        int ans = 0;
        while (i < chicken.length) {
            while (j < cow.length && chicken[i] >= cow[j][0]) {
                PQ.add(cow[j++]);
            }
            while (!PQ.isEmpty() && PQ.peek()[1] < chicken[i]) {
                PQ.poll();
            }
            if (PQ.isEmpty()) {
                i++;
                continue;
            }
            i++;
            PQ.poll();
            ans++;
        }
        return ans;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] info = bufferedReader.readLine().split("\\s+");
        int c = Integer.parseInt(info[0]);
        int n = Integer.parseInt(info[1]);

        int[] chicken = new int[c];
        for (int i = 0; i < c; i++) {
            chicken[i] = Integer.parseInt(bufferedReader.readLine());
        }
        int[][] cow = new int[n][];
        for (int i = 0; i < n; i++) {
            cow[i] = Arrays.stream(bufferedReader.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int ans = Result.match(chicken, cow);

        bufferedWriter.write(
                String.valueOf(ans)
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}