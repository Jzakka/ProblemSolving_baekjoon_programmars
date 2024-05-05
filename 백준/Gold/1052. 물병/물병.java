import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int n = info[0];
        int k = info[1];

        int ans = solution(n, k);

        res.append(ans);
        bw.write(res.toString());
        bw.close();
    }

    private static int solution(int n, int k) {
        if (n <= k) {
            return 0;
        }

        String bin = Integer.toBinaryString(n);

        int oneCnt = countOne(bin);
        if (oneCnt <= k) {
            return 0;
        }

        int lastPos = -1;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1' && k-- > 0) {
                lastPos = i;
            }
        }

        long ceil = (long)Math.pow(2, bin.length() - lastPos);
        long remain = Integer.parseInt(bin.substring(lastPos), 2);

        return (int) (ceil - remain);
    }

    private static int countOne(String bin) {
        int cnt = 0;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                cnt++;
            }
        }
        return cnt;
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }
}
