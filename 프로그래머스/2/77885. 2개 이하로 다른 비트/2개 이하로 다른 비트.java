class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long num = numbers[i];
            
            if (num % 2 == 0) {
                answer[i] = num + 1;
            } else {
                String s = "0" + Long.toBinaryString(num);
                char[] chars = s.toCharArray();
                
                for (int j = chars.length - 1; j > 0; j--) {
                    if (chars[j - 1] == '0' && chars[j] == '1') {
                        chars[j - 1] = '1';
                        chars[j] = '0';
                        break;
                    }
                }
                
                answer[i] = Long.parseLong(new String(chars), 2);
            }
        }
        
        return answer;
    }
}
