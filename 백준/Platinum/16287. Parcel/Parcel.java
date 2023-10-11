import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int w = info[0];
        int[] parcels = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        solution(w, parcels);

        printRes();
    }

    private static void solution(int w, int[] parcels) {
        Map<Integer, int[]> twoSums = new HashMap<>();

        for (int i = 0; i < parcels.length; i++) {
            int first = parcels[i];
            for (int j = i + 1; j < parcels.length; j++) {
                int second = parcels[j];

                if (!twoSums.containsKey(first + second)) {
                    twoSums.put(first + second, new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < parcels.length; i++) {
            int third = parcels[i];
            for (int j = i + 1; j < parcels.length; j++) {
                int fourth = parcels[j];

                if (twoSums.containsKey(w - (third + fourth))) {
                    int[] twoIndicies = twoSums.get(w - (third + fourth));
                    int firstIdx = twoIndicies[0];
                    int secondIdx = twoIndicies[1];

                    if (firstIdx != i && firstIdx != j && secondIdx != i && secondIdx != j) {
                        res.append("YES");
                        return;
                    }
                }
            }
        }

        res.append("NO");
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}