import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String[] inputs = br.readLine().split("\\s+");
            int n = Integer.parseInt(inputs[0]);
            int k = Integer.parseInt(inputs[1]);
            int[] building = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[][] buildTree = new int[k][2];
            for (int i = 0; i < k; i++) {
                String[] fromTo = br.readLine().split("\\s+");
                buildTree[i][0] = Integer.parseInt(fromTo[0]);
                buildTree[i][1] = Integer.parseInt(fromTo[1]);
            }
            int w = Integer.parseInt(br.readLine());

            solution(building, buildTree, w);
        }

        printRes();
    }

    static class Building{
        int number, time;

        public Building(int number, int time) {
            this.number = number;
            this.time = time;
        }
    }
    
    public static void solution(int[] building, int[][] buildTree, int w) {
        int n = building.length;
        Building[] buildings = new Building[n +1];
        boolean[] hasMother = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            buildings[i + 1] = new Building(i + 1, building[i]);
        }
        
        List<Building>[] graph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i->graph[i]=new ArrayList<>());

        for (int[] edge : buildTree) {
            int srcNum = edge[0];
            int destNum = edge[1];

            hasMother[destNum] = true;
            graph[srcNum].add(buildings[destNum]);
        }

        List<Integer> starts = new ArrayList<>();
        for (int start = 1; start <= n; start++) {
            if (!hasMother[start]) {
                starts.add(start);
            }
        }

        int[] DP = new int[n + 1];
        for (Integer start : starts) {
            bfs(DP, graph, buildings, start);
        }

        res.append(DP[w]).append("\n");
    }

    private static void bfs(int[] DP, List<Building>[] graph, Building[] buildings, int start) {
        DP[start] = buildings[start].time;
        // bfs
        Queue<Building> Q = new LinkedList<>();
        Q.add(buildings[start]);

        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                Building current = Q.poll();

                for (Building nextBuilding : graph[current.number]) {
                    if (DP[nextBuilding.number] < DP[current.number] + nextBuilding.time) {
                        Q.add(nextBuilding);
                        DP[nextBuilding.number] = DP[current.number] + nextBuilding.time;
                    }
                }
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}