import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        return Arrays.stream(files).map(File::new).sorted().map(File::toString).toArray(String[]::new);
    }

    class File implements Comparable<File>{
        String head;
        Integer number;
        String tail;
        String fileName;

        public File(String fileName) {
            this.fileName = fileName;
            int i = 0;
            while (i < fileName.length() && !Character.isDigit(fileName.charAt(i))) {
                i++;
            }
            head = fileName.substring(0, i).toLowerCase();
            int numberStart = i;
            while (i < fileName.length() && Character.isDigit(fileName.charAt(i))) {
                i++;
            }
            number = Integer.parseInt(fileName.substring(numberStart, i));
            if (i < fileName.length()) {
                tail = fileName.substring(i);
            } else {
                tail = "";
            }
        }

        @Override
        public int compareTo(File o) {
            if (this.head.equals(o.head)) {
                return this.number - o.number;
            }
            return this.head.compareTo(o.head);
        }

        @Override
        public String toString() {
            return fileName;
        }
    }
}