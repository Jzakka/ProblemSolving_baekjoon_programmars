import java.util.*;

class Solution {
    String[] board;
    Visited visited = new Visited();

    public int solution(String[] board) {
        this.board = board;

        int[] srcDest = findSrcDest();
        int srcX = srcDest[0];
        int srcY = srcDest[1];
        int destX = srcDest[2];
        int destY = srcDest[3];

        return bfs(srcX, srcY, destX, destY);
    }

    private int[] findSrcDest() {
        int[] srcDest = {0, 0, 0, 0};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                char character = board[i].charAt(j);
                if (character == 'R') {
                    srcDest[0] = i;
                    srcDest[1] = j;
                } else if (character == 'G') {
                    srcDest[2] = i;
                    srcDest[3] = j;
                }
            }
        }
        return srcDest;
    }


    private int bfs(int srcX, int srcY, int destX, int destY) {
        visited.visit(srcX, srcY);
        Queue<int[]> Q = new LinkedList<>();
        Q.add(new int[]{srcX, srcY});

        int cnt = 0;
        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                int[] current = Q.poll();
                int curX = current[0];
                int curY = current[1];

                if (curX == destX && curY == destY) {
                    return cnt;
                }



                List<int[]> nexts = nextDest(curX, curY);
                for (int[] next : nexts) {
                    int nextX = next[0];
                    int nextY = next[1];

                    Q.add(new int[]{nextX, nextY});
                }
            }
            cnt++;
        }

        return -1;
    }

    private List<int[]> nextDest(int x, int y) {
        List<int[]> nextDest = new ArrayList<>();

        //east
        int east = y;
        while (east + 1 < board[0].length() && board[x].charAt(east + 1) != 'D') {
            east++;
        }
        if (visited.notVisited(x, east)) {
            visited.visit(x,east);
            nextDest.add(new int[]{x, east});
        }

        //west
        int west = y;
        while (west - 1 >= 0 && board[x].charAt(west - 1) != 'D') {
            west--;
        }
        if (visited.notVisited(x, west)) {
            visited.visit(x,west);
            nextDest.add(new int[]{x, west});
        }

        //south
        int south = x;
        while (south + 1 < board.length && board[south+1].charAt(y) != 'D') {
            south++;
        }
        if (visited.notVisited(south, y)) {
            visited.visit(south, y);
            nextDest.add(new int[]{south, y});
        }

        //north
        int north = x;
        while (north - 1 >= 0 && board[north-1].charAt(y) != 'D') {
            north--;
        }
        if (visited.notVisited(north, y)) {
            visited.visit(north, y);
            nextDest.add(new int[]{north, y});
        }

        return nextDest;
    }

    class Visited {
        Set<Integer> visited = new HashSet<>();

        public void visit(int x, int y) {
            visited.add(cantorPairing(x, y));
        }

        public boolean isVisited(int x, int y) {
            return visited.contains(cantorPairing(x, y));
        }

        public boolean notVisited(int x, int y) {
            return !isVisited(x, y);
        }

        private int cantorPairing(int x, int y) {
            return (x + y) * (x + y + 1) / 2 + y;
        }

    }
}
