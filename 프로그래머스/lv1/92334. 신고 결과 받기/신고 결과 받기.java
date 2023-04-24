import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> accusation = new HashMap<>();

        //accsation init
        for (String id : id_list) {
            accusation.put(id, 0);
        }

        Map<String, ArrayList<String>> relation = new HashMap<>();
        Set<String> logs = Arrays.stream(report).collect(Collectors.toSet());

        for (String log : logs) {
            String complainant = log.split(" ")[0];
            String defendant = log.split(" ")[1];
            if (!relation.containsKey(defendant)) {
                relation.put(defendant, new ArrayList<>());
            }
            relation.get(defendant).add(complainant);

        }

        for (Map.Entry<String, ArrayList<String>> entry : relation.entrySet()) {
            if (entry.getValue().size() >= k) {
                entry.getValue().forEach(complainant -> accusation.put(complainant, accusation.get(complainant)+1));
            }
        }

        int[] result = new int[id_list.length];
        for (int i = 0; i< id_list.length;i++) {
            String id = id_list[i];
            result[i] = accusation.get(id);
        }
        return result;
    }
}