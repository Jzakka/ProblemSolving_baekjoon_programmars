class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] |= arr2[i];
        }
        String[] result = new String[arr1.length];
        for (int f = 0; f < arr1.length; f++) {
            int num = arr1[f];
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr1.length; i++, num >>= 1) {
                sb.append((num & 1) == 0 ? ' ' : '#');
            }
            result[f] = sb.reverse().toString();
        }
        return result;
    }
}