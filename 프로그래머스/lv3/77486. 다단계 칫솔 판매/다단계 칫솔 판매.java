
import java.util.*;

class Solution {

    private final String CENTER = "-";
    Map<String, Integer> members = new LinkedHashMap<>();
    Map<String, String> parents = new HashMap<>();

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        for (int i = 0; i < enroll.length; i++) {
            String member = enroll[i];
            String parent = referral[i];
            members.put(member, 0);
            parents.put(member, parent);
        }

        for (int i = 0; i < seller.length; i++) {
            dispenseProfit(seller[i], 100 * amount[i]);
        }


        return members.values().stream().mapToInt(Integer::intValue).toArray();
    }

    private void dispenseProfit(String seller, int profit) {
        if (seller.equals(CENTER) || profit == 0) {
            return;
        }

        int tenPercent = (int) Math.floor(profit * 0.1);
        int remain = profit - tenPercent;
        members.put(seller, members.get(seller) + remain);
        if (parents.containsKey(seller)) {
            dispenseProfit(parents.get(seller), tenPercent);
        }
    }
}