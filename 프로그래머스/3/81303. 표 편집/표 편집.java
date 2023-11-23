import java.util.Spliterator;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.IntStream;

class Solution {
    Stack<Integer> history = new Stack<>();
    TreeSet<Integer> table = new TreeSet<>();
    int cursorKey = 0;

    public String solution(int n, int k, String[] cmds) {
        IntStream.range(0, n).forEach(i -> table.add(i));
        cursorKey = k;

        for (String cmd : cmds) {
            char action = cmd.charAt(0);

            if (action == 'U' || action == 'D') {
                int moveCnt = Integer.parseInt(cmd.substring(2));
                move(action, moveCnt);
            } else if (action == 'C') {
                delete();
            } else {
                undo();
            }
        }

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (table.contains(i)) {
                res.append("O");
            } else {
                res.append("X");
            }
        }
        return res.toString();
    }

    private void undo() {
        Integer recent = history.pop();
        table.add(recent);
    }

    private void delete() {
        if (table.last() == cursorKey) {
            Integer last = table.last();
            cursorKey = table.lower(last);
            table.remove(last);
            history.push(last);
        } else {
            cursorKey = table.higher(cursorKey);
            Integer deleted = table.lower(cursorKey);
            table.remove(deleted);
            history.push(deleted);
        }
    }

    private void move(char direction, int moveCnt) {
        for (int i = 0; i < moveCnt; i++) {
            if (direction == 'U') {
                cursorKey = table.lower(cursorKey);
            } else {
                cursorKey = table.higher(cursorKey);
            }
        }
    }


}