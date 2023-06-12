import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(job -> job[0]));

        int responseSum = 0;
        int currentTime = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((job1, job2) -> {
//            int delta1 = job1[1] - job1[0];
//            int delta2 = job2[1] - job2[0];
//
//            if (delta1 == delta2) {
//                return job1[0] - job2[0];
//            }
//            return delta1 - delta2;
            return job1[1] - job2[1];
        });
        int cursor = 0;
        int finished = 0;
        while (finished < jobs.length) {
            while (cursor < jobs.length && jobs[cursor][0] <= currentTime) {
                heap.add(jobs[cursor]);
                cursor++;
            }

            int[] best = heap.poll();
            if (best == null) {
                currentTime = jobs[cursor][0];
            } else {
                currentTime = currentTime + best[1];
                responseSum += currentTime - best[0];
                finished++;
            }
        }

        return responseSum / jobs.length;
    }
}