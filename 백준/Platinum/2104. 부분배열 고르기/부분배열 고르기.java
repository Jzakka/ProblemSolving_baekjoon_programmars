import java.io.*;
import java.util.*;

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
//        SegmentNode min = constructMinTree(arr, 0, arr.length);
//        SegmentNode sum = constructSumTree(arr, 0, arr.length);

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

        long subSum = arr[mid];
        long subMin = arr[mid];
        long curAns = subSum * subMin;

        while (left >= start && right < end) {
            if (arr[left] <= arr[right]) {
                subSum += arr[right];
                subMin = Math.min(subMin, arr[right++]);
            } else {
                subSum += arr[left];
                subMin = Math.min(subMin, arr[left--]);
            }
            curAns = Math.max(curAns, subSum * subMin);
        }
        while (left >= start) {
            subSum += arr[left];
            subMin = Math.min(subMin, arr[left--]);
            curAns = Math.max(curAns, subSum * subMin);
        }
        while (right < end) {
            subSum += arr[right];
            subMin = Math.min(subMin, arr[right++]);
            curAns = Math.max(curAns, subSum * subMin);
        }

        return Math.max(curAns, Math.max(leftAns, rightAns));
    }

    public static class SegmentNode {

        int start, end, value;
        SegmentNode left, right;
        public SegmentNode(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

    }
    public static SegmentNode constructMinTree(int[] arr, int start, int end) {
        if (end - start == 1) {
            return new SegmentNode(start, end, arr[start]);
        }

        SegmentNode left = constructMinTree(arr, start, (start + end) / 2);
        SegmentNode right = constructMinTree(arr, (start + end) / 2, end);

        SegmentNode current = new SegmentNode(left.start, right.end, Math.min(left.value, right.value));
        current.left = left;
        current.right = right;

        return current;
    }

    public static SegmentNode constructSumTree(int[] arr, int start, int end) {
        if (end - start == 1) {
            return new SegmentNode(start, end, arr[start]);
        }

        SegmentNode left = constructSumTree(arr, start, (start + end) / 2);
        SegmentNode right = constructSumTree(arr, (start + end) / 2, end);

        SegmentNode current = new SegmentNode(left.start, right.end, left.value+ right.value);
        current.left = left;
        current.right = right;

        return current;
    }

    private static long subSum(SegmentNode current, int start, int end) {
        if (current != null && start <= current.start && current.end <= end) {
            return current.value;
        } else if (current == null || (current.end <= start && end <= current.start)) { // 현재 노드가 (-INF, start), [end, INF) 에 포함될 때
            return 0;
        }

        return subSum(current.left, start, end) + subSum(current.right, start, end);
    }

    public static int subMin(SegmentNode current, final int start, final int end) {
        // 현재 노드가 [start, end] 에 포함될 때
        if (current != null && start <= current.start && current.end <= end) {
            return current.value;
        } else if (current == null || (current.end <= start && end <= current.start)) { // 현재 노드가 (-INF, start), [end, INF) 에 포함될 때
            return Integer.MAX_VALUE;
        }

        int subMin = Integer.MAX_VALUE;

        subMin = Math.min(subMin, subMin(current.left, start, end));
        subMin = Math.min(subMin, subMin(current.right, start, end));

        return subMin;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */