import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int m = info[1];

        String[] words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();
        }

        solution(words, m);

        printRes();
    }

    private static void solution(String[] words, int m) {
        Map<String, Integer> appeared = new HashMap<>();
        for (String word : words) {
            mapInsert(appeared, word);
        }

        Arrays.stream(words).collect(Collectors.toSet())
                .stream()
                .filter(word -> word.length() >= m)
                .sorted((w1, w2) -> {
                    if (appeared.get(w1) == appeared.get(w2)) {
                        if (w1.length() == w2.length()) {
                            return w1.compareTo(w2);
                        }
                        return w2.length() - w1.length();
                    }
                    return appeared.get(w2) - appeared.get(w1);
                })
                .forEach(word -> res.append(word).append("\n"));
    }

    public static void mapInsert(Map<String, Integer> map, String key) {
        if (!map.containsKey(key)) {
            map.put(key, 0);
        }
        map.put(key, map.get(key) + 1);
    }

    /**
     * 애들 수 - 연속된 최대 증가 순열의 길이 = 최소로 애들을 움직이는 수
     */
    private static void solution(int[] children) {
        // DP[i] : i번 어린이까지 연속되게 증가하는 최대 증가수열의 길이
        int[] DP = new int[children.length + 1];
        int[] idx = new int[children.length + 1];
        idx[0] = Integer.MAX_VALUE;

        for (int i = 0; i < children.length; i++) {
            int child = children[i];
            idx[child] = i;
        }

        int maxContinuousLisLen = 0;
        for (int child : children) {
            if (idx[child] > idx[child - 1]) {
                DP[child] = DP[child - 1] + 1;
            } else {
                DP[child] = 1;
            }

            maxContinuousLisLen = Math.max(maxContinuousLisLen, DP[child]);
        }

        res.append(children.length - maxContinuousLisLen);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/**
 *
 * DP[i-1][j-1][0]  |   DP[i-1][j][0]   |   DP[i-1][j+1][0]
 * DP[i-1][j-1][1]  |   DP[i-1][j][1]   |   DP[i-1][j+1][1]
 * ============================================================
 * DP[i][j-1][0]    |   DP[i][j][0]     |   DP[i][j+1][0]
 * DP[i][j-1][1]    |   DP[i][j][1]     |   DP[i][j+1][1]
 *
 * DP[i][j][0] = DP[i][j-1][1] + 1
 * DP[i][j][1] = DP[][][]
 */