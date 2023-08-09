import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n, m;
        Scanner sc = new Scanner(System.in);
        String nm = sc.nextLine();
        n = Integer.parseInt(nm.split("\\s+")[0]);
        m = Integer.parseInt(nm.split("\\s+")[1]);
        String[] board = new String[n];
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            board[i] = line;
        }

        board = Arrays.stream(Arrays.copyOfRange(board, 1, n - 1))
                .map(line -> line.substring(1, m - 1))
                .toArray(String[]::new);

        solution(n - 2, m - 2, board);
    }

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int[] hole = {-1, -1};

    static void solution(int n, int m, String[] board) {
        int[] blue = {-1, -1};
        int[] red = {-1, -1};
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char cell = board[i].charAt(j);
                if (cell == 'B') {
                    blue[0] = i;
                    blue[1] = j;
                } else if (cell == 'R') {
                    red[0] = i;
                    red[1] = j;
                } else if (cell == 'O') {
                    hole[0] = i;
                    hole[1] = j;
                }
            }
        }

        bfs(board, blue[0], blue[1], red[0], red[1]);
    }

    private static void bfs(String[] board, int blueX, int blueY, int redX, int redY) {
        Queue<List<Integer>> Q = new LinkedList<>();
        Q.add(Arrays.asList(blueX, blueY, redX, redY));
        Set<List<Integer>> visited = new HashSet<>();
        visited.add(Arrays.asList(blueX, blueY, redX, redY));

        int level = 0;
        while (!Q.isEmpty() && level < 10) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                List<Integer> redAndBlue = Q.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int dirX = dx[dir];
                    int dirY = dy[dir];

                    List<Integer> nextRedAndBlue = getNext(board, redAndBlue, dirX, dirY);

                    if (success(nextRedAndBlue)) {
                        System.out.println(level + 1);
                        return;
                    } else if (!visited.contains(nextRedAndBlue)) {
                        visited.add(nextRedAndBlue);
                        Q.add(nextRedAndBlue);
                    }
                }
            }
            level++;
        }
        System.out.println(-1);
    }

    private static List<Integer> getNext(String[] board, List<Integer> redAndBlue, int dirX, int dirY) {
        Integer blueX = redAndBlue.get(0);
        Integer blueY = redAndBlue.get(1);
        Integer redX = redAndBlue.get(2);
        Integer redY = redAndBlue.get(3);

        int[] blue = {blueX, blueY};
        int[] red = {redX, redY};
        if (blueY == redY) {
            if (dirX < 0) { // x좌표가 더 작은 녀석을 먼저 이동시켜야 함
                if (blueX < redX) {
                    move(board, dirX, dirY, blue, red);
                } else {
                    move(board, dirX, dirY, red, blue);
                }
            } else { // x좌표가 더 큰 녀석을 먼저 이동시켜야 함
                if (blueX > redX) {
                    move(board, dirX, dirY, blue, red);
                } else {
                    move(board, dirX, dirY, red, blue);
                }
            }
        } else if (blueX == redX) {
            if (dirY < 0) { // y좌표가 더 작은 녀석을 먼저 이동시켜야 함
                if (blueY < redY) {
                    move(board, dirX, dirY, blue, red);
                } else {
                    move(board, dirX, dirY, red, blue);
                }
            } else { // y좌표가 더 큰 녀석을 먼저 이동시켜야 함
                if (blueY > redY) {
                    move(board, dirX, dirY, blue, red);
                } else {
                    move(board, dirX, dirY, red, blue);
                }
            }
        } else {
            move(board, dirX, dirY, red, blue);
        }
        return Arrays.asList(blue[0], blue[1], red[0], red[1]);
    }

    public static void move(String[] board, int dx, int dy, int[] first, int[] second) {
        while (!(first[0] == hole[0] && first[1] == hole[1]) && available(board, first[0] + dx, first[1] + dy, second[0], second[1])) {
            first[0] += dx;
            first[1] += dy;
        }

        while (!(second[0] == hole[0] && second[1] == hole[1]) && available(board, second[0] + dx, second[1] + dy, first[0], first[1])) {
            second[0] += dx;
            second[1] += dy;
        }
    }

    private static boolean available(String[] board, int x, int y, int otherX, int otherY) {
        return 0 <= x && x < board.length && 0 <= y && y < board[0].length() // 보드내부에 있어야 함
                && (!(x == otherX && y == otherY) || (otherX == hole[0] && otherY == hole[1])) // 다른 구슬과 겹칠 수 없음
                && (board[x].charAt(y) == '.' || board[x].charAt(y) == 'B' || board[x].charAt(y) == 'R' || board[x].charAt(y) == 'O');
    }

    private static boolean success(List<Integer> nextRedAndBlue) {
        Integer blueX = nextRedAndBlue.get(0);
        Integer blueY = nextRedAndBlue.get(1);
        Integer redX = nextRedAndBlue.get(2);
        Integer redY = nextRedAndBlue.get(3);
        return redX == hole[0] && redY == hole[1] && !(blueX == hole[0] && blueY == hole[1]);
    }
}