class Solution {
    public int solution(int[] number) {
        int result = 0;
        for (int f = 0; f < number.length; f++) {
            for (int i = f + 1; i < number.length; i++) {
                for (int j = i + 1; j < number.length; j++) {
                    if (number[i] + number[j] + number[f] == 0) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
}