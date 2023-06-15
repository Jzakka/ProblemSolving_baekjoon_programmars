import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

class Solution {
    LinkedHashMap<String, List<String>> dispatch = new LinkedHashMap<>();

    public String solution(int n, int t, int m, String[] timetable) {
        for (int i = 0; i < n; i++) {
            dispatch.put(LocalTime.of(9, 0).plus(i * t, ChronoUnit.MINUTES).toString(), new ArrayList<>());
        }

        Queue<String> crews = Arrays.stream(timetable).sorted().collect(Collectors.toCollection(LinkedList::new));

        for (String leaveTime : dispatch.keySet()) {
            int remainSeats = m;

            while (!crews.isEmpty() && remainSeats > 0 && !LocalTime.parse(crews.peek()).isAfter(LocalTime.parse(leaveTime))) {
                remainSeats--;
                dispatch.get(leaveTime).add(crews.poll());
            }
        }

        Map.Entry entry = dispatch.entrySet().toArray(Map.Entry[]::new)[dispatch.size() - 1];
        ArrayList<String> lastCrews = (ArrayList<String>) entry.getValue();

        if (lastCrews.size() < m) {
            return (String) entry.getKey();
        }

        String lastCrewArrivalTime = lastCrews.get(lastCrews.size() - 1);
        return LocalTime.parse(lastCrewArrivalTime).minus(1, ChronoUnit.MINUTES).toString();
    }
}