import java.util.stream.*;

class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        return IntStream
                .range(0, arr1.length)
                .mapToObj(i->IntStream
                        .range(0,arr1[0].length)
                        .map(j -> arr1[i][j] + arr2[i][j])
                        .toArray())
                .toArray(int[][]::new);
    }
}