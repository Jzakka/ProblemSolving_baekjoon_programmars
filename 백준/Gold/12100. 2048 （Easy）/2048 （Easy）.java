import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) {
        int n = Integer.parseInt(sc.nextLine());
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            String line = sc.nextLine();
            String[] numStrings = line.split("\\s+");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(numStrings[j]);
            }
        }

        solution(n,board);
    }

    public static void solution(int n, int[][] board) {
        List<List<Integer>> initBoard = arrayToList(board);

        int maxVal = 0;

        Queue<List<List<Integer>>> Q = new LinkedList<>();
        Q.add(initBoard);

        int level = 0;
        while (!Q.isEmpty() && level <= 5) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                List<List<Integer>> state = Q.poll();
                maxVal = Math.max(maxVal, state.stream().flatMap(Collection::stream).mapToInt(Integer::intValue).max().orElse(0));

                List<List<List<Integer>>> nextState = getNextState(n, state);

                Q.addAll(nextState);
            }
            level++;
        }

        System.out.println(maxVal);
    }

    public static List<List<Integer>> arrayToList(int[][] board) {
        return Arrays.stream(board)
                .map(line -> Arrays.stream(line).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private static List<List<List<Integer>>> getNextState(int n, List<List<Integer>> state) {
        List<List<List<Integer>>> nextState = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int dirX = dx[i];
            int dirY = dy[i];

            List<List<Integer>> moved = move(n, dirX, dirY, state);
            nextState.add(moved);
        }
        return nextState;
    }

    public static List<List<Integer>> move(int n, int dirX, int dirY, List<List<Integer>> state) {
        if (dirX != 0) {
            return moveX(n, dirX, state);
        } else {
            return moveY(n, dirY, state);
        }
    }

    public static List<List<Integer>> moveY(int n, int dirY, List<List<Integer>> state) {
        List<List<Integer>> res = Stream.generate(()->Stream.generate(()->0).limit(n).collect(Collectors.toList())).limit(n)
                .collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            boolean isMerged = false;
            Stack<Integer> row = new Stack<>();
            for (int j = dirY < 0 ? 0 : n - 1; dirY < 0 ? j < n : j >= 0; j += dirY < 0 ? 1 : -1) {
                Integer current = state.get(i).get(j);

                if (!row.isEmpty()
                        && !isMerged
                        && Objects.equals(current, row.peek())) {
                    row.set(row.size() - 1, 2 * current);
                    isMerged = true;
                } else if(current!= 0){
                    row.push(current);
                    isMerged = false;
                }
            }

            int idx = dirY < 0 ? row.size() -1: n-row.size();
            while (!row.isEmpty()) {
                res.get(i).set(idx, row.pop());
                idx += dirY < 0 ? -1 :1;
            }
        }

        return res;
    }

    public static List<List<Integer>> moveX(int n, int dirX, List<List<Integer>> state) {
        List<List<Integer>> res = Stream.generate(()->Stream.generate(()->0).limit(n).collect(Collectors.toList())).limit(n)
                .collect(Collectors.toList());

        for (int i = 0; i < n; i++) {
            boolean isMerged = false;

            Stack<Integer> col = new Stack<>();
            for (int j = dirX < 0 ? 0 : n - 1; dirX < 0 ? j < n : j >= 0; j += dirX < 0 ? 1 : -1) {
                Integer current = state.get(j).get(i);

                if (!col.isEmpty() && !isMerged && Objects.equals(current, col.peek())) {
                    col.set(col.size() - 1, 2 * current);
                    isMerged = true;
                } else if(current!= 0){
                    col.push(current);
                    isMerged = false;
                }
            }

            int idx = dirX < 0 ? col.size() -1: n-col.size();
            while (!col.isEmpty()) {
                res.get(idx).set(i, col.pop());
                idx += dirX < 0 ? -1 :1;
            }
        }

        return res;
    }
}