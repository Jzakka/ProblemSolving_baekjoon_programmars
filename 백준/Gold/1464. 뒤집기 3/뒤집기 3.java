import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MAX_DISTANCE = 1_000;

    public static void main(String[] args) throws IOException {
        String S = br.readLine();

        String ans = solution(S);
        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static String solution(String s) {
        String current = s;
        for (int i = 1; i < s.length(); i++) {
            current = reverseOrNot(current, i);
        }

        return min(current, reverse(current));
    }

    private static String reverseOrNot(String current, int prefixLen) {
        String prefix = current.substring(0, prefixLen);
        String original = prefix + current.charAt(prefixLen);
        String reversed = reverse(reverse(prefix) + current.charAt(prefixLen));

        return min(original, reversed) + current.substring(prefixLen + 1);
    }

    private static String reverse(String string) {
        StringBuilder reversed = new StringBuilder();
        for (int i = string.length()-1; i >=0 ; i--) {
            reversed.append(string.charAt(i));
        }
        return reversed.toString();
    }

    private static String min(String str1, String str2) {
        return str1.compareTo(str2) < 0 ? str1 : str2;
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

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}