class Solution {
    public int solution(int n, int a, int b) {
        int answer = 0;
        a += n - 1;
        b += n - 1;

        while (a != b) {
            a = defeat(a);
            b = defeat(b);
            answer++;
        }

        return answer;
    }

    private int defeat(int num) {
        if ((num & 1) == 1) {
            return (num - 1) / 2;
        }
        return num / 2;
    }
}