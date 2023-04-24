import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(int n) {
        int size = 1;
        int offset = 0;
        ArrayList<Integer> numbers = new ArrayList<>();
        int index;
        while ((index = (n - 1) - offset) >= 0) {
            int append = index % (3 * size) / size;
            numbers.add(convert(append));
            size *= 3;
            offset += size;
        }
        Collections.reverse(numbers);
        return numbers.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private Integer convert(int append) {
        if (append == 0) {
            return 1;
        }
        if (append == 1) {
            return 2;
        }
        if (append == 2) {
            return 4;
        }
        return 0;
    }
}

//
//    1
//    2
//    4
//   11
//   12
//   14
//   21
//   22
//   24
//   41
//   42
//   44
//  111
//  112
//  114
//  121
//  122
//  124

// size  ={1,3,9,27,...}
//offset ={0,3,12,39, ...}
// 18?==
//                012
// ((18-1)-0)%(1*3)/1
// ((18-1)-3)%(3*3)/3 = 14%9/3=1
// ((n-1)-offset)%(3*size)/size=5%27/27=0