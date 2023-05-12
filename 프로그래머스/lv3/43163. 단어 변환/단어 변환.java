import java.util.*;

class Solution {
    Set<String> visited = new HashSet<>();
    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        Queue<String> Q = new LinkedList<>();
        Q.offer(begin);
        visited.add(begin);

        int step = 0;
        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                String word = Q.poll();

                List<String> nexts = getNexts(word, words);

                for (String next : nexts) {
                    if (next.equals(target)) {
                        return step + 1;
                    }
                    Q.offer(next);
                }
            }
            step++;
        }

        return 0;
    }

    private List<String> getNexts(String word, String[] words) {
        List<String> candidates = new ArrayList<>();
        for (String candidate : words) {
            if (!visited.contains(candidate) && onlyOneDiffrence(candidate, word)) {
                candidates.add(candidate);
                visited.add(candidate);
            }
        }
        return candidates;
    }

    private boolean onlyOneDiffrence(String candidate, String word) {
        int diffCnt = 0;
        for (int i = 0; i < candidate.length(); i++) {
            if (candidate.charAt(i) != word.charAt(i)) {
                diffCnt++;
            }
            if (diffCnt > 1) {
                return false;
            }
        }
        return true;
    }
}