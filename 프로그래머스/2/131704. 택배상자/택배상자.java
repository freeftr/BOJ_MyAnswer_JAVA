import java.io.*;
import java.util.*;

// 보조 컨테이너 == 스택
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> sub = new Stack<>();
        int idx = 0;
        for (int i = 0; i < order.length; i++) {
            sub.add(i+1);
            
            while(!sub.isEmpty() && sub.peek() == order[idx]) {
                sub.pop();
                idx++;
            }
        }
        return idx;
    }
}