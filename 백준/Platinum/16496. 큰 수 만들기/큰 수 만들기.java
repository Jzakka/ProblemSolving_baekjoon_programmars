import java.io.*;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] arr = getInts();

        solution(arr);

        printRes();
    }

    private static void solution(int[] arr) {
        StringBuilder ans = new StringBuilder();

        Arrays.stream(arr).mapToObj(String::valueOf).sorted(Main::compare)
                .forEach(ans::append);

        res.append(new BigInteger(ans.toString()));
    }

    private static Integer compare(String num1, String num2) {
        int diff = Math.abs(num1.length() - num2.length());

        int cmp = 0;
        if (num1.length() > num2.length()) {
            String expanded = num2 + num1.substring(0, diff);
            cmp = expanded.compareTo(num1);
        } else if (num1.length() < num2.length()) {
            String expanded = num1 + num2.substring(0, diff);
            cmp = num2.compareTo(expanded);
        } else {
            cmp = num2.compareTo(num1);
        }

        if (cmp == 0) {
            return (num2 + num1).compareTo(num1 + num2);
        }
        return cmp;
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
5
4 3 2 5 9
5
 */