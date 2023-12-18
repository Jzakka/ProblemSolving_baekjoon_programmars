import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int k,n;
        int[] info = getInts();
        k = info[0];
        n = info[1];

        String[] nums = new String[k];
        for (int i = 0; i < k; i++) {
            nums[i] = br.readLine();
        }

        solution(nums, n);

        printRes();
    }

    private static void solution(String[] nums, int n) {
        List<String> selected = new ArrayList<>();

        if (nums.length < n) {
            // 가장 길이가 길고 크기가 큰 수
            Arrays.sort(nums, (num1, num2)->{
                if (num1.length() == num2.length()) {
                    return num2.compareTo(num1);
                }
                return num2.length() - num1.length();
            });
            for (int i = 0; i < n - nums.length; i++) {
                selected.add(nums[0]);
            }
        }
        selected.addAll(Arrays.asList(nums));

        Collections.sort(selected, (num1, num2)->{
            int cmp = 0;
            if (num1.length() < num2.length()) {
                String ext = num1 + num2.substring(0, num2.length() - num1.length());
                cmp= num2.compareTo(ext);
            } else if (num1.length() > num2.length()) {
                String ext = num2 + num1.substring(0, num1.length() - num2.length());
                cmp = ext.compareTo(num1);
            } else {
                cmp = num2.compareTo(num1);
            }

            if (cmp == 0) {
                return (num2 + num1).compareTo(num1 + num2);
            }

            return cmp;
        });

        selected.forEach(num -> res.append(num));
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
1 1 1 3 3 3
 */