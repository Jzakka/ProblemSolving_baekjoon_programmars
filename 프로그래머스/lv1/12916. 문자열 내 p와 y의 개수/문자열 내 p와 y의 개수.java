class Solution {
    boolean solution(String s) {
        return s.chars().reduce(0, (sum, character)->{
            if (character == 'p' || character == 'P') {
                sum++;
            } else if (character == 'y' || character == 'Y') {
                sum--;
            }
            return sum;
        }) == 0;
    }
}