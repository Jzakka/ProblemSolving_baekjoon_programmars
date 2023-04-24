
import java.util.*;

class Solution {
    Map<String, String> userContainer = new HashMap<>();
    ArrayList<Log> logs = new ArrayList<>();
    public String[] solution(String[] records) {
        for (String record : records) {
            update(record);
        }

        return logs.stream().map(Log::toString).toArray(String[]::new);
    }

    private void update(String record) {
        String[] datum = record.split(" ");
        String action = datum[0];
        String id = datum[1];

        if (action.equals("Enter")) {
            userContainer.put(id, datum[2]);
            logs.add(new Log(true, id));
        } else if (action.equals("Change")) {
            userContainer.put(id, datum[2]);
        } else {
            logs.add(new Log(false, id));
        }
    }

    class Log{
        boolean enter;
        String who;

        public Log(boolean enter, String who) {
            this.enter = enter;
            this.who = who;
        }

        @Override
        public String toString() {
            return String.format("%s님이 %s습니다.", userContainer.get(who), enter?"들어왔":"나갔");
        }
    }
}