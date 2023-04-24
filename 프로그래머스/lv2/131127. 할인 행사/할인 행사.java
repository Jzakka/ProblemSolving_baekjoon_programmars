import java.util.*;

class Solution {
    Map<String, Integer> needs = new HashMap<>();
    public int solution(String[] want, int[] number, String[] discount) {

        for (int i = 0; i < number.length; i++) {
            needs.put(want[i], number[i]);
        }

        for (int i = 0; i < 9; i++) {
            if (needs.containsKey(discount[i])) {
                needs.put(discount[i], needs.get(discount[i]) - 1);
            }
        }

        int result = 0;
        for (int i = 9; i < discount.length; i++) {
            if (needs.containsKey(discount[i])) {
                needs.put(discount[i], needs.get(discount[i]) - 1);
            }
            if (allBuyable()) {
                result++;
            }
            if (needs.containsKey(discount[i - 9])) {
                needs.put(discount[i-9], needs.get(discount[i-9]) + 1);
            }
        }

        return result;
    }

    boolean allBuyable() {
        for (Map.Entry<String, Integer> need : needs.entrySet()) {
            if (need.getValue() > 0) {
                return false;
            }
        }
        return true;
    }

    //"banana", "apple", "rice", "pork", "pot"
    //1,         -1,          0,      0,      1
    // "chicken",|| "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice"  "pot", ||  "banana", "apple", "banana"
}