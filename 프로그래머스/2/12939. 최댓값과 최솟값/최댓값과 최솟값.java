class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] numbers = s.split(" ");
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        answer = min + " " + max;
        
        return answer;
    }
}
/*
조건
- 공백으로 구분된 숫자

요구
- 최댓값, 최솟값 찾아서 리턴
*/