import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final long MOD = 1_000_000_007;
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] origin = getInts();
        int[] objective = getInts();

        solution(origin, objective);

        printRes();
    }

    private static void solution(int[] origin, int[] objective) {
        int[] diff = IntStream.range(0, origin.length)
                .map(i -> objective[i] - origin[i])
                .toArray();

        int ans = tab(diff, 0, diff.length, 0, true);

        res.append(ans);
    }

    private static int tab(int[] diff, int s, int e, int minAbs, boolean isPlus) {
        if (isPlus) {
            IntStream.range(s, e).forEach(i -> diff[i] -= minAbs);
        } else {
            IntStream.range(s, e).forEach(i -> diff[i] += minAbs);
        }

        int i = s;
        int curNum;
        int ans=0;
        while (i < e) {
            curNum = diff[i];
            if (curNum == 0) {
                i++;
                continue;
            }
            int start = i;
            int min = Math.abs(curNum);
            while (++i < e && isSameSign(diff[i], curNum)) {
                min = Math.min(min, Math.abs(diff[i]));
            }
            ans += min + tab(diff, start, i, min, curNum > 0);
        }

        return ans;
    }

    private static boolean isSameSign(int num1, int num2) {
        return (num1 < 0 && num2 < 0) || (num1 > 0 && num2 > 0);
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