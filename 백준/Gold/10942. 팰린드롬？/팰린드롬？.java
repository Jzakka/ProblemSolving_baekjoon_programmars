import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] sequence = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        int m = Integer.parseInt(br.readLine());

        int[][] questions = new int[m][];

        for (int i = 0; i < m; i++) {
            questions[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(sequence, questions);

        printRes();
    }

    static class Range{
        int start, end;

        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Range range = (Range) o;
            return start == range.start && end == range.end;
        }

        @Override
        public int hashCode() {
            return Objects.hash(start, end);
        }
    }

    private static void solution(int[] sequence, int[][] questions) {
        int[] extended = new int[2*sequence.length + 1];

        for (int i = 0; i < extended.length; i++) {
            if ((i & 1) == 1) {
                extended[i] = sequence[i / 2];
            } else {
                extended[i] = -1;
            }
        }

        int[] lps = new int[extended.length];

        int r=0, c=0;
        for (int i = 0; i < lps.length; i++) {
            if (i < r) {
                lps[i] = Math.min(r - i, lps[2 * c - i]);
            }

            while (i - 1 - lps[i] >= 0 && i + 1 + lps[i] < extended.length
                    && extended[i + 1 + lps[i]] == extended[i - 1 - lps[i]]) {
                lps[i]++;
            }

            if (lps[i] > r) {
                c = i;
                r = c + lps[c];
            }
        }

        for (int[] question : questions) {
            int start = question[0]-1;
            int end = question[1] - 1;

            int lpsMid = start + end + 1;

            if (lps[lpsMid] >= end - start + 1) {
                res.append(1).append("\n");
            } else {
                res.append(0).append("\n");
            }
        }
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}