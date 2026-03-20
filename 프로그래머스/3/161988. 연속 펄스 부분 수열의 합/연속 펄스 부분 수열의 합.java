class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        long max2 = Integer.MIN_VALUE;
        long max1 = Integer.MIN_VALUE;
        long sum1 = 0;
        long sum2 = 0;
        int N = sequence.length;
        
        for (int i = 0; i < N; i++) {
            int sign = i % 2 == 0 ? 1 : -1;
            
            int val1 = sequence[i] * sign;
            int val2 = sequence[i] * -sign;
            
            sum1 = Math.max(val1, val1 + sum1);
            sum2 = Math.max(val2, val2 + sum2);
            
            max1 = Math.max(max1, sum1);
            max2 = Math.max(max2, sum2);
        }
        return Math.max(max1, max2);
    }
}

/*
조건
- 연속 부분 수열에 같은 길이의 펄수 수열을 원소기리 곱함
- 펄스 수열 : 1, -1 번갈아 나오는 수열

요구
- 연속 펄스 부분 수열 중 합이 가장 큰 것

풀이
- 짝수부 홀수부 나눠서 누적합?

2 3 -6 1 3 -1 2 4
2 -6 3 2
3 1 -1 4
*/