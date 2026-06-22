import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        
        String[] temp = s.split(" ");
        int[] arr = new int[temp.length];
        
        for (int i = 0; i < temp.length; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }
        
        Arrays.sort(arr);
        
        answer = arr[0] + " " + arr[temp.length - 1];
        
        return answer;
    }
}