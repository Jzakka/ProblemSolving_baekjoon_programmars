import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        solution(arr);

        printRes();
    }

    private static void solution(int[] arr) {
        long ans = answering(arr, 0, arr.length);

        res.append(ans);
    }

    private static long answering(int[] arr, int start, int end) {
        if (end - start == 1) {
            return arr[start];
        }

        long leftAns = answering(arr, start, (start + end)/2);
        long rightAns = answering(arr, (start + end) / 2, end);

        int mid = (start + end) / 2;
        int left = mid - 1;
        int right = mid + 1;

        long height = arr[mid];
        long width = 1;
        long curAns = height;

        while (left >= start && right < end) {
            if (arr[left] <= arr[right]) {
                height = Math.min(height, arr[right++]);
            } else {
                height = Math.min(height, arr[left--]);
            }
            curAns = Math.max(curAns, (++width) * height);
        }
        while (left >= start) {
            height = Math.min(height, arr[left--]);
            curAns = Math.max(curAns, (++width) * height);
        }
        while (right < end) {
            height = Math.min(height, arr[right++]);
            curAns = Math.max(curAns, (++width) * height);
        }

        return Math.max(curAns, Math.max(leftAns, rightAns));
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */