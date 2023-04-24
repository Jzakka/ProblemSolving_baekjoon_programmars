class Solution {
    public int[] solution(String[][] places) {
        int[] res = new int[places.length];
        int i = 0;

        for (String[] waiting : places) {
            if (follow(waiting)) {
                res[i++] = 1;
            } else {
                res[i++] = 0;
            }
        }

        return res;
    }

    public boolean follow(String[] waiting) {
        for (int i = 0; i < waiting.length; i++) {
            for (int j = 0; j < waiting[0].length(); j++) {
                if (waiting[i].charAt(j) == 'P') {
                    //e
                    if (j + 1 < waiting[0].length() && waiting[i].charAt(j + 1) == 'P') {
                        return false;
                    }
                    //ee
                    if (j + 2 < waiting[0].length() && waiting[i].charAt(j + 2) == 'P' && waiting[i].charAt(j + 1) == 'O') {
                        return false;
                    }
                    //se
                    if (j + 1 < waiting[0].length() && i + 1 < waiting.length && waiting[i + 1].charAt(j + 1) == 'P'
                            && !(waiting[i + 1].charAt(j) == 'X' && waiting[i].charAt(j + 1) == 'X')) {
                        return false;
                    }
                    //s
                    if (i + 1 < waiting.length && waiting[i + 1].charAt(j) == 'P') {
                        return false;
                    }
                    //ss
                    if (i + 2 < waiting.length && waiting[i + 2].charAt(j) == 'P' && waiting[i + 1].charAt(j) == 'O') {
                        return false;
                    }
                    //sw
                    if (j > 0 && i + 1 < waiting.length && waiting[i + 1].charAt(j - 1) == 'P'
                            && !(waiting[i + 1].charAt(j) == 'X' && waiting[i].charAt(j - 1) == 'X')) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}