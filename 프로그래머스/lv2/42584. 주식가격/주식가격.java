import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Price[] priceArr = new Price[prices.length];
        for (int i = 0; i < prices.length; i++) {
            priceArr[i] = new Price(prices[i], i);
        }

        int[] result = NSE(priceArr);

        for (int i = 0; i < result.length; i++) {
            result[i] -= i;
        }

        return result;
    }

    //nextSmallerElement's index
    int[] NSE(Price[] prices) {
        int[] nse = new int[prices.length];
        Stack<Price> stk = new Stack<>();

        for (int i = prices.length - 1; i >= 0; i--) {
            Price price = prices[i];

            while (!stk.empty() && price.value <= stk.peek().value) {
                stk.pop();
            }

            if (stk.empty()) {
                nse[i] = prices.length - 1;
            } else {
                nse[i] = stk.peek().idx;
            }
            stk.push(price);
        }
        return nse;
    }

    class  Price{
        int value;
        int idx;

        public Price(int value, int idx) {
            this.value = value;
            this.idx = idx;
        }
    }
}

// 0 1 2 3 4
// 1 2 3 2 3
// X X 3 X X        오른쪽에서 처음으로 나보다 작은 게 있는 인덱스
// 4 3 1 1 0

// stk
// 1

// res
// X X 3 X X