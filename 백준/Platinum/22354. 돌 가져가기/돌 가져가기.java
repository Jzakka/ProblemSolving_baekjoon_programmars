import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        String colors = br.readLine();
        long[] weights = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int i = 0;
        while (st.hasMoreTokens()) {
            weights[i++] = Integer.parseInt(st.nextToken());
        }

        solution(colors, weights);

        printRes();
    }

    private static void solution(String colors, long[] weights) {
        int n = colors.length();

        List<Long> maxWeights = new ArrayList<>();

        int compressedLen = 0;
        char prevColor = colors.charAt(0);
        long groupMaxWeight = 0;
        for (int i = 0; i < n; i++) {
            if (prevColor != colors.charAt(i)) {
                maxWeights.add(groupMaxWeight);
                groupMaxWeight = 0;
                compressedLen++;
            }
            prevColor = colors.charAt(i);
            groupMaxWeight = Math.max(groupMaxWeight, weights[i]);
        }
        compressedLen++;

        if (compressedLen < 3) {
            res.append(0);
            return;
        }

        maxWeights.add(groupMaxWeight);

        int selectCounts = (int) Math.ceil((double) (compressedLen - 2) / 2);

        List<Long> inner = maxWeights.subList(1, compressedLen - 1);
        inner.sort((w1, w2) -> (int) (w2 - w1));

        long ans = 0;
        for (int i = 0; i < selectCounts; i++) {
            ans += inner.get(i);
        }

        res.append(ans);
    }

    /*
    8
WBWBWBWB
25 9 4 8 7 9 3 2000
     */

    private static long weight(List<Long> maxWeights, List<Integer> parents, Integer nodeNum) {
        nodeNum = getRealNode(parents, nodeNum);

        return maxWeights.get(nodeNum);
    }

    private static Integer getRealNode(List<Integer> parents, Integer nodeNum) {
        while (nodeNum != parents.get(nodeNum)) {
            nodeNum = parents.get(nodeNum);
        }
        return nodeNum;
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