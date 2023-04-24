import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long q1Sum = Arrays.stream(queue1).sum();
        long q2Sum = Arrays.stream(queue2).sum();
        long initQ1Sum = q1Sum;

        Queue<Integer> Q1 = new LinkedList<>();
        Queue<Integer> Q2 = new LinkedList<>();
        Arrays.stream(queue1).forEach(Q1::offer);
        Arrays.stream(queue2).forEach(Q2::offer);

        int cnt = 0;
        while (q1Sum != q2Sum && cnt < 5*(queue1.length + queue2.length)) {
            if (q1Sum > q2Sum) {
                Integer val = Q1.poll();
                Q2.offer(val);
                q1Sum -= val;
                q2Sum += val;
            } else {
                Integer val = Q2.poll();
                Q1.offer(val);
                q2Sum -= val;
                q1Sum += val;
            }

            cnt++;
        }
        return cnt == 5*(queue1.length + queue2.length) ? -1 : cnt;
    }
}