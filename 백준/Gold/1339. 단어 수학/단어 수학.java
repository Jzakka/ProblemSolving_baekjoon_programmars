import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

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
        long[] coefficients = new long[26];

        for (String word : words) {
            int e = word.length() - 1;

            for (int i = 0; i < word.length(); i++) {
                coefficients[word.charAt(i) - 'A'] += Math.pow(10, e - i);
            }
        }

        Arrays.sort(coefficients);

        long ans = 0;
        for (int i = 9; i >=0 ; i--) {
            ans += coefficients[i + 16] * i;
        }

        res.append(ans);
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

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}