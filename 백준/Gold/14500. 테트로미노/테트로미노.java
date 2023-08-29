import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static List<Function<Coordinate, Coordinate[]>> shapes = Arrays.asList(
            point -> new Coordinate[]{point, point.moveY(1), point.moveY(2), point.moveY(3)},
            point -> new Coordinate[]{point, point.moveX(1), point.moveX(2), point.moveX(3)},
            point -> new Coordinate[]{point, point.moveX(1), point.moveY(1), point.moveXY(1, 1)},

            point -> new Coordinate[]{point, point.moveX(1), point.moveX(2), point.moveXY(2, 1)},
            point -> new Coordinate[]{point, point.moveY(1), point.moveY(2), point.moveXY(-1, 2)},
            point -> new Coordinate[]{point, point.moveY(1), point.moveXY(1, 1), point.moveXY(2, 1)},
            point -> new Coordinate[]{point, point.moveX(1), point.moveY(1), point.moveY(2)},

            point -> new Coordinate[]{point, point.moveY(1), point.moveXY(-1, 1), point.moveXY(-2, 1)},
            point -> new Coordinate[]{point, point.moveX(1), point.moveXY(1, 1), point.moveXY(1, 2)},
            point -> new Coordinate[]{point, point.moveY(1), point.moveX(1), point.moveX(2)},
            point -> new Coordinate[]{point, point.moveY(1), point.moveY(2), point.moveXY(1, 2)},

            point -> new Coordinate[]{point, point.moveX(1), point.moveXY(1, 1), point.moveXY(2, 1)},
            point -> new Coordinate[]{point, point.moveY(1), point.moveXY(-1, 1), point.moveXY(-1, 2)},
            point -> new Coordinate[]{point, point.moveX(1), point.moveXY(1, -1), point.moveXY(2, -1)},
            point -> new Coordinate[]{point, point.moveY(1), point.moveXY(1, 1), point.moveXY(1, 2)},

            point -> new Coordinate[]{point, point.moveY(1), point.moveY(2), point.moveXY(1, 1)},
            point -> new Coordinate[]{point, point.moveY(1), point.moveXY(-1, 1), point.moveXY(1, 1)},
            point -> new Coordinate[]{point, point.moveX(1), point.moveXY(1, 1), point.moveX(2)},
            point -> new Coordinate[]{point, point.moveY(1), point.moveY(2), point.moveXY(-1, 1)}
    );

    public static void main(String[] args) throws Exception {
        String[] firstInputs = br.readLine().split("\\s+");
        int n = Integer.parseInt(firstInputs[0]);
        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        solution(matrix);
        printRes();
    }

    static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Coordinate moveXY(int dx, int dy) {
            return new Coordinate(x + dx, y + dy);
        }

        Coordinate moveX(int dx) {
            return new Coordinate(x + dx, y);
        }

        Coordinate moveY(int dy) {
            return new Coordinate(x, y + dy);
        }
    }

    private static void solution(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Coordinate current = new Coordinate(i, j);
                for (Function<Coordinate, Coordinate[]> shape : shapes) {
                    Coordinate[] shapePositions = shape.apply(current);
                    maxSum = Math.max(maxSum, sum(matrix, shapePositions));
                }
            }
        }
        res.append(maxSum);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }

    public static int sum(int[][] matrix, Coordinate[] coordinates) {
        Coordinate c1 = coordinates[0];
        Coordinate c2 = coordinates[1];
        Coordinate c3 = coordinates[2];
        Coordinate c4 = coordinates[3];

        int n = matrix.length;
        int m = matrix[0].length;
        if (outOfMatrix(n, m, c1) || outOfMatrix(n, m, c2) || outOfMatrix(n, m, c3) || outOfMatrix(n, m, c4)) {
            return -1;
        }

        return matrix[c1.x][c1.y] + matrix[c2.x][c2.y] + matrix[c3.x][c3.y] + matrix[c4.x][c4.y];
    }

    private static boolean outOfMatrix(int n, int m, Coordinate coordinate) {
        return !(0 <= coordinate.x && coordinate.x < n && 0 <= coordinate.y && coordinate.y < m);
    }
}