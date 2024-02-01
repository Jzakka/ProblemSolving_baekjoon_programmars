import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        long[] nums = new long[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        solution(nums);

        printRes();
    }

    private static void solution(long[] nums) {
        List<Long> minus = new ArrayList<>();
        List<Long> plus = new ArrayList<>();

        boolean zeroExists = false;
        int oneCnt = 0;
        for (long num : nums) {
            if (num > 0) {
                if (num == 1) {
                    oneCnt++;
                }
                plus.add(num);
            } else if (num < 0) {
                minus.add(num);
            } else {
                zeroExists = true;
            }
        }

        minus.sort(Comparator.comparingLong(c -> c)); // -5 -4 -3 -2 ...
        plus.sort(Comparator.comparing(num -> -num)); // 1 2 3 4 5

        BigInteger plusPoints;
        if (oneCnt > 0) {
            plus.removeIf(e -> e.equals(1L));
            plusPoints = getPoints(plus).add(new BigInteger(String.valueOf(oneCnt)));
        } else {
            plusPoints = getPoints(plus);
        }

        if ((minus.size() & 1) == 1 && (zeroExists)) {
            minus.remove(minus.size() - 1);
        }
        BigInteger ans = plusPoints.add(getPoints(minus));

        res.append(ans);
    }

    private static BigInteger getPoints(List<Long> orderedSequence) {
        BigInteger points = new BigInteger("0");
        for (int i = 0; i < orderedSequence.size(); i += 2) {
            if (i == orderedSequence.size() - 1) {
                points = points.add(new BigInteger(orderedSequence.get(i).toString()));
            } else {
                points = points.add(new BigInteger(orderedSequence.get(i).toString()).multiply(new BigInteger(orderedSequence.get(i + 1).toString())));
            }
        }
        return points;
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
4
3
1
1
1
 */