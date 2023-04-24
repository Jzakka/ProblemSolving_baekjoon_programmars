import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> participants = new HashMap<>();
        for (String p : participant) {
            if (participants.containsKey(p)) {
                participants.put(p, participants.get(p) + 1);
            } else {
                participants.put(p, 1);
            } 
        }
        for (String c : completion) {
            participants.put(c, participants.get(c) - 1);
        }
        for (Map.Entry<String, Integer> e: participants.entrySet()) {
            if (e.getValue() > 0) {
                return e.getKey();
            }   
        }
        return "";
    }
}