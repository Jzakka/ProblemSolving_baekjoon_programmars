import java.util.*;

class Solution {
    Set<LinkedHashSet<List<Integer>>> visited = new HashSet();
    int[][] board;
    int[][] minTime;
    int n,m;
    
    public int solution(int[][] board) {
        n = board.length;
        m = board[0].length;
        this.board = board;
        minTime = new int[n][m];
        for(int[] line:minTime){
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 1;
        
        Queue<LinkedHashSet<List<Integer>>> Q = new LinkedList();
        LinkedHashSet<List<Integer>> start = setOf(Arrays.asList(x1,y1), Arrays.asList(x2,y2));
        Q.add(start);
        visited.add(start);
        
        int res = 0;
        while(!Q.isEmpty()){
            int qLen = Q.size();
            
            for(int i = 0;i<qLen;i++){
                LinkedHashSet<List<Integer>> pos = Q.poll();
                
                Iterator<List<Integer>> it = pos.iterator();
                List<Integer> head = it.next();
                List<Integer> tail = it.next();
            
                // System.out.printf("%sLV:%d, HEAD:(%d,%d), TAIL:(%d,%d)%n", "\t".repeat(res), res, head.get(0), head.get(1), tail.get(0), tail.get(1));
                minTime[head.get(0)][head.get(1)] = Math.min(res, minTime[head.get(0)][head.get(1)]);
                minTime[tail.get(0)][tail.get(1)] = Math.min(res, minTime[tail.get(0)][tail.get(1)]);
                
                List<LinkedHashSet<List<Integer>>> nexts = available(pos);
                
                for(LinkedHashSet<List<Integer>> next:nexts){
                    if(next.contains(Arrays.asList(n-1, m-1))){
                        // printMinTime();
                        return res+1;
                    }
                }
                
                Q.addAll(nexts);
            }
            
            res++;
        }
        
        
        
        return res;
    }
    
    void printMinTime(){
        for(int i = 0; i< n;i++){
            for(int j = 0; j< m;j++){
                System.out.printf("%02d|", minTime[i][j] == Integer.MAX_VALUE ? -1 : minTime[i][j]);
            }
            System.out.println();
        }
    }
    
    List<LinkedHashSet<List<Integer>>> available(LinkedHashSet<List<Integer>> position){
        List<LinkedHashSet<List<Integer>>> availables = new ArrayList();
        Iterator<List<Integer>> it = position.iterator();
        List<Integer> head =  it.next();
        List<Integer> tail =  it.next();
        int headX = head.get(0);
        int headY = head.get(1);
        int tailX = tail.get(0);
        int tailY = tail.get(1);
        
        int xDiff = headX - tailX;
        int yDiff = headY - tailY;
        
        // System.out.printf("X_DIFF:%d, Y_DIFF:%d%n", xDiff, yDiff);
        // System.out.printf("ROTATE (%d,%d),(%d,%d)%n", headX, headY, tailX, tailY);
        
        // 頭を軸に時計回転
        LinkedHashSet<List<Integer>> headAxisClock = setOf(headX, headY, headX - yDiff, headY - xDiff);
        // System.out.printf("\tHEAD_AXIS_CLOCK (%d,%d),(%d,%d)%n", headX, headY, headX - yDiff, headY - xDiff);
        int[] crossHeadAxisClock = {tailX == headX ? headX-yDiff:tailX, tailY == headY?headY - xDiff:tailY};
        if(inRange(crossHeadAxisClock) && inRange(headAxisClock) && !visited.contains(headAxisClock)){
            visited.add(headAxisClock);
            availables.add(headAxisClock);
        }
        
        // 頭を軸に反時計回転
        LinkedHashSet<List<Integer>> headAxisCounterClock = setOf(headX, headY, headX + yDiff, headY + xDiff);
        // System.out.printf("\tHEAD_AXIS_COUNTER_CLOCK (%d,%d),(%d,%d)%n", headX, headY, headX + yDiff, headY + xDiff);
        int[] crossHeadAxisCounterClock = {tailX == headX ? headX + yDiff:tailX, tailY == headY?headY + xDiff:tailY};
        if(inRange(crossHeadAxisCounterClock) && inRange(headAxisCounterClock) && !visited.contains(headAxisCounterClock)){
            visited.add(headAxisCounterClock);
            availables.add(headAxisCounterClock);
        }
        
        //　尻尾を軸に時計回転
        LinkedHashSet<List<Integer>> tailAxisClock = setOf(tailX + yDiff, tailY + xDiff, tailX, tailY);
        // System.out.printf("\tTAIL_AXIS_CLOCK (%d,%d),(%d,%d)%n", tailX + yDiff, tailY + xDiff, tailX, tailY);
        int[] crossTailAxisClock = {headX == tailX ? tailX + yDiff : headX, headY == tailY ? tailY + xDiff:headY};
        if(inRange(crossTailAxisClock) && inRange(tailAxisClock) && !visited.contains(tailAxisClock)){
            visited.add(tailAxisClock);
            availables.add(tailAxisClock);
        }
        
        //　尻尾を軸に反時計回転
        LinkedHashSet<List<Integer>> tailAxisCounterClock = setOf(tailX - yDiff, tailY - xDiff, tailX, tailY);
        // System.out.printf("\tTAIL_AXIS_COUNTER_CLOCK (%d,%d),(%d,%d)%n", tailX - yDiff, tailY - xDiff, tailX, tailY);
        int[] crossTailAxisCounterClock = {headX == tailX ? tailX - yDiff : headX, headY == tailY ? tailY - xDiff:headY};
        // System.out.printf("\t  CROSS_TAIL_AXIS_COUNTER_CLOCK (%d,%d)%n", tailX | (tailX - yDiff),  tailY | (tailY - xDiff));
        if(inRange(crossTailAxisCounterClock) && inRange(tailAxisCounterClock) && !visited.contains(tailAxisCounterClock)){
            visited.add(tailAxisCounterClock);
            availables.add(tailAxisCounterClock);
        }
        
        int[] dx = {-1, 0,0,1};
        int[] dy = {0, -1,1,0};
        
        for(int i=0;i<4;i++){
            LinkedHashSet<List<Integer>> nextPos = setOf(Arrays.asList(head.get(0)+dx[i], head.get(1)+dy[i]), Arrays.asList(tail.get(0)+dx[i], tail.get(1)+dy[i]));
            if(!visited.contains(nextPos) && inRange(nextPos)){
                availables.add(nextPos);
                visited.add(nextPos);
            }
        }
        
        return availables;
    }
    
    boolean inRange(int[] pos){
        int x = pos[0];
        int y = pos[1];
        
        return (0<= x && x <n && 0<= y && y < m) && board[x][y] == 0;
    }
    
    boolean inRange(LinkedHashSet<List<Integer>> pos){
        Iterator<List<Integer>> it = pos.iterator();
        List<Integer> first = it.next();
        List<Integer> last = it.next();
        int headX = first.get(0);
        int headY = first.get(1);
        int tailX = last.get(0);
        int tailY = last.get(1);
        
        return (0<= headX && headX <n && 0<= headY && headY < m)
            && (0<= tailX && tailX <n && 0<= tailY && tailY < m)
            && board[headX][headY] == 0
            && board[tailX][tailY] == 0;
    }
    
    LinkedHashSet<List<Integer>> setOf(int x1,int y1,int x2, int y2){
        return setOf(Arrays.asList(x1,y1), Arrays.asList(x2,y2));
    }
    
    LinkedHashSet<List<Integer>> setOf(List<Integer>... e){
        return new LinkedHashSet(Arrays.asList(e));
    }
}