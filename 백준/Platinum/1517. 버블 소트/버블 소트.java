import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] arr = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(arr);

        printRes();
    }


    private static void solution(int[] arr) {
        long ans = mergeSortAndCountSwap(arr, 0, arr.length);

        res.append(ans);
    }

    private static long mergeSortAndCountSwap(int[] arr, int start, int end) {
        if (end - start == 1) {
            return 0;
        }

        int mid = (start + end) / 2;
        long leftCount = mergeSortAndCountSwap(arr, start, mid);
        long rightCount = mergeSortAndCountSwap(arr, mid, end);

        // 정렬 로직
        int l = start;
        int r = mid;

        List<Integer> sorted = new ArrayList<>();

        long swapped = 0;

        while (l < mid && r < end) {
            if (arr[l] > arr[r]) {
                // 왼쪽에 있는 것 만큼 오른쪽이 앞당겨지는 것과 같다.
                swapped += mid - l;

                sorted.add(arr[r++]);
            } else {
                sorted.add(arr[l++]);
            }
        }
        while (l < mid) {
            sorted.add(arr[l++]);
        }
        while (r < end) {
            sorted.add(arr[r++]);
        }

        for (int i = 0; i < sorted.size(); i++) {
            arr[start + i] = sorted.get(i);
        }

        return leftCount + rightCount + swapped;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */