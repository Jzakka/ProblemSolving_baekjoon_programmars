class Solution {
    public boolean solution(int x) {
        return x % String
                .valueOf(x)
                .chars()
                .map(c -> c - '0')
                .reduce((sum, num) -> sum + num)
                .getAsInt() == 0;
    }
}