import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Arrays.sort(tangerine);
        Set<Tang> tangs = new TreeSet<>();

        for (int i = 0, cnt = 1; i < tangerine.length; i++, cnt++) {
            if(i == tangerine.length - 1 || tangerine[i] != tangerine[i + 1]) {
                tangs.add(new Tang(tangerine[i], cnt));
                cnt = 0;
            }
        }

        int kinds = 0;
        for (Tang tang : tangs) {
            if (tang.count >= k) {
                return ++kinds;
            } else {
                k -= tang.count;
                kinds++;
            }
        }

        return kinds;
    }

    class Tang implements Comparable<Tang>{
        int num;
        int count;

        public Tang(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Tang o) {
            if (o.count == count) {
                return num - o.num;
            }
            return o.count - count;
        }
    }
}