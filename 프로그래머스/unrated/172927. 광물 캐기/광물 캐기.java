import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        if (5 * (picks[0] + picks[1] + picks[2]) < minerals.length) {
            minerals = Arrays.copyOfRange(minerals, 0, 5 * (picks[0] + picks[1] + picks[2]));
        }

        ArrayList<String[]> fives = group(minerals);

        List<Durability> durabilities = new ArrayList<>();
        for (int i = 0; i < fives.size(); i++) {
            String[] five = fives.get(i);
            Durability durability = new Durability();
            calc(durability, five);
            durabilities.add(durability);
        }

        pick(picks, durabilities.size());



        int res = 0;
        durabilities.sort(Comparator.comparingInt(d -> d.s));
        for (int i = 0; i < picks[2]; i++) {
            res += durabilities.get(i).s;
        }
        durabilities = durabilities.subList(picks[2], durabilities.size());

        durabilities.sort(Comparator.comparingInt(d -> d.i));
        for (int i = 0; i < picks[1]; i++) {
            res += durabilities.get(i).i;
        }
        durabilities = durabilities.subList(picks[1], durabilities.size());

        durabilities.sort(Comparator.comparingInt(d -> d.d));
        for (int i = 0; i < picks[0]; i++) {
            res += durabilities.get(i).d;
        }

        return res;
    }

    private void pick(int[] picks, int size) {
        int d = 0;
        int i = 0;
        int s = 0;

        while (picks[0] > 0 && size > 0) {
            picks[0]--;
            d++;
            size--;
        }

        while (picks[1] > 0 && size > 0) {
            picks[1]--;
            i++;
            size--;
        }

        while (picks[2] > 0 && size > 0) {
            picks[2]--;
            s++;
            size--;
        }

        picks[0] = d;
        picks[1] = i;
        picks[2] = s;
    }

    private void calc(Durability dura, String[] five) {
        dura.d = five.length;
        dura.i = calcForIron(five);
        dura.s = calcForStone(five);
    }

    private int calcForStone(String[] five) {
        int sum = 0;
        for (String matter : five) {
            if (matter.equals("diamond")) {
                sum += 25;
            } else if (matter.equals("iron")) {
                sum += 5;
            } else {
                sum++;
            }
        }
        return sum;
    }

    private int calcForIron(String[] five) {
        int sum = 0;
        for (String matter : five) {
            if (matter.equals("diamond")) {
                sum += 5;
            } else {
                sum++;
            }
        }
        return sum;
    }

    private ArrayList<String[]> group(String[] minerals) {
        ArrayList<String[]> mineralGroup = new ArrayList<>();

        int i = 0;
        for (; i + 5 < minerals.length; i+=5) {
            mineralGroup.add(Arrays.copyOfRange(minerals, i, i + 5));
        }
        mineralGroup.add(Arrays.copyOfRange(minerals, i, minerals.length));

        return mineralGroup;
    }

    class Durability {
        int d;
        int i;
        int s;
    }
}