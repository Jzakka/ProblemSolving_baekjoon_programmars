import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] bricks = new int[n][];

        for (int i = 0; i < n; i++) {
            bricks[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(bricks);

        printRes();
    }


    private static void solution(int[][] bricks) {
        int[][] newBricks = new int[bricks.length][];
        for (int i = 0; i < bricks.length; i++) {
            newBricks[i] = new int[]{bricks[i][0], bricks[i][1], bricks[i][2], i + 1};
        }
        bricks = newBricks;

        Arrays.sort(bricks,(brick1, brick2) -> brick2[0] - brick1[0]);

        int weights = Arrays.stream(bricks).mapToInt(brick -> brick[2]).sum();
        int[] DP = new int[weights + 1];
        int[] top = new int[weights + 1];
        int[] trace = new int[weights + 1];
        trace[0] = -1;

        for (int[] brick : bricks) {
            int height = brick[1];
            int weight = brick[2];
            int id = brick[3];

            int highestWeight = 0;
            int maxHeight = 0;
            for (int i = weight; i < DP.length; i++) {
                if (DP[i] > maxHeight) {
                    maxHeight = DP[i];
                    highestWeight = i;
                }
            }

            DP[weight] = maxHeight + height;
            trace[id] = top[highestWeight];
            top[weight] = id;
        }

        int maxHeight = 0;
        int lastId = 0;
        for (int i = 0; i < DP.length; i++) {
            if (DP[i] > maxHeight) {
                maxHeight = DP[i];
                lastId = top[i];
            }
        }

        List<Integer> tower = new ArrayList<>();
        while (lastId != 0) {
            tower.add(lastId);
            lastId = trace[lastId];
        }

        res.append(tower.size()).append("\n");
        for (Integer peak : tower) {
            res.append(peak).append("\n");
        }

    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*

벽돌을 올려놓으려면 아랫벽돌이 윗 벽돌보다 넓거나 같고 윗벽돌이 아랫벽돌보다 가벼워야 함

최대의 높이

25 3 4
16 2 5
9 2 3
4 4 6
1 5 2

DP[i][j] : 꼭대기의 밑면이 i이고 무게가 j일 떄 최대 높이

DP[i][j]

 */