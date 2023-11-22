import java.util.*;
import java.util.stream.Collectors;

class Solution {
    Map<String, Integer> gemCounts = new HashMap<>();

    public int[] solution(String[] gems) {
        int gemKinds = (int) Arrays.stream(gems).distinct().count();

        int r = -1, l = 0;

        int ansLen = Integer.MAX_VALUE;
        int ansStart = 0;
        int ansEnd = 0;

        while (r < gems.length) {
            if (gemCounts.size() < gemKinds) {
                r++;
                if (r < gems.length) {
                    mapAdd(gems[r]);
                }
            } else {
                if (r - l + 1 < ansLen) {
                    ansLen = r - l + 1;
                    ansStart = l + 1;
                    ansEnd = r + 1;
                }
                mapSub(gems[l++]);
            }
        }

        return new int[]{ansStart, ansEnd};
    }

    void mapAdd(String key) {
        if (!gemCounts.containsKey(key)) {
            gemCounts.put(key, 1);
        } else {
            gemCounts.put(key, gemCounts.get(key) + 1);
        }
    }

    void mapSub(String key) {
        gemCounts.put(key, gemCounts.get(key) - 1);
        if (gemCounts.get(key) == 0) {
            gemCounts.remove(key);
        }
    }
}