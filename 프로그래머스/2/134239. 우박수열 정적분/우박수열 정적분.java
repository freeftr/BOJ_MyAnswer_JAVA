import java.util.*;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        ArrayList<Integer> result = new ArrayList<>();

        while (k > 1) {
            result.add(k);
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = k * 3 + 1;
            }
        }
        
        result.add(1);
        
        double total = 0;
        
        for (int i = 1; i < result.size(); i++) {
            int left = result.get(i - 1);
            int right = result.get(i);
                
            total += Math.min(left, right);
                
            total += Math.abs(left - right) / 2.0;
        }
        
        int n = result.size() - 1;
        
        int idx = 0;

        for (int[] range : ranges) {
            int x = range[0];
            int y = n + range[1];
            
            if (x == y) {
                answer[idx++] = 0.0;
                continue;
            }
            
            if (x > y) {
                answer[idx++] = -1;
                continue;
            }
            
            if (x == 0 && y == 0) {
                answer[idx++] = total;
                continue;
            }
            
            double sum = 0;
            
            for (int i = x + 1; i <= y; i++) {
                int left = result.get(i - 1);
                int right = result.get(i);
                
                sum += Math.min(left, right);
                
                sum += Math.abs(left - right) / 2.0;
            }
            
            answer[idx++] = sum;
        }
        
        return answer;
    }
}

/*
조건
- 콜라츠 추측 = 다음을 반복하면 1이 댐.
1-1 짝수면 2로 나누기
1-2 홀수면 3 곱하고 1 더하기
2 1보다 크면 1번 반복.
- -b = n - b
*/