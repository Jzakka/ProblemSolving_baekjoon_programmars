import java.util.*;

class Solution {
    char[][] game;
    StringBuilder ans = new StringBuilder();
    Map<Character, List<int[]>> alphabets = new TreeMap<>();

    public String solution(int m, int n, String[] board) {
        game = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char letter = board[i].charAt(j);
                game[i][j] = letter;

                if (Character.isUpperCase(letter)) {
                    if (!alphabets.containsKey(letter)) {
                        alphabets.put(letter, new ArrayList<>());
                    }
                    alphabets.get(letter).add(new int[]{i, j});
                }
            }
        }

        while (delete()) ;

        if (alphabets.isEmpty()) {
            return ans.toString();
        }
        return "IMPOSSIBLE";
    }

    private boolean delete() {
        for (Character letter : alphabets.keySet()) {
            if (deletable(letter)) {
                ans.append(letter);
                List<int[]> pos = alphabets.get(letter);

                game[pos.get(0)[0]][pos.get(0)[1]] = '.';
                game[pos.get(1)[0]][pos.get(1)[1]] = '.';

                alphabets.remove(letter);
                return true;
            }
        }
        return false;
    }

    private boolean deletable(Character letter) {
        List<int[]> pos = alphabets.get(letter);
        int x1 = pos.get(0)[0];
        int y1 = pos.get(0)[1];
        int x2 = pos.get(1)[0];
        int y2 = pos.get(1)[1];

        return (availableRow(x1, Math.min(y1 + 1, y2 + 1), Math.max(y1 + 1, y2 + 1), letter)
                && availableCol(Math.min(x1, x2), Math.max(x1, x2), y2, letter))
                
                || (availableCol(Math.min(x1 + 1, x2 + 1), Math.max(x1 + 1, x2 + 1), y1, letter)
                && availableRow(x2, Math.min(y1, y2), Math.max(y1, y2), letter));
    }

    private boolean availableCol(int x1, int x2, int fixedY, Character letter) {
        for (int x = x1; x < x2; x++) {
            if (game[x][fixedY] != '.' && game[x][fixedY] != letter) {
                return false;
            }
        }
        return true;
    }

    private boolean availableRow(int fixedX, int y1, int y2, Character letter) {
        for (int y = y1; y < y2; y++) {
            if (game[fixedX][y] != '.' && game[fixedX][y] != letter) {
                return false;
            }
        }
        return true;
    }
}