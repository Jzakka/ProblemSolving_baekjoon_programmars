class Solution {
    public String solution(String X, String Y) {
        int[] xCnt = new int[10];
        int[] yCnt = new int[10];

        for (int i = 0; i < X.length(); i++) {
            int digit = X.charAt(i) - '0';
            xCnt[digit]++;
        }
        for (int i = 0; i < Y.length(); i++) {
            int digit = Y.charAt(i) - '0';
            yCnt[digit]++;
        }

        for (int i = 0; i < 10; i++) {
            xCnt[i] = Math.min(xCnt[i], yCnt[i]);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (i == 0 && sb.toString().equals("") && xCnt[i] > 0) {
                return "0";
            }
            for (int j = 0; j < xCnt[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString().equals("") ? "-1" : sb.toString();
    }
}