class Solution {
    public static int count = 1;

    public int solution(String s) {
        char first = s.charAt(0);

        int sameCount = 1;
        int diffCount = 0;
        int i = 1;
        for (; i < s.length() && sameCount != diffCount; i++) {
            if (first == s.charAt(i)) {
                sameCount++;
            } else {
                diffCount++;
            }
        }

        if (i<s.length() && sameCount == diffCount) {
            count++;
            solution(s.substring(i));
        }

        return count;
    }
}