import java.util.*;

class Solution {
    boolean[][] visited = new boolean[101][101];
    Map<String, String> tileParent = new HashMap<>();
    Map<String, Integer> sum = new HashMap<>();

    int width;
    int height;
    String[] map;

    public int[] solution(String[] maps) {
        width = maps[0].length();
        height = maps.length;
        map = maps;


        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) != 'X' && !tileParent.containsKey(i + "," + j)) {
                    sum.put(i + "," + j, 0);
                    dfs(i + "," + j, i, j);
                }
            }
        }

        if (sum.isEmpty()) {
            return new int[]{-1};
        }
        return sum.values().stream().sorted().mapToInt(Integer::intValue).toArray();
    }

    public void dfs(final String parent, int x, int y) {
        visited[x][y] = true;
        tileParent.put(x + "," + y, parent);
        sum.put(parent, sum.get(parent) + map[x].charAt(y)-'0');

        //e
        if (y + 1 < width && map[x].charAt(y + 1) != 'X' && !visited[x][y + 1]) {
            dfs(parent, x, y + 1);
        }
        //w
        if (y - 1 >= 0 && map[x].charAt(y - 1) != 'X' && !visited[x][y - 1]) {
            dfs(parent, x, y - 1);
        }
        //s
        if (x + 1 < height && map[x + 1].charAt(y) != 'X' && !visited[x + 1][y]) {
            dfs(parent, x + 1, y);
        }
        //n
        if (x - 1 >= 0 && map[x - 1].charAt(y) != 'X' && !visited[x - 1][y]) {
            dfs(parent, x - 1, y);
        }
    }
}