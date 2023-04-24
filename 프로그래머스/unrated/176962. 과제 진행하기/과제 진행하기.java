import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[][] plans) {
        LinkedList<Subject> subjects = new LinkedList<>(Arrays.stream(plans)
                .map(Subject::new)
                .sorted()
                .collect(Collectors.toList()));

        Stack<Subject> inCompleted = new Stack<>();
        Iterator<Subject> iterator = subjects.iterator();
        inCompleted.push(iterator.next());

        ArrayList<Subject> res = new ArrayList<>();

        while (iterator.hasNext()) {
            Subject newSubject = iterator.next();
            while (!inCompleted.empty() && (inCompleted.peek().endTime <= newSubject.startTime)) {// 걸치치 않는 동안 전부 pop
                Subject latestOngoing = inCompleted.pop();
                res.add(latestOngoing);
                if (!inCompleted.isEmpty()) {
                    Subject prevSubject = inCompleted.peek();
                    prevSubject.startTime = latestOngoing.endTime;
                    prevSubject.endTime = prevSubject.startTime + prevSubject.remainTime;
                }
            }
            if (!inCompleted.empty()) {
                inCompleted.peek().remainTime -= newSubject.startTime - inCompleted.peek().startTime;
            }
            inCompleted.push(newSubject);
        }

        while (!inCompleted.isEmpty()) {
            res.add(inCompleted.pop());
        }

        return res.stream().map(s -> s.name).toArray(String[]::new);
    }

    class Subject implements Comparable<Subject>{
        String name;
        int startTime;
        int endTime;
        int remainTime;

        public Subject(String[] plan) {
            String name = plan[0];
            String start = plan[1];
            String play = plan[2];

            this.name = name;
            startTime = timeParse(start);
            endTime = startTime + Integer.parseInt(play);
            remainTime = Integer.parseInt(play);
        }

        private int timeParse(String start) {
            String[] hhmm = start.split(":");
            int hour = Integer.parseInt(hhmm[0]);
            int minute = Integer.parseInt(hhmm[1]);

            return hour * 60 + minute;
        }

        @Override
        public int compareTo(Subject o) {
            return this.startTime-o.startTime;
        }
    }
}