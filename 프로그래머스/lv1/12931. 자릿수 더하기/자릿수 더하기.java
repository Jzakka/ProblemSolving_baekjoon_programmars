class Solution {
    public int solution(int n) {
        return String.valueOf(n).chars().reduce((x, y) -> x + (y - '0')).getAsInt()-'0';
    }
}