import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1000000;

    static class Building{
        int num, time, parentsCnt;
        List<Integer> nextBuildings = new ArrayList<>();

        public Building(int num) {
            this.num = num;
        }
    }
    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        Building[] buildings = new Building[n + 1];
        IntStream.rangeClosed(0, n).forEach(i -> buildings[i] = new Building(i));
        buildings[0].parentsCnt = -1;

        for (int i = 0; i < n; i++) {
            String[] inputs = br.readLine().split("\\s+");
            String time = inputs[0];

            buildings[i + 1].time = Integer.parseInt(time);
            for (int j = 1; j < inputs.length - 1; j++) {
                int nextBuilding = Integer.parseInt(inputs[j]);
                buildings[nextBuilding].nextBuildings.add(i+1);
                buildings[i+1].parentsCnt++;
            }
        }

        solution(buildings);
        printRes();
    }

    private static void solution(Building[] buildings) {
        int[] DP = new int[buildings.length];
        int[] starts = Arrays.stream(buildings)
                .filter(b -> b.parentsCnt == 0)
                .mapToInt(b -> b.num)
                .toArray();

        for (int start : starts) {
            Queue<Integer> Q = new LinkedList<>();
            DP[start] += buildings[start].time;
            Q.add(start);

            while (!Q.isEmpty()) {
                int qLen = Q.size();
                for (int i = 0; i < qLen; i++) {
                    Integer current = Q.poll();

                    Building curBuilding = buildings[current];
                    for (Integer nextBuildingNum : curBuilding.nextBuildings) {
                        Building nextBuilding = buildings[nextBuildingNum];
                        if (DP[current] + nextBuilding.time > DP[nextBuildingNum]) {
                            Q.add(nextBuildingNum);
                            DP[nextBuildingNum] = DP[current] + nextBuilding.time;
                        }
                    }
                }
            }
        }


        for (int i = 1; i < DP.length; i++) {
            res.append(DP[i]).append("\n");
        }
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}