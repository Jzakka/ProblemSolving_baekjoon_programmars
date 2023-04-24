import java.util.*;

class Solution {
    private int[] cards;

    private boolean[] visited;

    private Integer[] boxSize;

    public int solution(int[] cards) {
        this.cards = cards;
        visited = new boolean[cards.length + 1];
        boxSize = Arrays.stream(new int[cards.length]).boxed().toArray(Integer[]::new);

        int i = 0;
        for (int card : cards) {
            if (!visited[card]) {
                dfs(card, i);
                i++;
            }
        }

        Arrays.sort(boxSize, Collections.reverseOrder());
        return boxSize[0]*boxSize[1];
    }

    private void dfs(int card,final int idx) {
        if (visited[card]) {
            return;
        }
        boxSize[idx]++;
        visited[card] = true;
        dfs(cards[card-1], idx);
    }
}