import java.util.*;

class Solution {
    Map<String, Integer> points = new HashMap<>();

    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        for (int i = 0; i < name.length; i++) {
            points.put(name[i], yearning[i]);
        }

        return Arrays.stream(photo)
                .mapToInt(people -> Arrays.stream(people)
                        .mapToInt(p -> points.getOrDefault(p,0))
                        .sum())
                .toArray();
    }
}