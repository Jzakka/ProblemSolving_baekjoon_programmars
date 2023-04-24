import java.util.*;
import java.util.function.Supplier;

class Solution {
    Set<String> roads = new HashSet<>();
    Map<Character, Supplier<int[]>> futurePos = new HashMap<>();
    int[] pos = {0, 0};

    {
        futurePos.put('U', () -> new int[]{pos[0], pos[1] + 1});
        futurePos.put('D', () -> new int[]{pos[0], pos[1] - 1});
        futurePos.put('R', () -> new int[]{pos[0] + 1, pos[1]});
        futurePos.put('L', () -> new int[]{pos[0] - 1, pos[1]});
    }

    public int solution(String dirs) {
        dirs.chars().forEach(i -> move((char) i));
        return roads.size()/2;
    }

    private void move(char direction) {
        int[] dest = futurePos.get(direction).get();

        if (dest[0] >= -5 && dest[0] <= 5 && dest[1] >= -5 && dest[1] <= 5) {
            String road = new StringBuilder().append(pos[0]).append(pos[1]).append(dest[0]).append(dest[1]).toString();
            String reverse = new StringBuilder().append(dest[0]).append(dest[1]).append(pos[0]).append(pos[1]).toString();
            roads.add(road);
            roads.add(reverse);
            pos = dest;
        }
    }
}