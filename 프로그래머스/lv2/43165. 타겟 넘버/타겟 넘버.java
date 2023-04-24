class Solution {
    private int count = 0;
    private int target;
    private int[] numbers;

    public int solution(int[] numbers, int target) {
        this.target = target;
        this.numbers = numbers;

        calc(0, 0);

        return count;
    }

    private void calc(int acc, int idx) {
        if (idx == numbers.length) {
            if (acc == target) {
                count++;
            }
            return;
        }
        calc(acc + numbers[idx], idx + 1);
        calc(acc - numbers[idx], idx + 1);
    }
}