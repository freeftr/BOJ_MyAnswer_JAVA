import java.io.*;
import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int length = name.length();
        int idx = 0;
        
        for (int i = 0; i < length; i++) {
            answer += Math.min(Math.abs(name.charAt(i) - 'A'), Math.abs('Z' - name.charAt(i) + 1));
        }
        
        int cnt = length - 1;
        for (int i = 0; i < length; i++) {
            int next = i + 1;
            
            while(next < length && name.charAt(next) == 'A'){
                next++;
            }
            
            cnt = Math.min(cnt, Math.min(length - next, i) + i + length - next);
        }
        
        return answer + cnt;
    }
    
    static boolean check(char[] arr, String target) {
        for (int i = 0; i < target.length(); i++) {
            if (arr[i] != target.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    
    //ABCDEFGHIJKLMNOPQRSTUVWXYZ
    // 14
    // 13
}