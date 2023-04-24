import java.util.stream.IntStream;

class Solution {
    public int solution(int number, int limit, int power) {
        return IntStream.range(1, number + 1).reduce(0, (sum, num) -> {
            int cnt = count(num);
            sum += cnt > limit ? power : cnt;
            return sum;
        });
    }

    int count(int num) {
        int i = 1;
        int cnt = 0;
        for (; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                cnt += 2;
            }
        }

        if (i * i == num) {
            cnt++;
        }
        return cnt;
    }
}