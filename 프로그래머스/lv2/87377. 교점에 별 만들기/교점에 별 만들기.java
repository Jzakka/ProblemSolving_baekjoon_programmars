import java.util.*;
import java.util.stream.IntStream;

class Solution {
    Meets meets;

    public String[] solution(int[][] line) {
        meets = new Meets(line);

        return print(meets);
    }

    public String[] print(Meets meets) {
        int xOffset, yOffset, xLimit, yLimit;
        int[] area = getField(meets);

        xOffset = area[0];
        yOffset = area[1];
        xLimit = area[2];
        yLimit = area[3];

        String[] res = new String[yOffset - yLimit + 1];
        for (int y = 0; y <= yOffset - yLimit; y++) {
            StringBuilder sb = new StringBuilder();

            int finalY = y;
            IntStream.range(0, xLimit - xOffset + 1).forEach(x -> print(xOffset, yOffset, finalY, sb, x));

            res[y] = sb.toString();
        }

        return res;
    }

    private void print(int xOffset, int yOffset, int y, StringBuilder sb, int x) {
        int originX = x + xOffset;
        int originY = -y + yOffset;
        if (isMeet(originX, originY)) {
            sb.append('*');
            return;
        }
        sb.append('.');
    }

    private boolean isMeet(int originX, int originY) {
        return meets.has(new Meet(originX, originY));
    }

    public int[] getField(Meets meets) {
        int xOffset = Integer.MAX_VALUE, yOffset = Integer.MIN_VALUE,
                xLimit = Integer.MIN_VALUE, yLimit = Integer.MAX_VALUE;

        for (Meet meet : meets) {
            xOffset = Math.min(xOffset, meet.x);
            yOffset = Math.max(yOffset, meet.y);
            xLimit = Math.max(xLimit, meet.x);
            yLimit = Math.min(yLimit, meet.y);
        }

        return new int[]{xOffset, yOffset, xLimit, yLimit};
    }

    static class Meets implements Iterable<Meet> {
        private Set<Meet> meets;

        public Meets(int[][] line) {
            this.meets = intMeets(line);
        }

        private Set<Meet> intMeets(int[][] line) {
            Set<Meet> meets = new HashSet<>();
            for (int i = 0; i < line.length; i++) {
                for (int j = i + 1; j < line.length; j++) {
                    Meet meet = getMeet(line[i], line[j]);
                    if (meet != null) {
                        meets.add(meet);
                    }
                }
            }
            return meets;
        }

        private Meet getMeet(int[] line1, int[] line2) {
            long a, b, c, d, e, f;
            a = line1[0];
            b = line1[1];
            c = line2[0];
            d = line2[1];
            e = line1[2];
            f = line2[2];


            long parent = a * d - b * c;
            if (parent == 0) {
                return null;
            }

            long xChild = b * f - e * d;
            long yChild = e * c - a * f;

            if (xChild % parent != 0 || yChild % parent != 0) {
                return null;
            }

            return new Meet((int) (xChild / parent), (int) (yChild / parent));
        }

        public boolean has(Meet meet) {
            return meets.contains(meet);
        }

        @Override
        public Iterator<Meet> iterator() {
            return meets.iterator();
        }
    }

    static class Meet {
        int x;
        int y;

        public Meet(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Meet meet = (Meet) o;
            return x == meet.x && y == meet.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}