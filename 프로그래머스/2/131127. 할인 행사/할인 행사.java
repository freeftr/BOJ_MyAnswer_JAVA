import java.io.*;
import java.util.*;

class Solution {

    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<String, Integer> wish = new HashMap<>();

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        for (int i = 0; i < want.length; i++) {
            wish.put(want[i], number[i]);
        }

        int left = 0;
        int right = 9;

        for (int i = 0; i < 10; i++) {
            map.put(discount[i], map.getOrDefault(discount[i], 0) + 1);
        }

        if (check()) {
            answer++;
        }

        while (right + 1 < discount.length) {
            map.put(discount[left], map.get(discount[left]) - 1);
            if (map.get(discount[left]) == 0) {
                map.remove(discount[left]);
            }
            left++;
            right++;
            map.put(discount[right], map.getOrDefault(discount[right], 0) + 1);

            if (check()) {
                answer++;
            }
        }

        return answer;
    }

    static boolean check() {
        for (String key : wish.keySet()) {
            if (map.getOrDefault(key, 0) < wish.get(key)) {
                return false;
            }
        }
        return true;
    }
}
