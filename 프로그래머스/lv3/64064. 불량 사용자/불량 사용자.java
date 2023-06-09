import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        List<List<String>> availables = new ArrayList<>();
        String[] banned_regex = Arrays.stream(banned_id).map(id -> id.replace('*', '.')).toArray(String[]::new);
        for (String regex : banned_regex) {
            availables.add(new ArrayList<>());
            for (String id : user_id) {
                if (id.matches(regex)) {
                    availables.get(availables.size() - 1).add(id);
                }
            }
        }

        Set<Set<String>> resultSet = new HashSet<>();
        Set<String> curSet = new HashSet<>();

        recursive(resultSet, availables, curSet, 0);

        return resultSet.size();
    }

    private void recursive(Set<Set<String>> resultSet, List<List<String>> availables, Set<String> curSet, int cur) {
        if (cur == availables.size()) {
            resultSet.add(new HashSet<>(curSet));
            return;
        }

        for (int i = 0; i < availables.get(cur).size(); i++) {
            String id = availables.get(cur).get(i);

            if (!curSet.contains(id)) {
                curSet.add(id);
                recursive(resultSet, availables, curSet, cur + 1);
                curSet.remove(id);
            }
        }
    }
}