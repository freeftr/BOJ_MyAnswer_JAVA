class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int candidate = n + 1;
        int one = countOne(n);
        
        while (true) {
            if (countOne(candidate) == one) {
                answer = candidate;
                break;
            } else {
                candidate++;
            }
        }
        
        answer = candidate;
        
        return answer;
    }
    
    static int countOne(int num) {
        String s = Integer.toBinaryString(num);
        
        int result = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                result++;
            }
        }
        
        return result;
    }
}

/*
조건
- n보다 큰 자연수
- 이진수로 변환 시 n과 1의 개수가 동일
- 위를 만족하는 가장 작은 수

요구
- 다음 큰 숫자 찾아라

풀이
- 이진수로 바꾸고 0을 앞에다가 넣으면 되는거 아닌가
- 문자열에 0이 없으면 -> 맨 앞 1 뒤에 0 추가
- 문자열에 0이 있으면 -> 0인 곳 다음이 1이면 땅겨옴.
*/