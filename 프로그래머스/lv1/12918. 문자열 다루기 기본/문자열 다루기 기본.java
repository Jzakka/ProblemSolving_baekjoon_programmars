class Solution {
    public boolean solution(String s) {
        return s
                .chars()
                .mapToObj(Character::isDigit)
                .reduce(s.length() == 4 || s.length() == 6, (res, isNumeric) -> res && isNumeric);
    }
}