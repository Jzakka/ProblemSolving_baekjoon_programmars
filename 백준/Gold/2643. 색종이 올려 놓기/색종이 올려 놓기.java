import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] papers = new int[n][];

        for (int i = 0; i < n; i++) {
            papers[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(papers);

        printRes();
    }

    private static void solution(int[][] papers) {
        Arrays.stream(papers).forEach(paper -> {
            int height = paper[0];
            int width = paper[1];
            if (width < height) {
                paper[0] = width;
                paper[1] = height;
            }
        });

        Arrays.sort(papers, (paper1, paper2) -> {
            if (paper1[0] == paper2[0]) {
                return paper1[1] - paper2[1];
            }
            return paper1[0] - paper2[0];
        });

        int[] widths = Arrays.stream(papers).map(paper -> paper[1]).mapToInt(width -> width).toArray();

        List<Integer> lisLen = new ArrayList<>();

        for (int width : widths) {
            if (!lisLen.isEmpty() && lisLen.get(lisLen.size() - 1) > width) {
                int insertIdx = upperBound(lisLen, width);
                lisLen.set(insertIdx, width);
            } else {
                lisLen.add(width);
            }
        }

        res.append(lisLen.size());
    }

    private static int upperBound(List<Integer> arr, int target) {
        int lo = -1;
        int hi = arr.size();

        while (lo+1 < hi) {
            int mid = (lo + hi) / 2;
            if (arr.get(mid) <= target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}