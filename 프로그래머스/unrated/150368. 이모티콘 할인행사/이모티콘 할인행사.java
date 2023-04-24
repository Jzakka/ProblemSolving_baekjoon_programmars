import java.util.*;
import java.util.stream.Collectors;

class Solution {
    List<User> userList;

    int[] res = {0, 0};
    public int[] solution(int[][] users, int[] emoticons) {
        userList = Arrays.stream(users).map(User::new).collect(Collectors.toList());
        Emoticon[] emoticonArr = Arrays.stream(emoticons)
                .mapToObj(e -> new Emoticon(e, 0))
                .toArray(Emoticon[]::new);

        recursive(0, emoticonArr);

        return res;
    }

    public void recursive(int idx, Emoticon[] emoticons) {
        if (idx == emoticons.length) {
            int[] temp = {0, 0};
            for (User user : userList) {
                int totalPayment = Arrays.stream(emoticons)
                        .filter(e -> e.rate >= user.minRate)
                        .mapToInt(Emoticon::getDiscountedPrice)
                        .sum();
                if (user.maxMoney <= totalPayment) {
                    temp[0]++;
                } else {
                    temp[1] += totalPayment;
                }
            }
            if (temp[0] > res[0]) {
                res = temp;
            } else if (temp[0] == res[0] && temp[1] >= res[1]) {
                res = temp;
            }
            return;
        }

        for (int i = 10; i <= 40; i += 10) {
            emoticons[idx].rate = i;
            recursive(idx+1, emoticons);
        }
    }

    class Emoticon {
        long price;
        int rate;

        public Emoticon(int price, int rate) {
            this.price = price;
            this.rate = rate;
        }

        public int getDiscountedPrice() {
            return (int)(price * (100 - rate) / 100);
        }
    }

    class User {
        int minRate;
        int maxMoney;

        public User(int[] user) {
            minRate = user[0];
            maxMoney = user[1];
        }
    }
}