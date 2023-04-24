import java.util.*;

class Solution {
    Map<String, Integer> rank = new HashMap<>();
    
    public String[] solution(String[] players, String[] callings) {
        for (int i = 0; i < players.length; i++) {
            rank.put(players[i], i + 1);
        }

        Arrays.stream(callings).forEach(called->{
            Integer rank = this.rank.get(called);
            Integer index = rank -1; // index + 1 => index = rank - 1

            Integer prevManRank = index;
            Integer prevManIndex = prevManRank - 1;
            String prevMan = players[prevManIndex];// preManRank = index-1

            this.rank.put(prevMan, rank);
            this.rank.put(called, prevManRank);

            players[prevManIndex] = called;
            players[index] = prevMan;
        });

        return players;
    }
}