import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> genreMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genreName = genres[i];
            int play = plays[i];

            add(genreMap, genreName, new Music(i, play));
        }

        List<Integer> res = new ArrayList<>();

        genreMap.values().stream().sorted().forEach(genre -> {
            List<Integer> bestMusics = genre.getBestMusics();
            res.addAll(bestMusics);
        });

        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private void add(Map<String, Genre> genreMap, String genreName, Music music) {
        if (!genreMap.containsKey(genreName)) {
            genreMap.put(genreName, new Genre(genreName));
        }
        genreMap.get(genreName).musics.add(music);
        genreMap.get(genreName).plays += music.plays;
    }

    public static class Genre implements Comparable<Genre> {
        String name;
        int plays;
        List<Music> musics = new ArrayList<>();

        public Genre(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Genre genre = (Genre) o;
            return Objects.equals(name, genre.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }

        @Override
        public int compareTo(Genre o) {
            return o.plays - plays;
        }

        List<Integer> getBestMusics() {
            Collections.sort(musics);
            List<Integer> res = new ArrayList<>();
            res.add(musics.get(0).number);
            if (musics.size() > 1) {
                res.add(musics.get(1).number);
            }

            return res;
        }
    }

    public static class Music implements Comparable<Music> {
        int number;
        int plays;

        public Music(int number, int plays) {
            this.number = number;
            this.plays = plays;
        }

        @Override
        public int compareTo(Music o) {
            if (plays == o.plays) {
                return number - o.number;
            }
            return o.plays - plays;
        }
    }
}