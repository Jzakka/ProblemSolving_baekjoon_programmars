import java.util.Arrays;

class Solution {
    int[][] data;

    public int solution(int[][] data, int col, int row_begin, int row_end) {
        this.data = data;

        return hash(col-1, row_begin-1, row_end-1);
    }

    int hash(int col, int row_begin, int row_end) {
        Arrays.sort(data, (int[] t1, int[]t2)->{
            if (t1[col] == t2[col]) {
                return t2[0] - t1[0];
            }
            return t1[col]-t2[col];
        });

        //s_i = data[i].map(prop->prop%i).sum()

        int hashVal = s_i(data[row_end], row_end);
        for (int i = row_begin; i < row_end; i++) {
            hashVal = hashVal ^ s_i(data[i], i);
        }
        return hashVal;
    }

    private int s_i(int[] datum, int i) {
        return Arrays.stream(datum).map(data -> data % (i+1)).sum();
    }
}