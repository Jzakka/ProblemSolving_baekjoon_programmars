import java.util.*;

class Solution {
    private int[] pos = {0, 0};
    private int[] dest;
    private int[][] map;
    private int[][] minPath;

    public int solution(int[][] maps) {
        map = maps;
        dest = new int[]{maps.length - 1, maps[0].length - 1};
        minPath = new int[maps.length][maps[0].length];
        Arrays.stream(minPath).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));

        return bfs();
    }

    private int bfs() {
        Queue<int[]> Q = new LinkedList<>();

        int cnt = 1;
        Q.offer(pos);

        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                int[] current = Q.poll();

                if (current[1] == dest[1] && current[0] == dest[0]) {
                    return cnt;
                }

                minPath[current[0]][current[1]] = cnt;
                ArrayList<int[]> nextList = availableNext(current);
                nextList.forEach(Q::offer);
            }
            cnt++;
        }
        return -1;
    }

    private ArrayList<int[]> availableNext(int[] current) {
        int curX = current[0];
        int curY = current[1];
        ArrayList<int[]> next = new ArrayList<>();
        // east
        if (curY + 1 < map[0].length && map[curX][curY + 1] == 1 && minPath[curX][curY] + 1 < minPath[curX][curY + 1]) {
            minPath[curX][curY + 1] = minPath[curX][curY] + 1;
            next.add(new int[]{curX, curY + 1});
        }
        //west
        if (curY - 1 >= 0 && map[curX][curY - 1] == 1 && minPath[curX][curY] + 1 < minPath[curX][curY - 1]) {
            minPath[curX][curY - 1] = minPath[curX][curY] + 1;
            next.add(new int[]{curX, curY - 1});
        }
        //south
        if (curX + 1 < map.length && map[curX + 1][curY] == 1 && minPath[curX][curY] + 1 < minPath[curX + 1][curY]) {
            minPath[curX + 1][curY] = minPath[curX][curY] + 1;
            next.add(new int[]{curX + 1, curY});
        }
        //north
        if (curX - 1 >= 0 && map[curX - 1][curY] == 1 && minPath[curX][curY] + 1 < minPath[curX - 1][curY]) {
            minPath[curX - 1][curY] = minPath[curX][curY] + 1;
            next.add(new int[]{curX - 1, curY});
        }
        return next;
    }
}