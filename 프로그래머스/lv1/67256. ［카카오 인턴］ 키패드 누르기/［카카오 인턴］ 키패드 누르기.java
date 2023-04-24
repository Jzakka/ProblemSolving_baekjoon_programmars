
import java.util.*;

class Solution {
    Map<Character, int[]> keyPad = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    public String solution(int[] numbers, String hand) {
        keyPad.put('1', new int[]{1, 1});
        keyPad.put('2', new int[]{1, 2});
        keyPad.put('3', new int[]{1, 3});
        keyPad.put('4', new int[]{2, 1});
        keyPad.put('5', new int[]{2, 2});
        keyPad.put('6', new int[]{2, 3});
        keyPad.put('7', new int[]{3, 1});
        keyPad.put('8', new int[]{3, 2});
        keyPad.put('9', new int[]{3, 3});
        keyPad.put('*', new int[]{4, 1});
        keyPad.put('0', new int[]{4, 2});
        keyPad.put('#', new int[]{4, 3});

        Finger lf = new LeftFinger();
        Finger rf = new RightFinger();

        for (int number : numbers) {
            tap(number,hand, lf, rf);
        }

        return sb.toString();
    }

    private void tap(int number,String hand, Finger lf, Finger rf) {
        if (number == 1 || number == 4 || number == 7) {
            sb.append('L');
            lf.moveTo((char)(number+'0'));
        } else if (number == 3 || number == 6 || number == 9) {
            sb.append('R');
            rf.moveTo((char)(number+'0'));
        } else {
            selectNearst(number, hand, lf, rf);
        }
    }

    private void selectNearst(int number, String hand, Finger lf, Finger rf) {
        int leftDistance = getDistance(number, lf);
        int rightDistance = getDistance(number, rf);

        if (leftDistance < rightDistance) {
            sb.append('L');
            lf.moveTo((char)(number+'0'));
        } else if (leftDistance > rightDistance) {
            sb.append('R');
            rf.moveTo((char)(number+'0'));
        } else {
            if (hand.equals("right")) {
                sb.append('R');
                rf.moveTo((char) (number + '0'));
            } else {
                sb.append('L');
                lf.moveTo((char)(number+'0'));
            }
        }
    }

    private int getDistance(int number, Finger finger) {
        int[] numPos = keyPad.get((char) (number + '0'));
        int[] fingPos = finger.pos;

                return Math.abs(numPos[1] - fingPos[1]) + Math.abs(numPos[0] - fingPos[0]);

    }

    abstract class Finger {
        int[] pos;

        public void moveTo(char key) {
            pos = keyPad.get(key);
        }
    }

    class LeftFinger extends Finger{
        public LeftFinger() {
            pos = keyPad.get('*');
        }
    }

    class RightFinger extends Finger{
        public RightFinger() {
            pos = keyPad.get('#');
        }
    }
}