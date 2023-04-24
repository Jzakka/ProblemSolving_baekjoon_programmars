class Solution {
    public int solution(int n) {
        int exp = 17;

        int digit;
        do {
            digit = (int) (n % (long) Math.pow(3, exp) / Math.pow(3, exp-1));
            exp--;
        } while (digit == 0 && exp > 0);

        StringBuilder sb = new StringBuilder();
        for (int i = exp; i >=0 ; i--) {
            sb.append((int) (n % (long) Math.pow(3, i + 1) / Math.pow(3, i)));
        }
        sb.reverse();
        int res = 0;
        exp = 0;
        for (int i = sb.length() - 1; i >= 0; i--, exp++) {
            res += (sb.charAt(i) - '0') * Math.pow(3, exp);
        }
        return res;
    }
}