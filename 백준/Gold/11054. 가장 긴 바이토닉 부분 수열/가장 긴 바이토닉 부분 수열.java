import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] sequence = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(sequence);

        printRes();
    }

    private static void solution(int[] sequence) {
        List<Integer> ascending = new ArrayList<>();
        List<Integer> descending = new ArrayList<>();

        int[] ascDP = new int[sequence.length];
        int[] descDP = new int[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            int num = sequence[i];
            if (!ascending.isEmpty() && ascending.get(ascending.size() - 1) >= num) {
                int idx = lowerBound(ascending, num);
                ascending.set(idx, num);
            } else {
                ascending.add(num);
            }
            ascDP[i] = ascending.size();
        }

        for (int i = sequence.length - 1; i >= 0; i--) {
            int num = sequence[i];
            if (!descending.isEmpty() && descending.get(descending.size() - 1) >= num) {
                int idx = lowerBound(descending, num);
                descending.set(idx, num);
            } else {
                descending.add(num);
            }
            descDP[i] = descending.size();
        }

        res.append(IntStream.range(0, sequence.length).map(i -> ascDP[i] + descDP[i] - 1).max().getAsInt());
    }

    private static int lowerBound(List<Integer> list, int num) {
        int left,right;
        left = 0;
        right = list.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}