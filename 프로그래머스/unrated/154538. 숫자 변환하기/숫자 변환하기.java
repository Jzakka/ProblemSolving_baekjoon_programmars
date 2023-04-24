import java.util.*;

class Solution {
    Set<Integer> visited = new HashSet<>();
    public int solution(int x, int y, int n) {
        Queue<Integer> Q = new LinkedList<>();
        Q.offer(x);
        visited.add(x);

        int level = 0;
        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                Integer num = Q.poll();
                if (num == y) {
                    return level;
                }
                if (num*3 <= y && !visited.contains(num*3)) {
                    Q.offer(num * 3);
                    visited.add(num * 3);
                }
                if (num*2 <= y && !visited.contains(num*2)) {
                    Q.offer(num * 2);
                    visited.add(num * 2);
                }
                if (num + n <= y && !visited.contains(num+n)) {
                    Q.offer(num + n);
                    visited.add(num + n);
                }
            }
            level++;
        }
        return -1;
    }
}