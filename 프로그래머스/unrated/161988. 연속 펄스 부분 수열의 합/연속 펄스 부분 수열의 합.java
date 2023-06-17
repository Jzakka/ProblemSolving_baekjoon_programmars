class Solution {
    public long solution(int[] sequence) {
        long[] oddMinus = new long[sequence.length];
        long[] evenMinus = new long[sequence.length];

        for (int i = 0; i < sequence.length; i++) {
            long num = sequence[i];
            if ((i & 1) == 1) { //odd
                oddMinus[i] = -num;
                evenMinus[i] = num;
            } else {            //even
                oddMinus[i] = num;
                evenMinus[i] = -num;
            }
        }

        long[] oddAccSums = accSums(oddMinus);
        long[] minOddAccSums = minAccSums(oddAccSums);

        long[] evenAccSums = accSums(evenMinus);
        long[] minEvenAccSums = minAccSums(evenAccSums);

        long maxSubSum = Long.MIN_VALUE;
        for (int i = 0; i < oddAccSums.length; i++) {
            maxSubSum = Math.max(maxSubSum, oddAccSums[i] - minOddAccSums[i]);
        }

        for (int i = 0; i < evenAccSums.length; i++) {
            maxSubSum = Math.max(maxSubSum, evenAccSums[i] - minEvenAccSums[i]);
        }

        return maxSubSum;
    }

    private long[] minAccSums(long[] accSums) {
        long[] minAccSums = new long[accSums.length];

        long minSum = 0;
        for (int i = 0; i < accSums.length; i++) {
            minSum = Math.min(minSum, accSums[i]);
            minAccSums[i] = minSum;
        }

        return minAccSums;
    }

    private long[] accSums(long[] array) {
        long[] accSums = new long[array.length];

        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            accSums[i] = sum;
        }

        return accSums;
    }
}