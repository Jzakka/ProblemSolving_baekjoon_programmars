import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

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
        Arrays.sort(arr);

        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] + 1 == arr[i + 1]) {
                int k = getK(arr, i);
                if (k == arr.length) {
                    int l = getL(arr, i);
                    swap(arr, l, i+1);
                } else {
                    swap(arr, i + 1, k);
                }
            }
        }

        for (int num : arr) {
            res.append(num).append(" ");
        }
    }

    private static int getL(int[] arr, int i) {
        int l = i;
        while (l >= 0 && arr[l] == arr[i]) {
            l--;
        }
        return l + 1;
    }

    private static int getK(int[] arr, int i) {
        int k = i + 1;
        while (k < arr.length && arr[k] == arr[i+1]) {
            k++;
        }
        return k;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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
1 3 2 4 3

5
1 3 4 4 5
 */