import java.util.*;

class Solution {
    TreeMap<Integer, Integer> treeMap = new TreeMap<>((a, b) -> b - a);

    public long solution(int n, int[] works) {
        Arrays.stream(works)
                .boxed()
                .sorted((a, b) -> b - a)
                .forEach(num -> {
                    add(treeMap, num, 1);
                });
        treeMap.put(0, 0);


        while (n > 0 && treeMap.size() >= 2) {
            n = flat(n);
        }

        return result();
    }

    private long result() {
        long sqaureSum = 0;
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            sqaureSum += (long)entry.getKey() * entry.getKey() * entry.getValue();
        }
        return sqaureSum;
    }

    private int flat(int n) {
        // 가장 큰 값과 두번째로 큰 값의 차이 * 가장 큰 값의 개수 는 지워야할 개수
        Iterator<Map.Entry<Integer, Integer>> iterator = treeMap.entrySet().iterator();
        Map.Entry<Integer, Integer> first = iterator.next();
        Map.Entry<Integer, Integer> second = iterator.next();

        int needToDelete = (first.getKey() - second.getKey()) * first.getValue();

        // 지워야 할 개수가 n보다 작으면 다 지우고 두번째로 큰 값의 개수에 지운 개수를 더한다.
        if (needToDelete <= n) {
            add(treeMap, second.getKey(), first.getValue());
            add(treeMap, first.getKey(), -first.getValue());

            n -= needToDelete;
        }

        // 지워야 할 개수가 n보다 크면 n을 개수만큼 나눔. r개수는 q+1만큼 지우고, 나머지는 q만큼 지움
        else {
            int q = n / first.getValue();
            int r = n - first.getValue() * q;

            if (q > 0) {
                add(treeMap, first.getKey() - q, first.getValue() - r);
            }
            add(treeMap, first.getKey() - (q + 1), r);

            if (q == 0) {
                add(treeMap, first.getKey(), -r);
            } else {
                treeMap.put(first.getKey(), 0);
            }

            n = 0;
        }
        if (first.getValue() == 0) {
            treeMap.remove(first.getKey());
        }

        return n;
    }

    private void add(Map<Integer, Integer> map, Integer key, Integer delta) {
        if (delta == 0) {
            return;
        }
        if (map.containsKey(key)) {
            map.put(key, map.get(key) + delta);
        } else {
            map.put(key, delta);
        }
    }
}