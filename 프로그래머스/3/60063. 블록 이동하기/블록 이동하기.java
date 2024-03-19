import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

public class Solution {
    Set<Robot> visited = new HashSet();

    int[] dx = {-1,0,0,1};
    int[] dy = {0,-1,1,0};

    int[][] board;

    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        this.board = board;

        Queue<Robot> Q = new LinkedList();
        Robot start = new Robot(0,0,0,1);
        Q.add(start);
        visited.add(start);

        int ans = 0;
        while(!Q.isEmpty()){
            int qLen = Q.size();
//            out.println(qLen);

            for(int i=0;i<qLen;i++){
                Robot robot = Q.poll();

                if(arrived(robot, n-1,m-1)){
                    return ans;
                }

                // 수직, 수평 이동
                for(int d=0;d<4;d++){
                    Robot next = robot.move(dx[d], dy[d]);

                    if(next.movable() && !visited.contains(next)){
                        visited.add(next);
                        Q.add(next);
                    }
                }

                // 회전
                for(Robot next:robot.spinned()){
                    if(!visited.contains(next)){
                        visited.add(next);
                        Q.add(next);
                    }
                }
            }
            ans++;
        }


        return ans;
    }

    boolean arrived(Robot robot, int x, int y){
        return (robot.x1 == x && robot.y1 == y) ||
                (robot.x2 == x && robot.y2 == y);
    }

    class Robot{
        int x1,y1,x2,y2;

        Robot(int x1, int y1, int x2, int y2){
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        Robot move(int dx, int dy){
            return new Robot(x1 + dx, y1 + dy, x2 + dx, y2 + dy);
        }

        boolean movable(){
            return inRange(x1,y1) && inRange(x2,y2)
                    && board[x1][y1] == 0 && board[x2][y2] == 0;
        }
        
        /*
        1 0   (x1,y1) (x1,y1 + 1) x1,y1을 축으로 -90도 돌리면 x1,y1+1 => (x2,y2) ~ (x2,y2+1) ~ (x1,y1+1)
        1 0   (x2,y2) (x2,y2 + 1)
        
        1 1   (x1,y1)   (x2,y2)
        0 0   (x1+1,y1) (x2+1,y2)
        */

        List<Robot> spinned(){
            List<Robot> result = new ArrayList();
            // x1,y1을 축으로 -90도 회전
            Robot robot = checkAndSpin(x1,y1,x2,y2,-1);
            if(robot!= null){
                result.add(robot);
            }

            // x1,y1을 축으로 90도 회전
            robot = checkAndSpin(x1,y1,x2,y2,1);
            if(robot!=null){
                result.add(robot);
            }

            // x2,y2을 축으로 -90도 회전
            robot = checkAndSpin(x2,y2,x1,y1,-1);
            if(robot!=null){
                result.add(robot);
            }

            // x2,y2을 축으로 90도 회전
            robot = checkAndSpin(x2,y2,x1,y1,1);
            if(robot!=null){
                result.add(robot);
            }

            return result;
        }

        Robot checkAndSpin(int axisX,int axisY, int spinX, int spinY, int dir){
            int dx = axisX - spinX;
            int dy = axisY - spinY;
            int[] deltas = spin(dx, dy, dir);
            int destX = axisX + deltas[0];
            int destY = axisY + deltas[1];
            int midX = axisX + (-dx | deltas[0]);
            int midY = axisY + (-dy | deltas[1]);

            if(inRange(midX, midY) && board[midX][midY] == 0 &&
                    inRange(destX, destY) && board[destX][destY] == 0){
                return new Robot(axisX, axisY, destX, destY);
            }
            return null;
        }

        // dir = -1 or 1
        int[] spin(int dx, int dy, int dir){
            dx = Math.abs(dx) ^ 1;
            dy = Math.abs(dy) ^ 1;

            dx *= dir;
            dy *= dir;

            return new int[]{dx, dy};
        }

        public boolean equals(Object o){
            if(o instanceof Robot){
                Robot r = (Robot)o;
                return (x1 == r.x1 && y1 == r.y1 && x2 == r.x2 && y2 == r.y2) ||
                        (x1 == r.x2 && y1 == r.y2 && x2 == r.x1 && y2 == r.y1);
            }
            return false;
        }

        public int hashCode(){
            return Objects.hash(x1, y1, x2, y2) + Objects.hash(x2,y2,x1,y1);
        }
    }

    boolean inRange(int x,int y){
        return 0<= x && x < board.length
                && 0<=y && y< board[0].length;
    }
}



// 회전에 1
// 이동에 1
/*
앞 뒤 구분없음

로봇의 상태는 순서 상관없는 두 개의 좌표로 표현 가능
*/