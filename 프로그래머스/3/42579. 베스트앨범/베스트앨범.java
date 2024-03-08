import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    Map<String, Integer> playCnt = new HashMap();
    Map<String, List<Integer>> playlist = new HashMap();
    
    public int[] solution(String[] genres, int[] plays) {
        int n = genres.length;
        
        for(int i=0;i<n;i++){
            String genre = genres[i];
            int count = plays[i];
            
            mapAdd(genre, count, i);
        }
        
        return playCnt.entrySet().stream()
            .sorted((e1,e2) -> e2.getValue() - e1.getValue())
            .map(e -> playlist.get(e.getKey()))
            .peek(list -> list.sort(Comparator.comparingInt(i -> -plays[i])))
            .flatMapToInt(list -> list.subList(0,Math.min(list.size(), 2)).stream().mapToInt(Integer::intValue))
            .toArray();
    }
    
    void mapAdd(String genre, int count, int music){
        if(!playCnt.containsKey(genre)){
            // out.println("First" + genre + ":" + music);
            playCnt.put(genre, count);
            
            List<Integer> musics = new ArrayList();
            musics.add(music);
            playlist.put(genre, musics);
            return;
        }
        playCnt.put(genre, playCnt.get(genre) + count);
        // out.println(genre + ":" + music + " " + playCnt.get(genre));
        playlist.get(genre).add(music);
    }
}