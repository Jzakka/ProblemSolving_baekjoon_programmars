import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[][] courses = new int[n][];

        for (int i = 0; i < n; i++) {
            courses[i] = getInts();
        }

        int[] roomNumbers = solution(courses);

        res.append(Arrays.stream(roomNumbers).max().getAsInt()).append("\n");
        for (int roomNumber : roomNumbers) {
            res.append(roomNumber).append("\n");
        }

        bw.write(res.toString());
        bw.close();
    }

    private static int[] solution(int[][] courses) {
        PriorityQueue<Room> rooms = new PriorityQueue<>();

        Arrays.sort(courses, Comparator.<int[]>comparingInt(course -> course[1]).thenComparingInt(course -> course[2]));

        int[] roomNumbers = new int[courses.length + 1];

        int roomNum = 1;
        for (int[] course : courses) {
            int courseNum = course[0];
            int start = course[1];
            int end = course[2];

            int selectedRoomNumber;
            if (rooms.isEmpty() || rooms.peek().endTime > start) {
                selectedRoomNumber = roomNum++;
                rooms.add(new Room(selectedRoomNumber, end));
            } else {
                Room endRoom = rooms.poll();
                Room updatedRoom = endRoom.addCourse(course);
                selectedRoomNumber = updatedRoom.roomNum;
                rooms.add(updatedRoom);
            }
            roomNumbers[courseNum] = selectedRoomNumber;
        }

        return Arrays.copyOfRange(roomNumbers, 1, roomNumbers.length);
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}

class Room implements Comparable<Room> {
    int roomNum;
    int endTime;

    public Room(int roomNum, int endTime) {
        this.roomNum = roomNum;
        this.endTime = endTime;
    }

    public Room addCourse(int[] course) {
        return new Room(roomNum, course[2]);
    }

    @Override
    public int compareTo(Room o) {
        return endTime - o.endTime;
    }
}