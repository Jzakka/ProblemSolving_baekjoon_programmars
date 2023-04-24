import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        PriorityQueue<Missile> pq = new PriorityQueue<>();

        for (int[] target : targets) {
            pq.add(new Missile(target[0], target[1]));
        }

        // 새로운 게 들어와서 폭격을 할지 안할지 => 끝이 가장 작은것보다 새로운 놈의 시작점이 크거나 같으면 폭파
        PriorityQueue<Missile> missileGroup = new PriorityQueue<>(Comparator.comparingInt(m -> m.end));

        int cnt = 0;
        while (!pq.isEmpty()) {
            Missile missile = pq.poll();
            if (!missileGroup.isEmpty() && missileGroup.peek().end <= missile.start) {
                missileGroup.clear();
                cnt++;
            }
            missileGroup.add(missile);
        }

        return cnt + 1;
    }

    class Missile implements Comparable<Missile> {
        int start;
        int end;

        public Missile(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Missile o) {
            if (start == o.start) {
                return end - o.end;
            }
            return start-o.start;
        }
    }
}
