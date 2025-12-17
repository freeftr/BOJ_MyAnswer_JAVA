class Solution {
    
    public int[] solution(String s) {
        int[] answer = new int[2];
        
        while (!s.equals("1")) {
            int n = s.length();
            
            s = s.replaceAll("0", "");
            
            int c = s.length();
            int diff = n - c;
            
            s = Integer.toBinaryString(c);
            answer[0]++;
            answer[1] += diff;
        }
        
        return answer;
    }
}

/*
조건
- 0과 1로 이루어진 문자열 X를 이진변환함.
1. x의 모든 0 제거
2. c = x의 길이, x = c를 2진법으로 표현한 문자열.

요구
- s가 1이 될때까지 이진변한 횟수와 제거된 0의 개수 구하기

풀이
- 재귀
*/