import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] jewels = new long[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels[i][0] = Long.parseLong(st.nextToken());
            jewels[i][1] = Long.parseLong(st.nextToken());
        }

        long[] bags = new long[k];
        for (int i = 0; i < k; i++) {
            bags[i] = Long.parseLong(br.readLine());
        }

        solution(jewels, bags);

        printRes();
    }

    private static void solution(long[][] jewels, long[] bags) {
        TreeMap<Long, Long> bagSet = new TreeMap<>();
        for (long bag : bags) {
            if (bagSet.containsKey(bag)) {
                bagSet.put(bag, bagSet.get(bag) + 1);
            } else {
                bagSet.put(bag, 1L);
            }
        }

        Arrays.sort(jewels, (j1, j2) -> (int)(j2[1] -j1[1]));

        long ans = 0;
        for (long[] jewel : jewels) {
            long weight = jewel[0];
            long price = jewel[1];

            Long ceilingKey = bagSet.ceilingKey(weight);
            if (ceilingKey != null) {
                ans += price;

                bagSet.put(ceilingKey, bagSet.get(ceilingKey) - 1);
                if (bagSet.get(ceilingKey) == 0) {
                    bagSet.remove(ceilingKey);
                }
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