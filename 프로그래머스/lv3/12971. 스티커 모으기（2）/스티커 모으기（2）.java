class Solution {
    public int solution(int sticker[]) {
        if (sticker.length == 1) {
            return sticker[0];
        }
        if (sticker.length == 2) {
            return Math.max(sticker[0], sticker[1]);
        }
        if (sticker.length == 3) {
            return Math.max(Math.max(sticker[0], sticker[1]), sticker[2]);
        }

        int firstSelected = sticker[0] + maxSum(sticker,2,sticker.length-1);
        int secondSelected = sticker[1] + maxSum(sticker, 3, sticker.length);
        int thirdSelected = maxSum(sticker, 2, sticker.length);

        return Math.max(Math.max(firstSelected, secondSelected), thirdSelected);
    }

    int maxSum(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        if (start == end) {
            return nums[start];
        }

        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);

        for (int i = start + 2; i < end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[end - 1];
    }
}