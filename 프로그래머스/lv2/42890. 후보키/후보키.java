import java.util.*;

class Solution {
    int colLen;
    int rowLen;
    String[][] relation;
    Set<Set<Integer>> candidateKeys = new HashSet<>();
    public int solution(String[][] relation) {
        this.relation = relation;
        rowLen = relation.length;
        colLen = relation[0].length;

        return composite(0, new HashSet<Integer>());
    }

    private int composite(int idx, Set<Integer> chosen) {
        for (Set<Integer> candidateKey : candidateKeys) {
            if (chosen.containsAll(candidateKey)) {
                return 0;
            }
        }

        if (idx == colLen) {
            if (keySet(chosen).size() == rowLen) {
                candidateKeys.add(chosen);
                return 1;
            } else {
                return 0;
            }
        }
        HashSet<Integer> copied1 = new LinkedHashSet<>(chosen);
        HashSet<Integer> copied2 = new LinkedHashSet<>(chosen);
        copied2.add(idx);

        return composite(idx + 1, copied1) + composite(idx + 1, copied2);
    }

    private Set<String> keySet(Set<Integer> chosen) {
        Set<String> tuples = new HashSet<>();
        for (String[] tuple : relation) {
            StringBuilder candidateKey = new StringBuilder();
            for (Integer idx : chosen) {
                candidateKey.append(tuple[idx]).append(",");
            }
            tuples.add(candidateKey.toString());
        }
        return tuples;
    }
}