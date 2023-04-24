import java.util.*;
import java.util.function.Function;

class Solution {
    private String[] park;
    private final int[] robot = {0, 0};
    private int m;
    private int n;

    private final Map<Character, Function<Integer, Boolean>> go = new HashMap<>();
    {
        go.put('E', dist -> {
            for (int i = 0; i < dist; i++) {
                if (++robot[1] == n || park[robot[0]].charAt(robot[1]) == 'X') {
                    return false;
                }
            }
            return true;
        });

        go.put('W', dist -> {
            for (int i = 0; i < dist; i++) {
                if (--robot[1] == -1 || park[robot[0]].charAt(robot[1]) == 'X') {
                    return false;
                }
            }
            return true;
        });

        go.put('S', dist -> {
            for (int i = 0; i < dist; i++) {
                if (++robot[0] == m || park[robot[0]].charAt(robot[1]) == 'X') {
                    return false;
                }
            }
            return true;
        });

        go.put('N', dist -> {
            for (int i = 0; i < dist; i++) {
                if (--robot[0] == -1 || park[robot[0]].charAt(robot[1]) == 'X') {
                    return false;
                }
            }
            return true;
        });
    }

    public int[] solution(String[] park, String[] routes) {
        setRobot(park);

        Arrays.stream(routes).forEach(this::move);

        return robot;
    }

    private void move(String s) {
        int originX = robot[0];
        int originY = robot[1];

        String[] info = s.split(" ");
        Character direction = info[0].charAt(0);
        Integer distance = Integer.parseInt(info[1]);

        Boolean gone = go.get(direction).apply(distance);

        if (!gone) {
            robot[0] = originX;
            robot[1] = originY;
        }
    }

    private void setRobot(String[] park) {
        this.park = park;
        m = park.length;
        n = park[0].length();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char spot = park[i].charAt(j);
                if (spot == 'S') {
                    robot[0] = i;
                    robot[1] = j;
                    return;
                }
            }
        }
    }
}