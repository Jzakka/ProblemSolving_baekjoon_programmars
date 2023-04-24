import java.util.*;
import java.util.function.BiFunction;

class Solution {
    Map<Character, BiFunction<Long, Long, Long>> op = new HashMap<>();

    {
        op.put('-', (num1, num2) -> num1 - num2);
        op.put('+', (num1, num2) -> num1 + num2);
        op.put('*', (num1, num2) -> num1 * num2);
    }

    public long solution(String expression) {
        // -                0
        // +                1
        // *                2
        // -, + => 2        3
        // -, * => 2        4
        // +, * => 2        5
        // -, +, * => 6     6
        // - + *        7
        // - * +        8
        // + - *        9
        // + * -        10
        // * - +        11
        // * + -        12

        Long maxVal = Long.MIN_VALUE;
        List<List<Character>> priorities = priorities(operators(expression));
        for (List<Character> pr : priorities) {
            maxVal = Math.max(maxVal, calc(expression, pr));
        }

        return maxVal;
    }

    public List<List<Character>> priorities(List<Character> ops) {
        List<List<Character>> pr = new ArrayList<>();
        if (ops.size() == 1) {
            pr.add(ops);
        } else if (ops.size() == 2) {
            pr.add(ops);
            pr.add(List.of(ops.get(1), ops.get(0)));
        } else {
            pr.add(List.of('-', '+', '*'));
            pr.add(List.of('-', '*', '+'));
            pr.add(List.of('+', '-', '*'));
            pr.add(List.of('+', '*', '-'));
            pr.add(List.of('*', '-', '+'));
            pr.add(List.of('*', '+', '-'));
        }
        return pr;
    }

    public List<Character> operators(String exp) {
        List<Character> ops = new ArrayList<>();
        if (exp.contains("+")) {
            ops.add('+');
        }
        if (exp.contains("-")) {
            ops.add('-');
        }
        if (exp.contains("*")) {
            ops.add('*');
        }
        return ops;
    }

    public Long calc(String exp, List<Character> priority) {
        ArrayList<String> list = ListOf(exp);
        for (Character operator : priority) {
            calc(list, operator);
        }
        return Math.abs(Long.parseLong(list.get(0)));
    }

    private ArrayList<String> ListOf(String exp) {
        ArrayList<String> list = new ArrayList<>();
        int start = 0;
        int end = 1;
        while (start < exp.length()) {
            while (end < exp.length() && Character.isDigit(exp.charAt(end))) {
                end++;
            }
            list.add(exp.substring(start, end));
            if (end < exp.length()) {
                list.add(String.valueOf(exp.charAt(end)));
            }
            start = end + 1;
            end = start + 1;
        }
        return list;
    }

    // 1000+3000+20000+1000
    public ArrayList<String> calc(ArrayList<String> exp, Character operator) {
        while (exp.contains(String.valueOf(operator))) {
            int idxOfOp = exp.indexOf(String.valueOf(operator));
            long left = Long.parseLong(exp.get(idxOfOp-1));
            long right = Long.parseLong(exp.get(idxOfOp + 1));
            exp.set(idxOfOp + 1, String.valueOf(op.get(operator).apply(left, right)));
            exp.remove(idxOfOp - 1);
            exp.remove(idxOfOp - 1);
        }
        return exp;
    }
}