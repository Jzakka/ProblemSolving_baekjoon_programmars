import java.util.*;

class Solution {
    Set<String> cardSet1 = new HashSet<>();
    Set<String> cardSet2 = new HashSet<>();

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        for (String s : cards1) {
            cardSet1.add(s);
        }
        for (String s : cards2) {
            cardSet2.add(s);
        }

        if (!isOriginalOrder(cards1, cardSet1, goal) ||
                !isOriginalOrder(cards2, cardSet2, goal)) {
            return "No";
        }
        return "Yes";
    }

    private boolean isOriginalOrder(String[] card, Set<String> cardSet, String[] goals) {
        String[] filterd = Arrays.
                stream(goals)
                .filter(goal -> cardSet.contains(goal))
                .toArray(String[]::new);

        if (filterd.length > card.length) {
            return false;
        }
        for (int i = 0; i < filterd.length; i++) {
            if (!filterd[i].equals(card[i])) {
                return false;
            }
        }
        return true;
    }
}