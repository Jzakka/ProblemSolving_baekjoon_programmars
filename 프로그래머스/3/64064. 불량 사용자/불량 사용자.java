import java.util.*;
import java.util.stream.Collectors;

class Solution {
    Set<Set<Integer>> ans = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        List<Integer> combination = new ArrayList<>();
        combinate(user_id, banned_id, 0, combination);

        return ans.size();
    }

    private void combinate(String[] users, String[] bans, int banIdx, List<Integer> combination) {
        if (banIdx == bans.length) {
            ans.add(new HashSet<>(combination));
            return;
        }

        for (int i = 0; i < users.length; i++) {
            String user = users[i];

            if (!combination.contains(i) && match(user, bans[banIdx])) {
                combination.add(i);
                combinate(users, bans, banIdx + 1, combination);
                combination.remove((Integer) i);
            }
        }
    }

    private boolean match(String user, String ban) {
        int len = user.length();
        if (len != ban.length()) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (ban.charAt(i) == '*') {
                continue;
            }
            if (user.charAt(i) != ban.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}