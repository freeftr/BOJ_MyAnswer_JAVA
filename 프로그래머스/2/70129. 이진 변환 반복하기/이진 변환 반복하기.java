class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        StringBuilder sb = new StringBuilder();
        
        while (!s.equals("1")) {
            answer[0]++;
            int before = s.length();
            s = s.replaceAll("0", "");
            answer[1] += before - s.length();
            int c = s.length();
            s = Integer.toBinaryString(c);
        }
                
        return answer;
    }
}

/*
이진 변환
1. 0 모두 제거
2. c = 길이 x를 c의 이진법으로 바꾸기
3. 1이 될 때까지 반복

요구
- 변환 횟수와 제거된 0의 개수 구하기

풀이
1. "0" replace
*/