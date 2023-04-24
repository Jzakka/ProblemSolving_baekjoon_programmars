import java.util.*;

class Solution {
    Set<Move> visited = new HashSet<>();

    Node[][] grid;

    int row, col;

    Map<Move, Integer> cycles = new HashMap<>();

    public int[] solution(String[] grid) {
        setUp(grid);

        // 윗면
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Node dest = this.grid[i][j];
                cycleLength(dest, 'D');
                cycleLength(dest, 'U');
                cycleLength(dest, 'L');
                cycleLength(dest, 'R');
            }
        }

        return cycles.values().stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    private void cycleLength(Node dest, char from) {
        Move parent = new Move( dest, from);

        if (!visited.contains(parent)) {
            visited.add(parent);

            int cycleLen = dfs(parent, dest, from);
            if (cycleLen != -1) {
                cycles.put(parent, cycleLen);
            }
        }
    }

    private int dfs(final Move parent, Node dest, char from) {

        Move movement = dest.nextOf(from);

        int cnt = 0;
        while (!visited.contains(movement)) {
            visited.add(movement);

            from = movement.direction;
            dest = movement.dest;

            movement = dest.nextOf(from);
            cnt++;
        }

        if (movement.equals(parent)) {
            return cnt + 1;
        }
        return -1;
    }


    private void setUp(String[] grid) {
        row = grid.length;
        col = grid[0].length();
        this.grid = new Node[row][col];

        int no = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                this.grid[i][j] = new Node(i, j, grid[i].charAt(j), no++);
            }
        }
    }

    class Node {
        int i;
        int j;
        char direction;
        int num;

        public Node(int i, int j, char direction, int num) {
            this.i = i;
            this.j = j;
            this.direction = direction;
            this.num = num;
        }

        private Move nextOf(char from) {
            Move move;
            switch (direction) {
                case 'S':
                    move = straight(from);
                    break;
                case 'L':
                    move = left(from);
                    break;
                default:
                    move = right(from);
            }

            return move;
        }

        private Move right(char from) {
            //src -> dest
            if (from == 'R') {
                return getDown();
            }
            // dest <- src
            if (from == 'L') {
                return getUp();
            }
            // src
            //  v
            // dest
            if (from == 'D') {
                return getLeft();
            }
            // dest
            //  ^
            // src
            if (from == 'U') {
                return getRight();
            }
            return null;
        }

        private Move left(char from) {
            //src -> dest
            if (from == 'R') {
                return getUp();
            }
            // dest <- src
            if (from == 'L') {
                return getDown();
            }
            // src
            //  v
            // dest
            if (from == 'D') {
                return getRight();
            }
            // dest
            //  ^
            // src
            if (from == 'U') {
                return getLeft();
            }
            return null;
        }

        private Move straight(char from) {
            //src -> dest
            if (from == 'R') {
                return getRight();
            }
            // dest <- src
            if (from == 'L') {
                return getLeft();
            }
            // src
            //  v
            // dest
            if (from == 'D') {
                return getDown();
            }
            // dest
            //  ^
            // src
            if (from == 'U') {
                return getUp();
            }
            return null;
        }

        private Move getUp() {
            if (i - 1 < 0) {
                return new Move(grid[row - 1][j], 'U');
            }
            return new Move(grid[i - 1][j], 'U');
        }

        private Move getDown() {
            if (i + 1 == row) {
                return new Move(grid[0][j], 'D');
            }
            return new Move(grid[i + 1][j], 'D');
        }

        private Move getLeft() {
            if (j - 1 < 0) {
                return new Move(grid[i][col - 1], 'L');
            }
            return new Move(grid[i][j - 1], 'L');
        }

        private Move getRight() {
            if (j + 1 == col) {
                return new Move(grid[i][0], 'R');
            }
            return new Move(grid[i][j + 1], 'R');
        }
    }

    class Move {

        Node dest;
        char direction;

        public Move(Node dest, char direction) {
            this.dest = dest;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Move move = (Move) o;
            return direction == move.direction && Objects.equals(dest, move.dest);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dest, direction);
        }
    }
}