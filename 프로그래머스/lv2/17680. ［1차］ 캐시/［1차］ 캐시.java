import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        TreeMap<Integer, String> LRU = new TreeMap<>();
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();

        int latency = 0;
        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();

            if (map.containsKey(city)) {
                LRU.remove(map.get(city));
                map.put(city, i);
                LRU.put(i, city);
                latency++;
            } else {
                LRU.put(i, city);
                map.put(city, i);
                if (LRU.size() > cacheSize) {
                    Integer lastUsedTime = LRU.firstEntry().getKey();
                    String oldestCity = LRU.firstEntry().getValue();

                    LRU.remove(lastUsedTime);
                    map.remove(oldestCity);
                }
                latency += 5;
            }
        }

        return latency;
    }
}