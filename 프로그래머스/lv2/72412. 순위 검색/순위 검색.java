import java.util.*;

class Solution {

    String[] lang = {"cpp", "java", "python", "-"};
    String[] task = {"backend", "frontend", "-"};
    String[] career = {"junior", "senior",  "-"};
    String[] soulFood = {"chicken", "pizza", "-"};

    Map<Applier, List<Integer>> cases = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        caseBuild();

        for (String i : info) {
            String[] datum = i.split(" ");
            String language = datum[0];
            String task = datum[1];
            String career = datum[2];
            String soulFood = datum[3];
            int score = Integer.parseInt(datum[4]);
            add(language, task, career, soulFood, score);
        }

        cases.values().forEach(Collections::sort);

        int[] res = new int[query.length];
        int i=0;
        for (String q : query) {
            String[] words = q.split(" ");
            Applier criterion = new Applier(words[0], words[2], words[4], words[6]);
            int criterionScore = Integer.parseInt(words[7]);
            List<Integer> result = cases.get(criterion);
            res[i++] = count(result, criterionScore);
        }

        return res;
    }

    private int count(List<Integer> result, int c) {
        int index = Collections.binarySearch(result, c);

        if (index < 0) {
            index = -(index + 1);
        } else {
            while (index > 0 && result.get(index-1)==c) {
                index--;
            }
        }


        return result.size()-index;
    }

    private void add(String language, String task, String career, String soulFood, int score) {
        cases.get(new Applier(language, task, career, soulFood)).add(score);
        cases.get(new Applier("-", task, career, soulFood)).add(score);
        cases.get(new Applier(language, "-", career, soulFood)).add(score);
        cases.get(new Applier(language, task, "-", soulFood)).add(score);
        cases.get(new Applier(language, task, career, "-")).add(score);
        cases.get(new Applier("-", "-", career, soulFood)).add(score);
        cases.get(new Applier("-", task, "-", soulFood)).add(score);
        cases.get(new Applier("-", task, career, "-")).add(score);
        cases.get(new Applier(language, "-", "-", soulFood)).add(score);
        cases.get(new Applier(language, "-", career, "-")).add(score);
        cases.get(new Applier(language, task, "-", "-")).add(score);
        cases.get(new Applier("-", "-", "-", soulFood)).add(score);
        cases.get(new Applier("-", "-", career, "-")).add(score);
        cases.get(new Applier("-", task, "-", "-")).add(score);
        cases.get(new Applier(language, "-", "-", "-")).add(score);
        cases.get(new Applier("-", "-", "-", "-")).add(score);
    }

    private void caseBuild() {
        for (String l : lang) {
            for (String t : task) {
                for (String c : career) {
                    for (String s : soulFood) {
                        cases.put(new Applier(l, t, c, s), new ArrayList<>());
                    }
                }
            }
        }
    }

    class Applier{
        String language;
        String task;
        String career;
        String soulFood;

        public Applier(String language, String task, String career, String soulFood) {
            this.language = language;
            this.task = task;
            this.career = career;
            this.soulFood = soulFood;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Applier applier = (Applier) o;
            return (Objects.equals(language, applier.language) || applier.language.equals("-") || language.equals("-"))
                    && (Objects.equals(task, applier.task) || applier.task.equals("-") || task.equals("-"))
                    && (Objects.equals(career, applier.career) || applier.career.equals("-") || career.equals("-"))
                    && (Objects.equals(soulFood, applier.soulFood) || applier.soulFood.equals("-") || soulFood.equals("-"));
        }

        @Override
        public int hashCode() {
            return Objects.hash(language, task, career, soulFood);
        }
    }
}