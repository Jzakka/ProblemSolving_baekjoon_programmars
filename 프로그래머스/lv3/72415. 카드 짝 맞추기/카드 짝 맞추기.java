import java.util.*;

class Solution {
    Map<Integer, List<int[]>> cardPos = new HashMap();

    public int solution(int[][] board, int r, int c) {
        Set<Integer> cards = new TreeSet();

        int cardCnt = 0;
        for(int i = 0; i< 4;i++){
            for(int j = 0;j<4;j++){
                int cell = board[i][j];
                if(cell != 0){
                    cards.add(cell);
                    addMap(cardPos, cell, i,j);
                    cardCnt++;
                }
            }
        }

        int[] cardsKind = cards.stream().mapToInt(Integer::intValue).toArray();

        int res = Integer.MAX_VALUE;

        do{
            int play = play(board, r, c, cardsKind);
            res = Math.min(res, play);
        }while(nextPermutation(cardsKind));

        return res+cardCnt;
    }

    private void addMap(Map<Integer, List<int[]>> cardPos, int card, int i, int j) {
        if (cardPos.containsKey(card)) {
            cardPos.get(card).add(new int[]{i, j});
        } else {
            List<int[]> positions = new ArrayList<>();
            positions.add(new int[]{i, j});
            cardPos.put(card, positions);
        }
    }

    int play(int[][] originalBoard, int r, int c, int[] removeOrdered){
        int[][] board = new int[4][4];
        for(int i = 0;i<4;i++){
            for(int j = 0;j<4;j++){
                board[i][j] = originalBoard[i][j];
            }
        }

        return remove(board, r, c, 0, removeOrdered, 0, false);
    }

    int remove(int[][] board, int curX, int curY, int removeIdx, int[] removeOrdered, int keyCnt, boolean haveCard){
        if(removeIdx == removeOrdered.length){
            return keyCnt;
        }
        board[curX][curY] = 0;
        // 카드를 가지고 있는 경우 쌍이되는 카드의 위치로 이동
        if(haveCard){
            int card = removeOrdered[removeIdx];
            int[] nextPos = cardPos.get(card).get(0);
            int dist = minDist(board, curX, curY, nextPos);

            // 방문한 카드 삭제
            cardPos.get(card).remove(nextPos);

            int res =  remove(board, nextPos[0], nextPos[1], removeIdx+1, removeOrdered, keyCnt+ dist, false);
            // 방문한 카드 복구
            cardPos.get(card).add(nextPos);

            board[curX][curY] = card;
            return res;
        }

        // 카드가 없는 경우 다음 순서의 카드 2개를 방문
        int card = removeOrdered[removeIdx];
        int res = Integer.MAX_VALUE;

        // 첫번째 카드 방문
        int[] nextPos = cardPos.get(card).get(0);
        int dist = minDist(board, curX, curY, nextPos);

        cardPos.get(card).remove(nextPos);
        res = Math.min(
                res,
                remove(board, nextPos[0], nextPos[1], removeIdx, removeOrdered, keyCnt + dist, true)
        );
        cardPos.get(card).add(nextPos);

        // 두번째 카드 방문
        nextPos = cardPos.get(card).get(0);
        dist = minDist(board, curX, curY, nextPos);

        cardPos.get(card).remove(nextPos);
        res = Math.min(
                res,
                remove(board, nextPos[0], nextPos[1], removeIdx, removeOrdered, keyCnt+dist, true)
        );
        cardPos.get(card).add(nextPos);

        board[curX][curY] = removeOrdered[removeIdx];
        return res;
    }

    int minDist(int[][] board, int curX, int curY, int[] pos){
        // 목표지점까지 가는 최소 거리 계산
        if(curX == pos[0] && curY == pos[1]){
            return 0;
        }

        Queue<int[]> Q = new LinkedList();
        Q.add(new int[]{curX, curY});
        boolean[][] visited = new boolean[4][4];
        visited[curX][curY] = true;

        int dist = 1;
        while(!Q.isEmpty()){
            int qLen  = Q.size();
            for(int i=0;i<qLen;i++){
                int[] polled = Q.poll();

                List<int[]> positions = getPositions(board, polled, visited);

                for(int[] position: positions){
                    if(position[0] == pos[0] && position[1] == pos[1]){
                        return dist;
                    }
                }

                Q.addAll(positions);
            }
            dist++;
        }
        return -1;
    }

    List<int[]> getPositions(int[][] board, int[] pos, boolean[][] visited){
        int[] dx = {-1, 0, 0, 1};
        int[] dy = {0, -1, 1, 0};

        List<int[]> positions = new ArrayList();

        for(int i=0;i<4;i++){
            int[] smallMoved = {pos[0] + dx[i], pos[1]+dy[i]};

            if(isAvailable(smallMoved, visited)){
                visited[smallMoved[0]][smallMoved[1]] = true;
                positions.add(smallMoved);
            }

            // Ctrl + 방향키로 움직인 결과
            int[] ctrlMoved = getCtrlMoved(board, pos, dx[i], dy[i]);

            if(isAvailable(ctrlMoved, visited)){
                visited[ctrlMoved[0]][ctrlMoved[1]] = true;
                positions.add(ctrlMoved);
            }
        }

        return positions;
    }

    boolean isAvailable(int[] pos, boolean[][] visited){
        return pos[0] < 4 && pos[0] >= 0 && pos[1] < 4 && pos[1] >= 0
                && !visited[pos[0]][pos[1]];
    }

    int[] getCtrlMoved(int[][] board, int[] pos, int dx, int dy){
        int i = 1;
        while(pos[0] + dx*i < 4 && pos[0] + dx*i >= 0 &&
                pos[1] + dy*i < 4 && pos[1] + dy*i >= 0 &&
                board[pos[0] + dx*i][pos[1]+dy*i] == 0){
            i++;
        }

        if(pos[0] + dx*i < 4 && pos[0] + dx*i >= 0 && pos[1] + dy*i < 4 && pos[1] + dy*i >= 0){
            return new int[]{pos[0] + dx*i, pos[1] + dy*i};
        }

        return new int[]{pos[0] + dx*(i-1), pos[1] + dy*(i-1)};
    }

    boolean nextPermutation(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            if(arr[i] > arr[i-1]){
                int curSwapNum = Integer.MAX_VALUE;
                int replacePos = -1;

                for(int j = arr.length-1;j>=i;j--){
                    if(arr[i-1] < arr[j] && arr[j] < curSwapNum){
                        curSwapNum = arr[j];
                        replacePos = j;
                    }
                }

                int temp = arr[i-1];
                arr[i-1] = arr[replacePos];
                arr[replacePos] = temp;
                Arrays.sort(arr, i, arr.length);
                return true;
            }
        }
        return false;
    }

    void print(int[] arr){
        for(int num:arr){
            System.out.print(num+",");
        }
        System.out.println();
    }
}