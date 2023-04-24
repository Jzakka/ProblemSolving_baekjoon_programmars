class Solution {
    public int[] solution(String[] wallpaper) {
        int height = wallpaper.length;
        int width = wallpaper[0].length();

        int l, u, d, r;
        l = u = Integer.MAX_VALUE;
        d = r = Integer.MIN_VALUE;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    l = Math.min(j, l);
                    u = Math.min(i, u);
                    d = Math.max(i, d);
                    r = Math.max(j, r);
                }
            }
        }

        return new int[]{u, l, d+1, r+1};
    }
}