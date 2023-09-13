import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        String word = br.readLine();

        solution(word);

        printRes();
    }

    private static void solution(String word) {
        int[] DP = new int[word.length()];

        Arrays.fill(DP, Integer.MAX_VALUE);

        countPalindromes(DP, word, 0);

        res.append(DP[0]);
    }

    private static int countPalindromes(int[] DP, String word, int curIdx) {
        if (curIdx == word.length()) {
            return 0;
        }

        if (DP[curIdx] != Integer.MAX_VALUE) {
            return DP[curIdx];
        }

        for (int i = curIdx + 1; i <= word.length(); i++) {
            if (isPalindrome(word, curIdx, i)) {
                DP[curIdx] = Math.min(DP[curIdx], 1 + countPalindromes(DP, word, i));
            }
        }

        return DP[curIdx];
    }

    private static boolean isPalindrome(String word, int start, int end) {
        int left = start;
        int right = end - 1;

        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }


    private static int traversal(int[] dp, List<Integer>[] tree, Set<Integer> visited, int node) {
        visited.add(node);

        dp[node] = 1;
        for (Integer adjacent : tree[node]) {
            if (!visited.contains(adjacent)) {
                dp[node] += traversal(dp, tree, visited, adjacent);
            }
        }

        return dp[node];
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}