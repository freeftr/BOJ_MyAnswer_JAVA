import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        List<Integer> left = new ArrayList<>();
        left.add(a[0]);

        for (int i = 1; i < a.length; i++) {
            left.add(Math.min(a[i], left.get(i - 1)));
        }

        List<Integer> right = new ArrayList<>(Collections.nCopies(a.length, 0));
        right.set(a.length - 1, a[a.length - 1]);

        for (int i = a.length - 2; i >= 0; i--) {
            right.set(i, Math.min(a[i], right.get(i + 1)));
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] > left.get(i) && a[i] > right.get(i)) continue;
            answer++;
        }

        return answer;
    }
}
