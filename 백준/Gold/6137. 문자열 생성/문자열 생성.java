import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Character> S = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            S.offerLast(br.readLine().charAt(0));
        }

        solution(S);

        printRes();
    }

    private static void solution(ArrayDeque<Character> s) {
        int cnt = 0;
        while (!s.isEmpty()) {
            res.append(selectChar(s));
            if (++cnt % 80 == 0) {
                res.append("\n");
            }
        }
    }

    private static char selectChar(ArrayDeque<Character> S) {
        Iterator<Character> left = S.iterator();
        Iterator<Character> right = S.descendingIterator();

        int size = S.size();
        for (int i = 0; i < size / 2; i++) {
            Character leftChar = left.next();
            Character rightChar = right.next();

            if (leftChar > rightChar) {
                return S.pollLast();
            } else if (leftChar < rightChar) {
                return S.pollFirst();
            }
        }
        return S.pollLast();
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
/*
6
A
C
D
B
C
A
 */