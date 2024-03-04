import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][];

        for (int i = 0; i < n; i++) {
            map[i] = getInts();
        }

        solution(map);

        printRes();
    }

    private static void solution(int[][] map) {
        int n = map.length;

        // islands[i] = [n * x + y, ...]
        Set<Integer>[] islands = countIslands(map);
        int m = islands.length;

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            ans = Math.min(ans, dijkstra(i, map, islands[i]));
        }

        res.append(ans - 1);
    }

    private static int dijkstra(int curIsland, int[][] map, Set<Integer> island) {
        int res = Integer.MAX_VALUE;
        int n = map.length;
        int[][] minDist = new int[n][n];
        Arrays.stream(minDist).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        // {x,y,dist}
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));
        PQ.addAll(island.stream().map(numPos -> {
            int x = numPos / n;
            int y = numPos - x * n;
            minDist[x][y] = 0;
            return new int[]{x, y, 0};
        }).collect(Collectors.toList()));

        while (!PQ.isEmpty()) {
            int[] pos = PQ.poll();
            int x = pos[0];
            int y = pos[1];
            int dist = pos[2];

            if (dist > minDist[x][y]) {
                continue;
            }

            minDist[x][y] = dist;

//            System.out.printf("x:%d, y:%d, numPos:%d, dist:%d%n", x, y, map[x][y], dist);

            if (map[x][y] != 0 && map[x][y] != curIsland) {
                res = Math.min(dist, res);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (inRange(nx, ny, map.length) && dist + 1 < minDist[nx][ny]) {
                    minDist[nx][ny] = dist + 1;
                    PQ.add(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return res;
    }

    private static Set<Integer>[] countIslands(int[][] map) {
        int n = map.length;

        Set<Integer>[] islands = IntStream.range(0, n * n / 2 + 1)
                .mapToObj(HashSet::new)
                .toArray(Set[]::new);
        boolean[][] visited = new boolean[n][n];

        int islandNum = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(islandNum++, islands, map, visited, i, j);
                }
            }
        }

        return Arrays.copyOfRange(islands, 0, islandNum);
    }

    private static void dfs(int islandNum, Set<Integer>[] islands, int[][] map, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        map[x][y] = islandNum;
        islands[islandNum].add(map.length * x + y);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (inRange(nx, ny, map.length) && map[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(islandNum, islands, map, visited, nx, ny);
            }
        }
    }

    private static boolean inRange(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
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
8
0 0 0 0 0 0 0 0
0 0 1 1 1 1 1 0
0 0 1 0 0 0 1 0
0 0 1 0 0 0 1 0
0 0 1 0 1 0 1 0
0 0 0 0 1 0 0 0
0 0 0 0 1 0 0 0
0 0 0 0 0 0 0 0
 */
