import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String s = br.readLine();
        String p = br.readLine();

        solution(s, p);

        printRes();
    }

    private static void solution(String s, String p) {
        Map<Character, List<Integer>> dictionary = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char letter = s.charAt(i);
            dictionary.computeIfAbsent(letter, key -> new ArrayList<>()).add(i);
        }

        int i = 0;
        int cnt = 0;
        while (i < p.length()) {
            char letter = p.charAt(i);
            int maxLen = 0;
            for (Integer sIdx : dictionary.get(letter)) {
                int len = getLength(s, p, sIdx, i);
                if (len > maxLen) {
                    maxLen = len;
                }
            }
            i += maxLen;
            cnt++;
        }
        res.append(cnt);
    }

    private static int getLength(String s, String p, int sIdx, int pIdx) {
        int len = 0;
        while (sIdx < s.length() && pIdx < p.length() && s.charAt(sIdx++) == p.charAt(pIdx++)) {
            len++;
        }
        return len;
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