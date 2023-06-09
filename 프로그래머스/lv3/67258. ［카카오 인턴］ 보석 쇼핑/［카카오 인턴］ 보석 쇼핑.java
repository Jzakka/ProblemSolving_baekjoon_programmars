import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> counts = new HashMap<>();
        Arrays.stream(gems).forEach(gem -> {
            mapAdd(counts, gem);
        });

        Map<String, Integer> current = new HashMap<>();

        int start = 0;
        int end = 0;

        int minLen = Integer.MAX_VALUE;
        int[] res = {0, 0};
        while (end < gems.length) {
            //현재 범위에 보석이 부족함
            while (current.size() < counts.size() && end < gems.length){
                mapAdd(current, gems[end]);
                end++;
            }

            //현재 범위에 모든 보석이 존재하는 동안
            while (current.size() == counts.size()){
                //최소길이 업데이트
                int len = end - start + 1;
                if (len < minLen) {
                    minLen = len;
                    res[0] = start+1;
                    res[1] = end;
                }
                mapSub(current, gems[start]);
                start++;
            }
        }

        return res;
    }

    private void mapSub(Map<String, Integer> map, String key) {
        map.put(key, map.get(key) - 1);

        if (map.get(key) == 0) {
            map.remove(key);
        }
    }

    private void mapAdd(Map<String, Integer> map, String key) {
        if (map.containsKey(key)) {
            map.put(key, map.get(key)+1);
        } else {
            map.put(key, 1);
        }
    }
}