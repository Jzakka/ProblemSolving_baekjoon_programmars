import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = getInts();
        }

        solution(n, matrix);

        printRes();
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Point o) {
            if (x == o.x) {
                return y - o.y;
            }
            return x - o.x;
        }
    }

    static class Shark extends Point{
        int level = 2;
        int exp = 0;
        int moved = 0;
        public Shark(int x, int y) {
            super(x, y);
        }

        public void eat() {
            exp++;
            if (exp == level) {
                level++;
                exp = 0;
            }
        }

        public void moveTo(Fish fish) {
            this.x = fish.x;
            this.y = fish.y;
            this.moved += fish.distance;
        }
    }

    private static void solution(int n, int[][] matrix) {
        Shark shark = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int digit = matrix[i][j];
                if (digit == 9) {
                    shark = new Shark(i, j);
                    matrix[i][j] = 0;
                }
            }
        }

        travel(matrix, shark);

        res.append(shark.moved);
    }

    static class Fish extends Point{
        int distance = 0;

        public Fish(int x, int y, int distance) {
            super(x, y);
            this.distance = distance;
        }
    }

    private static void travel(int[][] matrix, Shark shark) {
        Fish toEat = null;
        // 레이더 돌림
        while ((toEat = search(matrix, shark)) != null) {
            // 이동
            shark.moveTo(toEat);
            shark.eat();
            matrix[shark.x][shark.y] = 0;
        }
    }

    private static Fish search(int[][] matrix, Shark shark) {
        int n = matrix.length;
        boolean[][] visited = new boolean[n][n];
        Queue<Point> Q = new LinkedList<>();
        Q.add(shark);
        visited[shark.x][shark.y] = true;

        Fish toEat = null;
        int dist = 0;
        while (!Q.isEmpty()) {
            int qLen = Q.size();
            if (toEat != null && toEat.distance < dist) {
                break;
            }
            for (int i = 0; i < qLen; i++) {
                Point point = Q.poll();

                if (matrix[point.x][point.y] != 0 && matrix[point.x][point.y] < shark.level) {
                    if (toEat == null || (point.compareTo(toEat) < 0)) {
                        toEat = new Fish(point.x, point.y, dist);
                    }
                }

                for (int d = 0; d < 4; d++) {
                    int nx = point.x + dx[d];
                    int ny = point.y + dy[d];

                    if (inRange(nx, ny, n) && !visited[nx][ny] && matrix[nx][ny] <= shark.level) {
                        visited[nx][ny] = true;
                        Q.add(new Point(nx, ny));
                    }
                }
            }
            dist++;
        }

        return toEat;
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