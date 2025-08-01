import java.io.*;
import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        int length = numbers.length;
        String[] strs = new String[length];
        for (int i = 0; i < length; i++) {
            strs[i] = Integer.toString(numbers[i]);
        }
                
        Arrays.sort(strs, (a,b) -> (b + a).compareTo(a + b));
        if (strs[0].equals("0")) return "0";

        for (String s : strs) {
            answer += s;
        }
        return answer;
    }
}