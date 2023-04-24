class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[][] firstAppeared = new int[26][2];
        for (int i = 0; i < 26; i++) {
            firstAppeared[i][1] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < keymap.length; i++) {
            for (char c = 'A'; c <= 'Z'; c++) {
                int index = keymap[i].indexOf(c);
                if (index != -1 && index < firstAppeared[c-'A'][1]) {
                    firstAppeared[c-'A'][0] = i;
                    firstAppeared[c-'A'][1] = index;
                }
            }
        }

        int[] counts = new int[targets.length];

        int t = 0;
        for (String target : targets) {
            int totalInput = 0;
            for (int i = 0; i < target.length(); i++) {
                int[] keyAndCount = firstAppeared[target.charAt(i)-'A'];
                if (totalInput == -1 || keyAndCount[1] == Integer.MAX_VALUE) {
                    totalInput = -1;
                } else {
                    totalInput+= keyAndCount[1]+1;
                }
            }
            counts[t++] += totalInput;
        }

        return counts;
    }
}