class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int a = 1;
        int b = 1;
        
        while (a<=b) {
            int result = (a + b) * (b - a + 1) / 2;
            
            if (result == n) {
                answer++;
                a++;
                continue;
            } else if (result < n) {
                b++;
                continue;
            } else {
                a++;
            }
        }
        return answer;
    }
}

/*
n을 연속된 자연수의 합으로 구하는 경우의 수

풀이
- 투포인터?
- 연속된 수의 합 = (a + b) * (b - a + 1) / 2;
*/