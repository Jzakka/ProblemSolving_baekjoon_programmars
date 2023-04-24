import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> wear = new HashMap<>();

        for (String[] clothe : clothes) {
            if (wear.containsKey(clothe[1])) {
                wear.put(clothe[1], wear.get(clothe[1]) + 1);
            } else {
                wear.put(clothe[1], 2);
            }
        }

        return wear.values().stream().reduce(1, (prd, val) -> prd *= val) - 1;
    }
}