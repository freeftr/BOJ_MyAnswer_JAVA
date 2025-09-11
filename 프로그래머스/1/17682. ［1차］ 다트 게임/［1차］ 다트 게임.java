import java.util.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        int[] nums = new int[3];
        int idx = 0;
        
        for (int i = 0; i < dartResult.length(); i++) {
            if ('S' == dartResult.charAt(i) || dartResult.charAt(i) == 'D' || dartResult.charAt(i) == 'T') {
                String temp = dartResult.charAt(i - 1) + "";
                for (int j = i - 2; j >= 0; j++) {
                    if ('0' <= dartResult.charAt(j) && dartResult.charAt(j) <= '9') {
                        temp = dartResult.charAt(j) + temp;
                    } else {
                        break;
                    }
                }
                int base = Integer.parseInt(temp);
                String type = dartResult.charAt(i) + "";
                String prize = "";
                
                if (i != dartResult.length() - 1 && (dartResult.charAt(i + 1) == '*' || dartResult.charAt(i + 1) == '#')) {
                    prize = dartResult.charAt(i + 1) + "";
                }
                
                if (type.equals("D")) {
                    base = (int) Math.pow(base, 2);
                }
                
                if (type.equals("T")) {
                    base = (int) Math.pow(base, 3);
                }
                
                if (prize.equals("*")) {
                    if (idx != 0) {
                        nums[idx - 1] *= 2;
                    } 
                    base *= 2;
                }
                
                if (prize.equals("#")) {
                    base *= -1;
                }
                
                nums[idx] = base;
                idx++;
            }
        }
        
        for (int num : nums) {
            answer += num;
        }
        return answer;
    }
}