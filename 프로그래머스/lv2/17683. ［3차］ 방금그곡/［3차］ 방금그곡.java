
import java.util.ArrayList;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String melody = parseMelody(m);

        ArrayList<Object[]> candidates = new ArrayList();

        for (String musicinfo : musicinfos) {
            Object[] objects = parseInfo(musicinfo);
            Integer playTime = (Integer) objects[0];
            String totalPlayed = (String) objects[2];

            if (isPartOf(totalPlayed, melody, playTime)) {
                candidates.add(objects);
            }
        }
        candidates.sort((o1,o2)->{
            if (o1[0].equals(o2[0])) {
                return 0;    
            }
            return (Integer) o2[0] - (Integer) o1[0];
            
        });

        return candidates.isEmpty()?"(None)":(String) candidates.get(0)[1];
    }

    public String parseMelody(String melody) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < melody.length(); i++) {
            char alphabet = melody.charAt(i);
            if (alphabet == 'C' || alphabet == 'D' || alphabet == 'F'
                    || alphabet == 'G' || alphabet == 'A') {
                if (i + 1 < melody.length() && melody.charAt(i+1) == '#') {
                    sb.append((char)(alphabet - ('A' - 'a')));
                    i++;
                    continue;
                }
            }
            sb.append(alphabet);
        }

        return sb.toString();
    }

    public boolean isPartOf(String radioMelody, String melody, Integer time) {
        String totalPlayed = radioMelody.repeat(Math.max(0, time / radioMelody.length())) +
                radioMelody.substring(0, time % radioMelody.length());
        return totalPlayed.contains(melody);
    }

    public Object[] parseInfo(String info) {
        String[] infos = info.split(",");

        Integer time = calcTime(infos[0], infos[1]);
        String title = infos[2];
        String melody = parseMelody(infos[3]);

        return new Object[]{time, title, melody};
    }

    public int calcTime(String start, String end) {
        int startTime = getMinutes(start);
        
        int endTime = getMinutes(end);

        return endTime - startTime;
    }

    private int getMinutes(String start) {
        String[] units = start.split(":");
        int hours = Integer.parseInt(units[0]);
        return hours * 60 + Integer.parseInt(units[1]);
    }
}