import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final Map<Integer, Character> dir = new HashMap<>();

    static {
        dir.put(0, 'U');
        dir.put(1, 'L');
        dir.put(2, 'R');
        dir.put(3, 'D');
    }

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int R = info[0];
        int C = info[1];

        int[][] rollerCoaster = new int[R][];

        for (int i = 0; i < R; i++) {
            rollerCoaster[i] = getInts();
        }

        solution(rollerCoaster, R, C);

        printRes();
    }

    private static void solution(int[][] rollerCoaster, final int R, final int C) {
        if ((R & 1) == 0 && (C & 1) == 0) {
            avoidMinimumBlock(rollerCoaster, R, C);
        } else if ((R & 1) == 0) {
            for (int r = 0; r < C - 1; r++) {
                for (int du = 0; du < R - 1; du++) {
                    res.append(((r & 1) == 0) ? 'D' : 'U');
                }
                res.append('R');
            }
            for (int d = 0; d < R - 1; d++) {
                res.append('D');
            }
        } else {
            for (int d = 0; d < R - 1; d++) {
                for (int rl = 0; rl < C - 1; rl++) {
                    res.append(((d & 1) == 0) ? 'R' : 'L');
                }
                res.append('D');
            }
            for (int r = 0; r < C - 1; r++) {
                res.append('R');
            }
        }
    }

    private static void avoidMinimumBlock(int[][] rollerCoaster, int R, int C) {
        int[] minPos = getMinPos(rollerCoaster, R, C);

        final int RIGHT = 0, DOWN = 0;
        final int LEFT = 1, UP = 1;

        int avoidX = minPos[0];
        int avoidY = minPos[1];


        if ((avoidY & 1) == 1) {
            horizonSweep(avoidX, C, RIGHT);
            if (avoidX > 0) {
                res.append('D');
            }
            verticalSweep(avoidX, 0, avoidX, avoidY, avoidX + 1, C - 1);
            if (avoidX < R - 2) {
                res.append('D');
            }
            horizonSweep(R - avoidX - 2, C, LEFT);
        } else {
            horizonSweep(avoidX - 1, C, RIGHT);
            if (avoidX - 1 > 0) {
                res.append('D');
            }
            verticalSweep(avoidX - 1, 0, avoidX, avoidY, avoidX, C - 1);
            if (avoidX < R - 1) {
                res.append('D');
            }
            horizonSweep(R - avoidX - 1, C, LEFT);
        }
    }

    private static void horizonSweep(int rows, int C, int initDir) {
        final int RIGHT = 0;
        final int LEFT = 1;

        int dir = initDir;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < C - 1; j++) {
                if (dir == RIGHT) {
                    res.append('R');
                } else {
                    res.append('L');
                }
            }
            if (i < rows - 1) {
                res.append('D');
            }
            dir ^= 1;
        }
    }

    private static void verticalSweep(int curX, int curY, int avoidX, int avoidY, int destX, int destY) {
        final int DOWN = 0;
        final int UP = 1;
        int dir = DOWN;

        while (!(curX == destX && curY == destY)) {
            if (dir == DOWN) {
                if (curX + 1 == avoidX && curY == avoidY) {
                    curY++;
                    res.append('R');
                } else {
                    curX++;
                    res.append('D');
                    if (!(curX == destX && curY == destY)) {
                        curY++;
                        res.append('R');
                        dir ^= 1;
                    }
                }
            } else {
                if (curX - 1 == avoidX && curY == avoidY) {
                    curY++;
                    res.append('R');
                } else {
                    curX--;
                    res.append('U');
                    curY++;
                    res.append('R');
                    dir ^= 1;
                }
            }
        }
    }

    private static int[] getMinPos(int[][] rollerCoaster, int R, int C) {
        int minVal = Integer.MAX_VALUE;
        int[] pos = {0, 0};

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if ((((i & 1) ^ (j & 1)) == 1) && minVal > rollerCoaster[i][j]) {
                    minVal = rollerCoaster[i][j];
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }

        return pos;
    }

    private static boolean available(int x, int y, int R, int C) {
        return 0 <= x && x < R && 0 <= y && y < C;
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
2 4
2 2 1 2
2 1 1 2
 */