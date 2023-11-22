class Solution {
    final int N = 5;

    public int[] solution(String[][] places) {
        int i = 0;
        int[] res = {0, 0, 0, 0, 0};
        for (String[] place : places) {
            if (good(place)) {
                res[i] = 1;
            }
            i++;
        }
        return res;
    }

    private boolean good(String[] place) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (place[i].charAt(j) == 'P' && notGood(place, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean notGood(String[] place, int x, int y) {
        if (available(x + 1, y - 1) && place[x + 1].charAt(y - 1) == 'P' && (place[x].charAt(y - 1) != 'X' || place[x + 1].charAt(y) != 'X')) {
            return true;
        }
        if (available(x + 1, y) && place[x + 1].charAt(y) == 'P') {
            return true;
        }
        if (available(x, y + 1) && place[x].charAt(y + 1) == 'P') {
            return true;
        }
        if (available(x + 2, y) && place[x + 2].charAt(y) == 'P' && place[x + 1].charAt(y) != 'X') {
            return true;
        }
        if (available(x + 1, y + 1) && place[x + 1].charAt(y + 1) == 'P' && (place[x].charAt(y + 1) != 'X' || place[x + 1].charAt(y) != 'X')) {
            return true;
        }
        if (available(x, y + 2) && place[x].charAt(y + 2) == 'P' && place[x].charAt(y + 1) != 'X') {
            return true;
        }

        return false;
    }

    boolean available(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}