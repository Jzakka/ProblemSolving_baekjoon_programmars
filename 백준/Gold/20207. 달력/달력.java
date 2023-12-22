import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] schedule = new int[n][];
        for (int i = 0; i < n; i++) {
            schedule[i] = getInts();
        }

        solution(schedule);

        printRes();
    }

    private static void solution(int[][] schedule) {
        int[] calendar = new int[366];
        for (int[] work : schedule) {
            for (int i = work[0]; i <= work[1]; i++) {
                calendar[i]++;
            }
        }

        int i = 0;
        int height = 0;
        int width = 0;
        int ans = 0;
        while (i <= 365) {
            height = 0;
            width = 0;

            while (i <= 365 && calendar[i] == 0) {
                i++;
            }
            while (i <= 365 && calendar[i] > 0) {
                height = Math.max(height, calendar[i]);
                width++;
                i++;
            }
            ans += width * height;
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