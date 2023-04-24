import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        return Arrays
                .stream(commands)
                .mapToInt(arr -> Arrays.stream(Arrays
                        .copyOfRange(array, arr[0] - 1, arr[1]))
                        .sorted()
                        .toArray()[arr[2] - 1])
                .toArray();
    }
}