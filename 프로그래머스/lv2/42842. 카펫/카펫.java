class Solution {
    public int[] solution(int brown, int yellow) {
        int hPlusW = brown / 2 - 2;
        int h = 1;
        for (;h <= hPlusW; h++) {
            int w = hPlusW - h;
            if (yellow == h * w) {
                return new int[]{w+2, h+2};
            }
        }
        return null;
    }
}