import java.util.*;
import java.util.stream.IntStream;

class Solution {
    Deque<Delivery> deliveries = new LinkedList<>();
    Deque<PickUp> pickUps = new LinkedList<>();

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        IntStream.range(0,n).forEach(idx->{
            if (deliveries[idx] > 0) {
                this.deliveries.addFirst(new Delivery(idx, deliveries[idx]));
            }
            if (pickups[idx] > 0) {
                this.pickUps.addFirst(new PickUp(idx, pickups[idx]));
            }
        });

        long accDist = 0;

        while (!(this.deliveries.isEmpty() && this.pickUps.isEmpty())) {
            int maxGone = deliver(cap);
            maxGone = Math.max(maxGone, pickup(cap));
            accDist += 2*maxGone;

//            System.out.printf("maxGone:%d%n", maxGone);
        }

        return accDist;
    }

    private int pickup(int cap) {
        int maxGone = -1;
        while (!this.pickUps.isEmpty() && cap > 0) {
            PickUp front = this.pickUps.peekFirst();
            maxGone = Math.max(maxGone, front.idx + 1);
            if (cap >= front.pickup) {
                cap -= front.pickup;
                this.pickUps.pollFirst();
            } else {
                front.pickup -= cap;
                cap = 0;
            }
        }

        return maxGone;
    }

    private int deliver(int load) {
        int maxGone = -1;

        while (!this.deliveries.isEmpty() && load > 0) {
            Delivery front = this.deliveries.peekFirst();
            maxGone = Math.max(maxGone, front.idx + 1);
            if (load >= front.delivery) {
                load -= front.delivery;
                this.deliveries.pollFirst();
            } else {
                front.delivery -= load;
                load = 0;
            }
        }

        return maxGone;
    }

    class Delivery {
        int idx;
        int delivery;

        public Delivery(int idx, int delivery) {
            this.idx = idx;
            this.delivery = delivery;
        }
    }

    class PickUp {
        int idx;
        int pickup;

        public PickUp(int idx, int pickup) {
            this.idx = idx;
            this.pickup = pickup;
        }
    }
}