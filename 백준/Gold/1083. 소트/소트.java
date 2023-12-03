import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] suffixSumGas = new int[1_000_001];

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] arr = getInts();

        int s = Integer.parseInt(br.readLine());
        solution(n, arr, s);

        printRes();
    }

    private static void solution(int n, int[] arr, int s) {
        for (int i = 0; i < n; i++) {
            s = pull(arr, i, i + s);
        }

        Arrays.stream(arr).forEach(num -> res.append(num).append(" "));
    }

    private static int pull(int[] arr, int start, int end) {
        int s = end - start;
        int maxVal = Integer.MIN_VALUE;
        int maxIdx = start;

        for (int i = start; i <= Math.min(arr.length-1, end); i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
                maxIdx = i;
            }
        }
        for (int i = maxIdx; i > start; i--) {
            swap(arr, i, i - 1);
        }

        return s - (maxIdx - start);
    }

    private static void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
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