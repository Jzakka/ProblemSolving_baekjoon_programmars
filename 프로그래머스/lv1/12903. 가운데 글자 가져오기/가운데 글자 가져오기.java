class Solution {
    public String solution(String s) {
        int strLen = s.length();
        return (strLen & 1) == 0 ? s.substring(strLen / 2-1, strLen / 2 + 1) : s.substring(strLen / 2, strLen / 2 + 1);
    }
}