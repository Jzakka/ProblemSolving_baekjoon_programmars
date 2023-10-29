import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

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
        int ans = 0;

        List<Integer> lte0 = new ArrayList<>();
        List<Integer> gt1 = new ArrayList<>();
        for (int num : arr) {
            if (num == 1) {
                ans++;
            } else if (num <= 0) {
                lte0.add(num);
            } else {
                gt1.add(num);
            }
        }

        lte0.sort(Comparator.comparingInt(i -> i));
        gt1.sort(Comparator.comparingInt(i -> i));

        for (int i = 0; i < lte0.size(); i+=2) {
            if (i == lte0.size() - 1) {
                ans += lte0.get(i);
            } else {
                ans += lte0.get(i) * lte0.get(i + 1);
            }
        }

        for (int i = gt1.size()-1; i >= 0; i-=2) {
            if (i == 0) {
                ans += gt1.get(i);
            } else {
                ans += gt1.get(i) * gt1.get(i - 1);
            }
        }

        res.append(ans);
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

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}