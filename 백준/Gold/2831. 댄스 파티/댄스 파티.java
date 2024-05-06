import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] men = getInts();
        int[] women = getInts();

        int ans = solution(n, men, women);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static int solution(int n, int[] men, int[] women) {
        int[] menHigher = Arrays.stream(men).filter(man -> man > 0).map(Math::abs).sorted().toArray();
        int[] menLower = Arrays.stream(men).filter(man -> man < 0).map(Math::abs).sorted().toArray();
        int[] womenHigher = Arrays.stream(women).filter(woman -> woman > 0).map(Math::abs).sorted().toArray();
        int[] womenLower = Arrays.stream(women).filter(woman -> woman < 0).map(Math::abs).sorted().toArray();

        int ans = match(menLower, womenHigher);
        ans += match(womenLower, menHigher);

        return ans;
    }

    private static int match(int[] lookDown, int[] lookUp) {
        int i=0, j = 0;

        int matchCnt = 0;
        while (i < lookDown.length && j < lookUp.length) {
            if (lookDown[i] > lookUp[j]) {
                i++;
                j++;
                matchCnt++;
            } else{
                i++;
            }
        }

        return matchCnt;
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