import java.util.*;

class Solution {
    Map<Character, Integer> order = new HashMap<>();

    public int solution(String skill, String[] skill_trees) {
        for (int i = 0; i < skill.length(); i++) {
            order.put(skill.charAt(i), i);
        }


        return (int) Arrays.stream(skill_trees).filter(this::inOrder).count();
    }

    private boolean inOrder(String skillTree){
        int prevIdx = -1;
        for (int i = 0; i < skillTree.length(); i++) {
            char skill = skillTree.charAt(i);
            if (order.containsKey(skill)) {
                if (prevIdx + 1 != order.get(skill)) {
                    return false;
                }
                prevIdx++;
            }
        }
        return true;
    }
}