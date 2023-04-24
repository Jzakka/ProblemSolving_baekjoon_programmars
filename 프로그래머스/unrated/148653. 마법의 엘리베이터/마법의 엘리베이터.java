class Solution {
    public int solution(int storey) {
        return move(storey, 0);
    }

    public int move(int floor, int movedCnt) {
        if (floor == 0) {
            return movedCnt;
        }

        int digit = floor % 10;
        if (digit > 5) {
            return move((floor+10) / 10, movedCnt + (10 - digit));
        } else if (digit < 5) {
            return move(floor / 10, movedCnt + digit);
        } else {
            int secondDigit = floor % 100 / 10;
            if (secondDigit >= 5) {
                return move((floor + 10) / 10, movedCnt + 5);
            } else {
                return move(floor / 10, movedCnt + 5);
            }
        }
    }
}