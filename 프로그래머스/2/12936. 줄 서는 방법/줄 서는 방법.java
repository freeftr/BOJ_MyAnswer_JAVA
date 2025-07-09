import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> candidate = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            candidate.add(i);
        }

        k--;
        
        for (int i = 0; i < n; i++) {
            long fact = factorial(n - i);
            int index = (int)(k / fact);
            answer[i] = candidate.get(index);
            candidate.remove(index);
            k %= fact;
        }

        return answer;
    }

    private long factorial(int a) {
        long result = 1;
        for (int i = 2; i <= a - 1; i++) {
            result *= i;
        }
        return result;
    }
}
