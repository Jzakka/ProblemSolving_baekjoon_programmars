import java.util.*;

class Solution {
    private Map<String, Integer> degree = new HashMap<>();

    public String solution(String[] survey, int[] choices) {
        degree.put("RT", 0);
        degree.put("CF", 0);
        degree.put("JM", 0);
        degree.put("AN", 0);

        for (int i = 0; i < survey.length; i++) {
            String question = survey[i];
            int choice = choices[i];
            check(question, choice);
        }

        StringBuilder result = new StringBuilder();
        buildResult("RT", result);
        buildResult("CF", result);
        buildResult("JM", result);
        buildResult("AN", result);

        return result.toString();
    }

    private void buildResult(String an, StringBuilder result) {
        if (degree.get(an) > 0) {
            result.append(an.charAt(1));
        } else {
            result.append(an.charAt(0));
        }
    }

    private void check(String question, int choice) {
        boolean reverse = false;
        if (!degree.containsKey(question)) {
            question = new StringBuilder(question).reverse().toString();
            reverse = true;
        }

        choice -= 4;
        degree.put(question, degree.get(question) + (reverse ? -choice : choice));
    }
}