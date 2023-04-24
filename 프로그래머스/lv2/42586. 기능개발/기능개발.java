import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> feats = new ArrayList<>();

        int criterion = 0;
        while (criterion < progresses.length) {
            int count = 0;

            int remainDays = (int) Math.ceil((double) (100 - progresses[criterion]) / speeds[criterion]);

            for (int i = 0; i < progresses.length; i++) {
                if (progresses[i] >= 100) {
                    continue;
                }
                progresses[i] += remainDays * speeds[i];
            }
            while (criterion < progresses.length && progresses[criterion] >= 100) {
                criterion++;
                count++;
            }

            feats.add(count);
        }
        return feats.stream().mapToInt(Integer::intValue).toArray();
    }
}