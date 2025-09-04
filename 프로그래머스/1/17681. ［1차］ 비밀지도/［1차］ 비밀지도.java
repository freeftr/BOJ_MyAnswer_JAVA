import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String[] s1 = new String[n];
        String[] s2 = new String[n];
        
        for (int a = 0; a < n; a++) {
            String t1 = Integer.toBinaryString(arr1[a]);
            String t2 = Integer.toBinaryString(arr2[a]);
            
            while (t1.length() < n) t1 = "0" + t1;
            while (t2.length() < n) t2 = "0" + t2;
            
            s1[a] = t1;
            s2[a] = t2;
        }
        
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (s1[i].charAt(j) == '1' || s2[i].charAt(j) == '1') {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            answer[i] = sb.toString();
        }
        
        return answer;
    }
}
