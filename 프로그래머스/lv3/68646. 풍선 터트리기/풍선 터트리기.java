class Solution {
    public int solution(int[] a) {
        int[] leftMin = getLeftMin(a); // i 까지 a의 최솟값
        int[] rightMin = getRightMin(a); // i 후부터 a의 최솟값

        int ans = 0;
        for (int i = 0; i <a.length; i++) {
            if (leftMin[i] > a[i] || a[i] < rightMin[i]) {
                ans++;
            }
        }

        return ans;
    }

    private int[] getRightMin(int[] a) {
        int[] rightMin = new int[a.length];

        int min = Integer.MAX_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            rightMin[i] = min;
            min = Math.min(min, a[i]);
        }

        return rightMin;
    }

    private int[] getLeftMin(int[] a) {
        int[] leftMin = new int[a.length];

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            leftMin[i] = min;
            min = Math.min(min, a[i]);
        }

        return leftMin;
    }
}