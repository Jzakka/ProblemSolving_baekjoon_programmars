import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        List<Integer> winNumList = new ArrayList<>(Arrays.stream(win_nums)
                .boxed()
                .collect(Collectors.toList()));
        int commonNumberCount = 0;
        int invisibleCount = 0;

        int i ;
        i = 0;

        for (; i < lottos.length; i++) {
            Integer selected = lottos[i];
            if (selected == 0) {
                invisibleCount++;
            } else if (winNumList.contains(selected)) {
                winNumList.remove(selected);
                commonNumberCount++;
            }
        }

        return new int[]{rank(commonNumberCount + invisibleCount),rank(commonNumberCount)};
    }

    int rank(int matched){
        switch (matched) {
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 6;
        }
    }
}