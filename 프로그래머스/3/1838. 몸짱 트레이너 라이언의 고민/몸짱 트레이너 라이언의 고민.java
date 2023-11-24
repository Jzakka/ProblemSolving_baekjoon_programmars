import java.util.*;

class Solution {
    int n;

    public int solution(int n, int m, int[][] timetable) {
        this.n = n;

        Arrays.sort(timetable, (t1, t2) -> {
            if (t1[0] == t2[0]) {
                return t1[1] - t2[1];
            }
            return t1[0] - t2[0];
        });

        int maxPerson = getMaxPerson(timetable);

        int totalLockers = n * n;
        if (maxPerson == 1) {
            return 0;
        }
        if (maxPerson == (totalLockers + 1) / 2) {
            return 2;
        }
        if (maxPerson > (totalLockers + 1) / 2) {
            return 1;
        }

        int lo = 1;
        int hi = (n - 1) * 2;
        for (int i = hi; i >= lo; i--) {
            if (check(i, maxPerson)) {
                return i;
            }
        }
        return -1;
    }

    private boolean check(int distance, int maxPerson) {
        int maxAllocated = 0;
        for (int k = 0; k < n; k++) {
            List<int[]> allocated = new ArrayList<>();
            allocated.add(new int[]{0, k});

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (allocatable(distance, allocated, i, j)) {
                        allocated.add(new int[]{i, j});
                    }
                }
            }
            maxAllocated = Math.max(maxAllocated, allocated.size());
        }

        return maxAllocated >= maxPerson;
    }

    private boolean allocatable(int distance, List<int[]> allocated, int x, int y) {
        for (int[] locker : allocated) {
            if (getDistance(locker[0], locker[1], x, y) < distance) {
                return false;
            }
        }
        return true;
    }

    private int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private int getMaxPerson(int[][] timetable) {
        PriorityQueue<int[]> PQ = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        int person = 0;

        for (int i = 0; i < timetable.length; i++) {
            int[] time = timetable[i];

            while (!PQ.isEmpty() && PQ.peek()[1] < time[0]) {
                PQ.poll();
            }
            PQ.add(time);
            person = Math.max(person, PQ.size());
        }

        return person;
    }
}