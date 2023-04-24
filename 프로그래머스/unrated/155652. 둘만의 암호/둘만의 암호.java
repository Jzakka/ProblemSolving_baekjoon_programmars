import java.util.stream.Collectors;

class Solution {
    public String solution(String s, String skip, int index) {
        return s.chars()
                .mapToObj(c -> transform(c, skip, index))
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private char transform(int c, String skip, int index) {
        while (index > 0) {
            if (++c > 'z') {
                c = 'a';
            }
            if (!skip.contains(String.valueOf((char)c))) {
                index--;
            }
        }
        return (char)c;
    }
}