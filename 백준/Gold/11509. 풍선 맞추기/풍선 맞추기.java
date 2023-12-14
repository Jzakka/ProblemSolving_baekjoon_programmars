import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());


        int[] arrows = getInts();
        solution(arrows);

        printRes();
    }

    private static void solution(int[] arrows) {
        TreeSet<Integer>[] arrowIdx = new TreeSet[1_000_001];
        IntStream.rangeClosed(1, 1_000_000).forEach(i -> arrowIdx[i] = new TreeSet<>());

        for (int i = 0; i < arrows.length; i++) {
            arrowIdx[arrows[i]].add(i);
        }

        int ans = 0;
        for (int i = arrowIdx.length-1; i > 0; i--) {
            for (Integer src : arrowIdx[i]) {
                popBallon(i, src, arrowIdx);
                ans++;
            }
        }
        res.append(ans);
    }

    private static void popBallon(int height, Integer idx, TreeSet<Integer>[] arrowIdx) {
        while (height > 1) {
            Integer ceilIdx = arrowIdx[height-1].ceiling(idx);
            if (ceilIdx != null) {
                arrowIdx[height - 1].remove(ceilIdx);
            } else {
                return;
            }
            height--;
            idx = ceilIdx;
        }
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