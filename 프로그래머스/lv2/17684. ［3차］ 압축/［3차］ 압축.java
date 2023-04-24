import java.util.*;

class Solution {
    Map<String, Integer> dictionary = new HashMap<>();
    Integer index = 1;

    public int[] solution(String msg) {
        initDictionary();

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < msg.length(); i++) {
            int j = 1;
            while (i+j < msg.length() && dictionary.containsKey(msg.substring(i, i + j + 1))) {
                j++;
            }
            String w = msg.substring(i, i + j);
            result.add(dictionary.get(w));

            if (i + j < msg.length()) {
                String c = msg.substring(i + j, i + j + 1);
                dictionary.put(w + c, index++);
            }
            i = i + j-1;
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public void initDictionary() {
        for (char word = 'A'; word <= 'Z'; word++) {
            dictionary.put(String.valueOf(word), index++);
        }
    }
}