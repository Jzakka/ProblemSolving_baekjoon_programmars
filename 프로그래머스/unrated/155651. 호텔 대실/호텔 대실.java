import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        List<Room> rooms = new ArrayList<>();
        List<Reservation> reserveData = new ArrayList<>();
        for (String[] res : book_time) {
            reserveData.add(new Reservation(res[0], res[1]));
        }
        Collections.sort(reserveData);

        rooms.add(new Room(reserveData.get(0)));

        for (int i = 1; i < reserveData.size(); i++) {
            Reservation reservation = reserveData.get(i);
            if (noRoom(rooms, reservation)) {
                rooms.add(new Room(reservation));
            }
        }
        return rooms.size();
    }

    private boolean noRoom(List<Room> rooms, Reservation reservation) {
        for (Room room : rooms) {
            if (room.acceptable(reservation)) {
                room.accept(reservation);
                return false;
            }
        }
        return true;
    }

    class Room{
        TreeSet<Reservation> reservations = new TreeSet<>();

        public Room(Reservation first) {
            reservations.add(first);
        }

        public boolean acceptable(Reservation reservation) {
            for (Reservation reserved : reservations) {
                if (reserved.overlap(reservation)) {
                    return false;
                }
            }
            return true;
        }

        public void accept(Reservation reservation) {
            reservations.add(reservation);
        }
    }

    private class Reservation implements Comparable<Reservation> {
        int start;
        int end;

        public Reservation(String s, String e) {
            start = parse(s);
            end = parse(e);
        }

        private int parse(String s) {
            String[] times = s.split(":");
            return Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
        }

        public boolean overlap(Reservation reservation) {
            return !(reservation.end + 10 <= this.start || this.end + 10 <= reservation.start);
        }

        @Override
        public int compareTo(Reservation o) {
            return this.start - o.start;
        }
    }
}