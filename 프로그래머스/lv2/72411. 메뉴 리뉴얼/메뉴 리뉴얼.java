import java.util.*;

class Solution {
    Map<String, Integer> combinations = new LinkedHashMap<>();

    public String[] solution(String[] orders, int[] courses) {
        for (String order : orders) {
            for (int course : courses) {
                char[] chars = new char[course];
                combination(order.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString(),
                        0,0,course,chars);
            }
        }

        List<String> res = new ArrayList<>();
        for (int course : courses) {
            Optional<String> maxStr = combinations.keySet().stream().filter(k -> k.length() == course)
                    .max((a, b) -> combinations.get(a) - combinations.get(b));
            if (maxStr.isPresent()) {
                int max = combinations.get(maxStr.get());
                if (max > 1) {
                    combinations.keySet().stream().filter(k -> k.length() == course).filter(k-> combinations.get(k)==max).forEach(res::add);
                }
            }
        }
        Collections.sort(res);

        return res.toArray(String[]::new);
    }

    public void combination(final String order, int cur, int cnt, final int k, char[] chars) {
        if (cur == order.length()) {
            return;
        }

        //select
        chars[cnt] = order.charAt(cur);
        if (cnt + 1 == k) {
            String comb = String.valueOf(chars);
            combinations.put(comb, combinations.getOrDefault(comb, 0)+1);
            //ignore
            combination(order, cur+1, cnt, k, chars);
            return;
        }
        combination(order, cur+1, cnt+1, k, chars);
        //ignore
        combination(order, cur+1, cnt, k, chars);
    }
}