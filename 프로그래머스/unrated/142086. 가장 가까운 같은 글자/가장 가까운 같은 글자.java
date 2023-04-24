import java.util.Arrays;
class Solution {
    public int[] solution(String s) {
        int[] lastIdx = new int[26];
        Arrays.fill(lastIdx, -1);

        int[] result = new int[s.length()];
        for (int i = 0; i < result.length; i++) {
            char alphabet = s.charAt(i);
            int lastAppearedIdx = lastIdx[alphabet - 'a'];

            result[i] = lastAppearedIdx == -1 ? -1 : i - lastAppearedIdx;
            lastIdx[alphabet - 'a'] = i;
        }
        return result;
    }
}