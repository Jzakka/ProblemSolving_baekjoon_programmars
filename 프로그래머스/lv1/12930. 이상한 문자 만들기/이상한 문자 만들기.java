class Solution {
    public String solution(String s) {
        int i = 0;
        int wordIdx = 0;
        StringBuilder res = new StringBuilder();
        for (; i < s.length(); i++) {
            if (Character.isAlphabetic(s.charAt(i))) {
                if (wordIdx % 2 == 0) {
                    res.append(String.valueOf(s.charAt(i)).toUpperCase());
                } else {
                    res.append(String.valueOf(s.charAt(i)).toLowerCase());
                }
                wordIdx++;
            } else {
                wordIdx = 0;
                res.append(s.charAt(i));
            }
        }
        return res.toString();
    }
}