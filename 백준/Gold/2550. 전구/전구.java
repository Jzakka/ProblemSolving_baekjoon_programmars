import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static String str;

    public static void main(String[] args) throws Exception {
        str = br.readLine();

        int[] switches = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] bulbs = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(switches, bulbs);

        printRes();
    }

    private static void solution(int[] switches, int[] bulbs) {
        int n = switches.length;

        int[] bulbsIdx = new int[n + 1];
        for (int i = 0; i < n; i++) {
            bulbsIdx[bulbs[i]] = i;
        }

        int[] linkedIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int num = switches[i];
            int bulbIdx = bulbsIdx[num];

            linkedIdx[i] = bulbIdx;
        }

        // DP[i] : 원래 원소의 i번째 수가 리스트에서 몇 번째인지
        int[] DP = new int[n];
        // prev[i] : 리스트에서 원래 배열의 i번째 원소의 앞 원소
        int[] prev = new int[n];
        List<Integer> lis = new ArrayList<>();

        int maxIdx = -1;
        for (int i = 0; i < n; i++) {
            if (!lis.isEmpty() && linkedIdx[lis.get(lis.size() - 1)] >= linkedIdx[i]) {
                int insertPos = upperBound(linkedIdx, lis, linkedIdx[i]);
                lis.set(insertPos, i);
                DP[i] = insertPos;
            } else {
                DP[i] = lis.size();
                lis.add(i);
                maxIdx = i;
            }

            if (DP[i] == 0) {
                prev[i] = -1;
            } else {
                prev[i] = lis.get(DP[i] - 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        int cur = maxIdx;
        while (cur != -1) {
            ans.add(switches[cur]);
            cur = prev[cur];
        }

        Collections.sort(ans);
        res.append(ans.size()).append("\n");
        ans.forEach(i -> res.append(i).append(" "));
    }

    private static int upperBound(int[] linkedIdx, List<Integer> lis, int target) {
        int lo = 0;
        int hi = lis.size() - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;

            if (linkedIdx[lis.get(mid)] <=  target) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }

        return lo;
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
