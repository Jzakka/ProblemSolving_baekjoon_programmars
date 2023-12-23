import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            solution(Integer.parseInt(br.readLine()));
        }
        printRes();
    }

    private static void solution(int time) {
        int[] ans = {0, 0, 0, 0, 0};

        ans[0] += time / 60;
        time -= ans[0] * 60;


        int ten = time / 10;
        if (ten <= 3) { // 십의자리가 0,1,2,3
            ans[1] += ten;
            time -= ans[1] * 10;

            int one = time;
            if (one <= 5) {
                ans[3] += one;
            } else {
                if (ten == 3) {
                    ans[0]++;
                    ans[1] = 0;
                    ans[2] += 2;
                    ans[4] += 10 - one;
                } else {
                    ans[4] += 10 - one;
                    ans[1]++;
                }
            }
        } else {
            ans[0]++;
            ans[2] += 6 - ten;
            time %= 10;

            int one = time;
            if (one < 5) {
                ans[3] += one;
            } else {
                ans[4] += 10 - one;
                ans[2]--;
            }
        }


        for (int cnt : ans) {
            res.append(cnt).append(" ");
        }
        res.append("\n");
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