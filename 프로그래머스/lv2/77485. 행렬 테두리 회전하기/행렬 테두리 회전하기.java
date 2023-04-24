import java.util.*;

class Solution {
    private int[][] matrix;

    public int[] solution(int rows, int columns, int[][] queries) {
        build(rows, columns);
        List<Integer> minValues = new ArrayList<>();

        for (int[] query : queries) {
            minValues.add(rotateAndGetMin(query));
        }

        return minValues.stream().mapToInt(Integer::valueOf).toArray();
    }

    private void build(int rows, int columns) {
        matrix = new int[rows][columns];

        int cell = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++, cell++) {
                matrix[i][j] = cell;
            }
        }
    }

    private Integer rotateAndGetMin(int[] query) {
        int x1 = query[0]-1;
        int y1 = query[1]-1;
        int x2 = query[2]-1;
        int y2 = query[3]-1;

        int temp = matrix[x1][y1];
        int minVal = temp;
        //V
        for (int i = x1; i < x2; i++) {
            matrix[i][y1] = matrix[i + 1][y1];
            minVal = Math.min(minVal, matrix[i][y1]);
        }
        //->
        for (int i = y1; i < y2; i++) {
            matrix[x2][i] = matrix[x2][i + 1];
            minVal = Math.min(minVal, matrix[x2][i]);
        }
        //^
        for (int i = x2; i > x1; i--) {
            matrix[i][y2] = matrix[i - 1][y2];
            minVal = Math.min(minVal, matrix[i][y2]);
        }
        //<-
        for (int i = y2; i > y1+1; i--) {
            matrix[x1][i] = matrix[x1][i - 1];
            minVal = Math.min(minVal, matrix[x1][i]);
        }
        matrix[x1][y1 + 1] = temp;

        return minVal;
    }
}