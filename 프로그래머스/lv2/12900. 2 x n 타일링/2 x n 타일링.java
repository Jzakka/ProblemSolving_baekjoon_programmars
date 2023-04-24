// tlqkf 괜히 복잡하게 생각했네 애미
class Solution {
    public int solution(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int prev = 1;
        int current = 1;

        for (int i = 2; i <= n; i++) {
            int temp = current;
            current += prev;
            current %= 1_000_000_007;
            prev = temp;
        }

        return current;
    }
}