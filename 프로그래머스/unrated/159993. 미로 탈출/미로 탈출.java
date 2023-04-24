import java.util.*;

class Solution {
    int[] curPos = {0, 0};
    int[] exit = {0, 0};
    int[] lever = {0, 0};
    String[] map;
    boolean[][] visited;
    int time = 0;
    public int solution(String[] maps) {
        map = maps;
        visited = new boolean[maps.length][maps[0].length()];
        construct(maps);

        if (goTo(lever)) {
            curPos = lever;
            for (int i = 0; i < visited.length; i++) {
                for (int j = 0; j < visited[0].length; j++) {
                    visited[i][j] = false;
                }
            }
            if (goTo(exit)) {
                return time;
            }
        }
        return -1;
    }

    private boolean goTo(int[] dest) {
        int destX = dest[0];
        int destY = dest[1];

        Queue<int[]> queue = new LinkedList<>();
        visited[curPos[0]][curPos[1]] = true;
        queue.offer(curPos);

        while (!queue.isEmpty()) {
            int qLen = queue.size();
            for (int i = 0; i < qLen; i++) {
                int[] pos = queue.poll();

                if (pos[0] == destX && pos[1] == destY) {
                    return true;
                }

                List<int[]> nexts = available(pos);

                for (int[] next : nexts) {
                    visited[next[0]][next[1]] = true;
                    queue.offer(next);
                }
            }
            time++;
        }

        return false;
    }

    private List<int[]> available(int[] pos) {
        List<int[]> nexts = new ArrayList<>();
        //up
        if (pos[0] - 1 >= 0 && map[pos[0]-1].charAt(pos[1]) != 'X' && !visited[pos[0]-1][pos[1]]) {
            nexts.add(new int[]{pos[0] - 1, pos[1]});
        }
        //down
        if (pos[0] + 1 < map.length && map[pos[0]+1].charAt(pos[1]) != 'X' && !visited[pos[0]+1][pos[1]]) {
            nexts.add(new int[]{pos[0] + 1, pos[1]});
        }
        //left
        if (pos[1] - 1 >= 0 && map[pos[0]].charAt(pos[1] - 1) != 'X' && !visited[pos[0]][pos[1] - 1]) {
            nexts.add(new int[]{pos[0], pos[1] - 1});
        }
        //right
        if (pos[1] + 1 < map[0].length() && map[pos[0]].charAt(pos[1] + 1) != 'X' && !visited[pos[0]][pos[1] + 1]) {
            nexts.add(new int[]{pos[0], pos[1] + 1});
        }

        return nexts;
    }

    private void construct(String[] maps) {
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                char point = maps[i].charAt(j);
                if (point == 'S') {
                    curPos[0] = i;
                    curPos[1] = j;
                }
                if (point == 'E') {
                    exit[0] = i;
                    exit[1] = j;
                }
                if (point == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                }
            }
        }
    }
}