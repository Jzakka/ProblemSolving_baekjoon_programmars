import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
                            // E   S  W  N
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static int DIRECTION = 0;
    private static final int SNAKE = 1;
    private static final int APPLE = 2;
    public static void main(String[] args) {
        int n, k,l;
        n = Integer.parseInt(sc.nextLine());
        k = Integer.parseInt(sc.nextLine());

        int[][] board = new int[n][n];
        for (int i = 0; i < k; i++) {
            int x, y;
            String[] inputs = sc.nextLine().split("\\s+");
            x = Integer.parseInt(inputs[0])-1;
            y = Integer.parseInt(inputs[1]) - 1;

            board[x][y] = APPLE;
        }
        l = Integer.parseInt(sc.nextLine());
        int[] directionTimes = new int[l];
        char[] directions = new char[l];

        for (int i = 0; i < l; i++) {
            String[] inputs = sc.nextLine().split("\\s+");
            int time = Integer.parseInt(inputs[0]);
            char dir = inputs[1].charAt(0);
            directionTimes[i] = time;
            directions[i] = dir;
        }

        solution(n, board, directionTimes, directions);
    }

    private static void solution(int n, int[][] board, int[] directionTimes, char[] directions) {
        int time = 0;
        Deque<int[]> snake = new ArrayDeque<>(Arrays.asList(new int[]{0, 0}));
        board[0][0] = SNAKE;
        for (int i = 0; i < directions.length; i++) {
            while (time < directionTimes[i]) {
                time++;
                if (!move(board, snake)) {
                    System.out.println(time);
                    return;
                }
            }
            changeDirection(directions[i]);
        }

        while (move(board, snake)) {
            time++;
        }

        System.out.println(time+1);
    }

    private static boolean move(int[][] board, Deque<int[]> snake) {
        int dirX = dx[DIRECTION];
        int dirY = dy[DIRECTION];

        int[] head = snake.peekFirst();

        int nextX = head[0] + dirX;
        int nextY = head[1] + dirY;
        if (available(board, nextX, nextY)) {
            if (board[nextX][nextY] != APPLE) {
                int[] tail = snake.pollLast();
                board[tail[0]][tail[1]] = 0;
            }

            snake.offerFirst(new int[]{nextX, nextY});
            board[nextX][nextY] = SNAKE;
            return true;
        }

        return false;
    }

    private static boolean available(int[][] board, int nextX, int nextY) {
        int n = board.length;

        return 0 <= nextX && nextX < n && 0 <= nextY && nextY < n && board[nextX][nextY] != SNAKE;
    }

    private static void changeDirection(char dir) {
        if (dir == 'L') {
            DIRECTION = (DIRECTION + 3) % 4;
        } else {
            DIRECTION = (DIRECTION + 1) % 4;
        }
    }
}