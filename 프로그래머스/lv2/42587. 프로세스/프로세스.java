
import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        ArrayList<Entity> sheets = new ArrayList<>();

        for (int i = 0; i < priorities.length; i++) {
            sheets.add(new Entity(i, priorities[i]));
        }

        int i = 0;
        int order = 0;
        while (true) {
            Entity current = sheets.get(i);
            int j = i + 1;
            boolean prints = true;

            for (; j < sheets.size(); j++) {
                if (current.priority < sheets.get(j).priority) {
                    sheets.addAll(sheets.subList(i, j));
                    i = j;
                    prints = false;
                    break;
                }
            }
            if (prints) {
                order++;
                if (sheets.get(i).name == location) {
                    break;
                }
                i++;
            }
        }

        return order;
    }

    class Entity implements Comparable<Entity> {
        int name;
        int priority;

        public Entity(int name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        @Override
        public int compareTo(Entity o) {
            if (priority == o.priority) {
                return name - o.name;
            }

            return o.priority - priority;
        }
    }
}