import java.util.*;

class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);

        for (int h = citations.length; h > 0; h--) {
            if (citations[citations.length - h]>=h) {
                return h;
            }
        }
        return 0;
    }

    //0 1 3 5 6
    //n개 중 h개는 h이상의 원소
    //n-h개는 h이하의 원소
    //arr[n-h] >= h이어야함
}