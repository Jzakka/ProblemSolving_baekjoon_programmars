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
        List<Integer> vector = new ArrayList<>();

        for (int number : sequence) {
            if (!vector.isEmpty() && vector.get(vector.size() - 1) >= number) {
                int lowerBoundIdx = lowerBound(vector, number);
                vector.set(lowerBoundIdx, number);
            } else {
                vector.add(number);
            }
        }

        res.append(vector.size());
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }

    private static int lowerBound(List<Integer> arr, int num){
        int left,right;
        left = 0;
        right = arr.size();

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}