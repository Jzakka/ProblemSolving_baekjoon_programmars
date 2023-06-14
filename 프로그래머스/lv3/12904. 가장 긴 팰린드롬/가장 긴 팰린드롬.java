class Solution {
    public int solution(String s) {
        int answer = 0;

        StringBuilder sb = new StringBuilder("#");
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i)).append('#');
        }


        int c = 0;
        int r = 0;
        int[] LPS = new int[sb.length()];
        for (int i = 1; i < sb.length(); i++) {
            if (i < r) {
                LPS[i] = Math.min(r - i, LPS[2 * c - i]);
            }

            while (i + LPS[i] + 1 < sb.length() && i - LPS[i] - 1 >= 0 && sb.charAt(i + LPS[i] + 1) == sb.charAt(i - LPS[i] - 1)) {
                LPS[i]++;
            }

            if (r < LPS[i]) {
                c = i;
                r = i + LPS[i];
            }

            answer = Math.max(LPS[i], answer);
        }

        return answer;
    }
}