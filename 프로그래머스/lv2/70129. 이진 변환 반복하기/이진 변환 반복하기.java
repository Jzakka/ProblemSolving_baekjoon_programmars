class Solution {
    public int[] solution(String s) {
        int deletedZero = 0;
        int converted = 0;
        int ones = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones++;
            }
        }
        converted++;
        deletedZero += s.length() - ones;

        while (ones > 1) {
            int[] oneAndZero = countOneBits(ones);
            ones = oneAndZero[0];
            deletedZero += oneAndZero[1];
            converted++;
        }

        return new int[]{converted, deletedZero};
    }

    int[] countOneBits(int number) {
        int oneLen = 0;
        int deletedZero = 0;
        while (number > 0) {
            if ((number & 1) == 0) {
                deletedZero++;
            } else {
                oneLen++;
            }

            number >>= 1;
        }
        return new int[]{oneLen, deletedZero};
    }
}