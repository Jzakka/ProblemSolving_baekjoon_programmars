import java.util.*;

class Solution {
    int n, m;
    int[] redGoal, blueGoal;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    Set<Integer> walls = new HashSet<>();

    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        redGoal = getTarget(maze, 3);
        blueGoal = getTarget(maze, 4);
        buildWalls(maze);

        int ans = moveCart(maze);

        return ans;
    }

    private void buildWalls(int[][] maze) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 5) {
                    walls.add(hash(i, j));
                }
            }
        }
    }

    private int moveCart(int[][] initState) {
        Queue<State> Q = new LinkedList<>();
        int[] red = getTarget(initState, 1);
        int[] blue = getTarget(initState, 2);
        Set<Integer> redBlocked = new LinkedHashSet<>(walls);
        redBlocked.add(hash(red));
        Set<Integer> blueBlocked = new LinkedHashSet<>(walls);
        blueBlocked.add(hash(blue));

        Q.add(new State(red, blue, redBlocked, blueBlocked));

        int level = 0;
        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                State state = Q.poll();

                List<State> nextStates = getNextStates(state);
                for (State nextState : nextStates) {
                    if (nextState.end) {
                        // state.debugHistory();
                        return level + 1;
                    } else {
                        Q.add(nextState);
                    }
                }
            }
            level++;
        }
        return 0;
    }

    private List<State> getNextStates(State state) {
        int[] red = state.red;
        int[] blue = state.blue;

        List<int[]> nextReds = getNextPos(red, state.redBlocked, redGoal);
        List<int[]> nextBlues = getNextPos(blue, state.blueBlocked, blueGoal);

        List<State> nextStates = new ArrayList<>();
        for (int[] nextRed : nextReds) {
            for (int[] nextBlue : nextBlues) {
                if ((otherPos(nextRed, blue) || otherPos(nextBlue, red)) && otherPos(nextRed, nextBlue)) {
                    if (goal(nextRed, nextBlue)) {
                        nextStates.add(State.endState());
                        return nextStates;
                    }
                    Set<Integer> redBlocked = new LinkedHashSet<>(state.redBlocked);
                    Set<Integer> blueBlocked = new LinkedHashSet<>(state.blueBlocked);
                    if (otherPos(nextRed, redGoal)) {
                        redBlocked.add(hash(nextRed));
                    }
                    if (otherPos(nextBlue, blueGoal)) {
                        blueBlocked.add(hash(nextBlue));
                    }
                    nextStates.add(new State(nextRed, nextBlue, redBlocked, blueBlocked));
                }
            }
        }
        return nextStates;
    }

    private List<int[]> getNextPos(int[] pos, Set<Integer> blocked, int[] goal) {
        List<int[]> nextPos = new ArrayList<>();
        if (samePos(pos, goal)) {
            nextPos.add(pos);
            return nextPos;
        }
        int x = pos[0];
        int y = pos[1];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (available(nx, ny) && !blocked.contains(hash(nx, ny))) {
                nextPos.add(new int[]{nx, ny});
            }
        }
        return nextPos;
    }

    private boolean goal(int[] red, int[] blue) {
        return samePos(red, redGoal) && samePos(blue, blueGoal);
    }

    private boolean otherPos(int[] red, int[] blue) {
        return red[0] != blue[0] || red[1] != blue[1];
    }

    private boolean samePos(int[] pos1, int[] pos2) {
        return !otherPos(pos1, pos2);
    }

    private boolean available(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    private int[] getTarget(int[][] state, int target) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (state[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    private int hash(int x, int y) {
        return (x + y) * (x + y + 1) / 2 + y;
    }

    private int hash(int[] arr) {
        return hash(arr[0], arr[1]);
    }
}

class State {
    int[] red;
    int[] blue;
    Set<Integer> redBlocked;
    Set<Integer> blueBlocked;
    boolean end;

    public State(boolean end) {
        this.end = end;
    }

    public static State endState() {
        return new State(true);
    }

    public State(int[] red, int[] blue, Set<Integer> redBlocked, Set<Integer> blueBlocked) {
        this.red = red;
        this.blue = blue;
        this.redBlocked = redBlocked;
        this.blueBlocked = blueBlocked;
    }

    public void debugHistory() {
        System.out.println("===redblocked===");
        for (Integer integer : redBlocked) {
            int[] original = inverse(integer);
            System.out.printf("x:%dy:%d%n", original[0], original[1]);
        }
        System.out.println("===blueblocked===");
        for (Integer integer : blueBlocked) {
            int[] original = inverse(integer);
            System.out.printf("x:%dy:%d%n", original[0], original[1]);
        }
    }

    public int[] inverse(int z){
        int w = (int) Math.floor((Math.sqrt(8 * (long) z + 1) - 1) / 2);
        int t = (w * (w + 1)) / 2;

        // k2, k1 계산
        int k2 = z - t;
        int k1 = w - k2;


        return new int[]{k1, k2};
    }
}