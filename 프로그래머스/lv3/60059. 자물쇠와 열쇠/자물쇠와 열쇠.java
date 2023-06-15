import java.util.*;
import java.util.function.BiFunction;

class Solution {
    int m;
    private BiFunction<Integer, Integer, Position>[] calcSpin = new BiFunction[4];

    public boolean solution(int[][] key, int[][] lock) {
        m = lock.length;
        List<Position> keyBump = new ArrayList<>();
        List<Position> lockHollow = new ArrayList<>();

        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key[0].length; j++) {
                if (key[i][j] == 1) {
                    keyBump.add(new Position(i, j));
                }
            }
        }

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock[0].length; j++) {
                if (lock[i][j] == 0) {
                    lockHollow.add(new Position(i, j));
                }
            }
        }

        int m = lock.length;
        calcSpin[0] = (x, y) -> new Position(x, y);
        calcSpin[1] = (x, y) -> new Position(y, -x + m - 1);
        calcSpin[2] = (x, y) -> new Position(-x + m - 1, -y + m - 1);
        calcSpin[3] = (x, y) -> new Position(-y + m - 1, x);

        if (lockHollow.isEmpty()) {
            return true;
        }

        for (Position criterionBump : keyBump) {
            int spin = 0;

            boolean isUnlocked = false;
            while (spin < 4 && !(isUnlocked = unlocked(lockHollow, keyBump, spin, criterionBump))) {
                spin++;
            }

            if (isUnlocked) {
                return true;
            }
        }

        return false;
    }

    private boolean unlocked(List<Position> lockHollows, List<Position> key, int spin, Position criterionBump) {
        Set<Position> rotatedKeyBumps = getRotatedKey(key, spin);
        Position spinCriterionBump = calcSpin[spin].apply(criterionBump.x, criterionBump.y);

        Set<Position> movedKeyBumps = getMovedKey(rotatedKeyBumps, lockHollows.get(0), spinCriterionBump);

        for (Position lockHollow : lockHollows) {
            if (!movedKeyBumps.contains(lockHollow)) {
                return false;
            }
            movedKeyBumps.remove(lockHollow);
        }

        for (Position remainBump : movedKeyBumps) {
            int x = remainBump.x;
            int y = remainBump.y;
            if (x >= 0 && x < m && y >= 0 && y < m) {
                return false;
            }
        }

        return true;
    }

    private Set<Position> getMovedKey(Set<Position> rotatedKey, Position hollow, Position spinCriterionBump) {
        Set<Position> movedKey = new LinkedHashSet<>();
        int xOffset = hollow.x - spinCriterionBump.x;
        int yOffset = hollow.y - spinCriterionBump.y;

        for (Position bump : rotatedKey) {
            movedKey.add(new Position(bump.x + xOffset, bump.y + yOffset));
        }

        return movedKey;
    }

    private Set<Position> getRotatedKey(List<Position> keyBumps, int spin) {
        Set<Position> rotatedKey = new LinkedHashSet<>();

        for (Position bump : keyBumps) {
            Position spinPosition = calcSpin[spin].apply(bump.x, bump.y);
            rotatedKey.add(new Position(spinPosition.x, spinPosition.y));
        }
        return rotatedKey;
    }

    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x && y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
