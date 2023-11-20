import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        char[] src = new char[n];
        char[] dest = new char[n];

        br.read(src);
        br.readLine();
        br.read(dest);

        solution(src, dest);

        printRes();
    }

    private static void solution(char[] src, char[] dest) {
        int n = src.length;

        char[] copy1 = Arrays.copyOf(src, n);
        char[] copy2 = Arrays.copyOf(src, n);

        // copy1 은 첫번째 인덱스를 뒤집은 케이스
        flip(0, copy1);
        int ans1 = 1;
        for (int i = 1; i < copy1.length; i++) {
            if(copy1[i - 1] != dest[i - 1]){
                flip(i, copy1);
                ans1++;
            }
        }
        if (copy1[copy1.length - 1] != dest[dest.length - 1]) {
            ans1 = Integer.MAX_VALUE;
        }

        int ans2 = 0;
        for (int i = 1; i < copy2.length; i++) {
            if(copy2[i - 1] != dest[i - 1]){
                flip(i, copy2);
                ans2++;
            }
        }
        if (copy2[copy2.length - 1] != dest[dest.length - 1]) {
            ans2 = Integer.MAX_VALUE;
        }

        int ans = Math.min(ans1, ans2);
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }

        res.append(ans);
    }

    private static void flip(int idx, char[] seq){
        if (idx - 1 >= 0) {
            seq[idx - 1] = flip(seq[idx - 1]);
        }
        seq[idx] = flip(seq[idx]);
        if (idx + 1 < seq.length) {
            seq[idx + 1] = flip(seq[idx + 1]);
        }
    }

    private static char flip(char c) {
        return (c == '0' ? '1' : '0');
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