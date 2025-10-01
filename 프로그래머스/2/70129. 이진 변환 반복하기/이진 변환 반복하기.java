class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        String a = s;
        
        while (!a.equals("1")) {
            int zero = 0;
            
            for (int i = 0; i < a.length(); i++) {
                if (a.charAt(i) == '0') {
                    zero++;
                }
            }
            
            int c = a.length() - zero;
            a = Integer.toBinaryString(c);
            
            answer[0]++;
            answer[1] += zero;
        }
        
        return answer;
    }
}

/*
조건
- x의 모든 0을 제거
- x의 길이로 이진수 만들기.

요구
- s가 1이 될 때까지 반복.
- 이진변환 횟수 + 0의 개수
*/