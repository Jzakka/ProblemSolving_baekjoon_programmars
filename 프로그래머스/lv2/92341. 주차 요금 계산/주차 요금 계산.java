
import java.util.*;

class Solution {
    int basicTime;
    int basicFee;
    int unitTime;
    int unitFee;
    Map<Integer, Car> cars = new TreeMap<>();
    public int[] solution(int[] fees, String[] records) {
        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];

        for (String record : records) {
            update(record);
        }

        cars.values().stream().filter(car -> !car.inTime.empty()).forEach(car -> car.parkingTime+=60 * 24 - 1 - car.inTime.peek());

        for (Map.Entry<Integer, Car> entry : cars.entrySet()) {
            entry.getValue().calcFee();
        }

        return cars.values().stream().mapToInt(car -> car.fee).toArray();
    }

    private void update(String recordStr) {
        Record record = parse(recordStr);

        if (!cars.containsKey(record.number)) {
            cars.put(record.number, new Car(record.number));
        }

        cars.get(record.number).addTime(record.time);
    }

    private Record parse(String record) {
        String[] datum = record.split(" ");
        int minutes = timeToMinute(datum[0]);
        return new Record(minutes, Integer.parseInt(datum[1]), datum[2]);
    }

    private int timeToMinute(String time) {
        String[] timeData = time.split(":");
        return Integer.parseInt(timeData[0]) * 60 + Integer.parseInt(timeData[1]);
    }

    class Record{
        int time;
        int number;
        String direction;

        public Record(int time, int number, String direction) {
            this.time = time;
            this.number = number;
            this.direction = direction;
        }
    }

    class Car{
        int number;
        Stack<Integer> inTime = new Stack<>();

        int parkingTime;
        int fee;

        public Car(int number) {
            this.number = number;
        }

        public void addTime(int time) {
            if (inTime.empty()) {
                inTime.push(time);
            } else {
                parkingTime += time - inTime.peek();
                inTime.pop();
            }
        }

        public void calcFee() {
            fee = basicFee;
            if (parkingTime > basicTime) {
                int exceedTime = parkingTime - basicTime;
                fee += Math.ceil((double) exceedTime / unitTime) * unitFee;
            }
        }
    }
}