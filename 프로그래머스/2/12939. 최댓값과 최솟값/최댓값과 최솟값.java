import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        String[] words = s.split(" ");
        int[] nums = new int[words.length];
        
        for (int i = 0; i < words.length; i++) {
            nums[i] = Integer.parseInt(words[i]);
        }
        
        Arrays.sort(nums);
        return nums[0] + " " + nums[nums.length - 1];
    }
}