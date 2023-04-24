import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        Stack<Integer> ingredients = new Stack<>();

        int burgers = 0;
        for (int i : ingredient) {
            ingredients.push(i);
            while (containsBurger(ingredients)) {
                burgers++;
                ingredients.pop();
                ingredients.pop();
                ingredients.pop();
                ingredients.pop();
            }
        }

        return burgers;
    }

    private boolean containsBurger(Stack<Integer> ingredients) {
        if (ingredients.size() < 4) {
            return false;
        }

        List<Integer> combination = ingredients.subList(ingredients.size() - 4, ingredients.size());
        if (isBurger(combination)) {
            return true;
        }
        return false;
    }

    private boolean isBurger(List<Integer> combination) {
        return combination.get(0) == 1 && combination.get(1) == 2
                && combination.get(2) == 3 && combination.get(3) == 1;
    }
}