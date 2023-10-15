import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final String UNIST = "UNIST";
    private static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        solution(words);

        printRes();
    }

    private static void solution(String[] words) {
        int[] DP = new int[UNIST.length() + 1];
        DP[0] = 1;

        for (String word : words) {
            int[] range = getPoistion(word);
            int start = range[0];
            int end = range[1];

            for (int i = 0; i < DP.length; i++) {
                if (start < i && i <= end) {
                    DP[i] += DP[start];
                    DP[i] %= MOD;
                }
            }
        }

        res.append(DP[DP.length - 1]);
    }

    private static int[] getPoistion(String word) {
        switch (word.charAt(0)) {
            case 'U':
                return getPoistion(word, 0);
            case 'N':
                return getPoistion(word, 1);
            case 'I':
                return getPoistion(word, 2);
            case 'S':
                return getPoistion(word, 3);
            case 'T':
                return getPoistion(word, 4);
            default:
                break;
        }
        return new int[]{-1, -1};
    }

    private static int[] getPoistion(String word, int unistPos) {
        int[] pos = {unistPos, 0};

        int wordPos = 0;
        while (wordPos < word.length() && unistPos < UNIST.length() && word.charAt(wordPos) == UNIST.charAt(unistPos)) {
            unistPos++;
            wordPos++;
        }
        pos[1] = unistPos;

        return pos;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
