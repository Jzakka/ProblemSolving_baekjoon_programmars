import java.util.*;
import java.util.stream.Collectors;

class Solution {
    Map<String, Set<String>> availables = new HashMap<>();

    public String[] solution(String[][] tickets) {
        Map<String, Integer> visited = new HashMap<>();
        int ticketCnt = tickets.length;

        for (String[] ticket : tickets) {
            String src = ticket[0];
            String dest = ticket[1];

            String key = src + ":" + dest;
            if (visited.containsKey(key)) {
                visited.put(key, visited.get(key) + 1);
            } else {
                visited.put(key, 1);
            }

            if (availables.containsKey(src)) {
                availables.get(src).add(dest);
            } else {
                availables.put(src, new TreeSet<>(List.of(dest)));

            }

        }

        List<String> res = new ArrayList<>();
        String src = "ICN";
        res.add(src);

        res = travel(visited, res, src, ticketCnt);
        return res.toArray(String[]::new);

    }

    public List<String> travel(Map<String, Integer> visited, List<String> history, String src, int ticketCnt) {
        if (ticketCnt == 0) {
            return history;
        }

        List<String> nexts = getNexts(visited, src);
        for (String next : nexts) {
            String key = src + ":" + next;

            useTicket(visited, key);
            history.add(next);
            List<String> result = travel(visited, history, next, ticketCnt - 1);
            if (result != null) {
                return result;
            }

            restoreTicket(visited, key);
            history.remove(history.size() - 1);
        }

        return null;
    }

    private void restoreTicket(Map<String, Integer> visited, String key) {
        visited.put(key, visited.get(key) + 1);
    }

    private void useTicket(Map<String, Integer> visited, String key) {
        visited.put(key, visited.get(key) - 1);
    }

    private List<String> getNexts(Map<String, Integer> visited, String src) {
        Set<String> nexts = availables.get(src);
        if (nexts == null) {
            return Collections.emptyList();
        }
        return nexts.stream()
                .filter(next -> visited.get(src + ":" + next) != 0)
                .collect(Collectors.toList());
    }
}