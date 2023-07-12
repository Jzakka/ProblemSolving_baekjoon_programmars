import java.util.*;

class Solution {
    class Pos{
        int x,y;
        Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        int distance(Pos p){
            return Math.abs(x-p.x) + Math.abs(y-p.y);
        }
        
        @Override
        public boolean equals(Object o){
            Pos p = (Pos)o;
            return x==p.x && y==p.y;
        }
    }
    int n,m;
    int totalLocker;
    public int solution(int n, int m, int[][] timetable) {
        this.n = n;
        this.m = m;
        this.totalLocker = n*n;
        
        Arrays.sort(timetable, (t1,t2)->{
           if(t1[0] == t2[0]) {
               return t1[1] - t2[1];
           }
            return t1[0] - t2[0];
        });
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((t1,t2)->{
            if(t1[1] == t2[1]) {
               return t1[0] - t2[0];
            }
            return t1[1] - t2[1];
        });
        
        int maxPeople = 0;
        for(int[] time:timetable){
            if(heap.isEmpty()){
               heap.add(time);
            }else{
                while(!heap.isEmpty() && heap.peek()[1] < time[0]){
                    heap.poll();
                }
                heap.add(time);
            }
            maxPeople = Math.max(maxPeople, heap.size());
        }
        
        return farDist(maxPeople);
    }
    
    int farDist(int people){
        if(people == 1){
            return 0;
        }
        // 체스판으로 할당 된 경우 무조건 거리는 2
        if(people == (totalLocker+1)/2){
            return 2;
        }
        // 체스판보다 할당량이 많으면 거리는 무조건 1
        if(people > (totalLocker+1)/2){
            return 1;
        }
        
        // 최대거리에서 거리를 줄여나가면서 최대 할당 락커수가 인원수랑 같으면 거리 반환
        for(int dist = 2*(n-1);dist>=1;dist--){
            int maxAllocate = allocate(dist);
            if(maxAllocate >= people){
                return dist;
            }
        }
        return -1;
    }
    
    int allocate(int dist){
        int maxCnt = 0;
        
        // 처음 할당할 맨 윗 행의 락커의 시작점
        for(int ty = 0;ty<n;ty++){
            List<Pos> positions = new ArrayList(Arrays.asList(new Pos(0,ty)));
            
            for(int i = 0;i<n;i++){
                for(int j=0;j<n;j++){
                    Pos pos = new Pos(i,j);
                    if(further(positions, pos, dist)){
                        positions.add(pos);
                    }
                }
            }
            
            maxCnt = Math.max(maxCnt, positions.size());
        }
        
        return maxCnt;
    }
    
    boolean further(List<Pos> positions, Pos pos, int dist){
        for(Pos allocated:positions){
            if(allocated.distance(pos) < dist){
                return false;
            }
        }
        return true;
    }
}