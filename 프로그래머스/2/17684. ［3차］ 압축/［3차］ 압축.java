import java.util.*;

class Solution {
    public int[] solution(String msg) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<String> dict = new ArrayList<>();
        
        for (int i = 0; i < 26; i++) {
            dict.add("" + (char)('A' + i));
        }
        
        int i = 0;
        while (i < msg.length()) {
            String w = msg.charAt(i) + "";
            int idx = i;
            
            while (idx + 1 < msg.length() && dict.contains(w + msg.charAt(idx + 1))) {
                w += msg.charAt(++idx);
            }
            
            result.add(dict.indexOf(w) + 1);
            
            if (idx + 1 < msg.length()) {
                dict.add(w + msg.charAt(idx + 1));
            }
            
            i = idx + 1;
        }
        
        int[] answer = new int[result.size()];
        for (int j = 0; j < result.size(); j++) {
            answer[j] = result.get(j);
        }
        
        return answer;
    }
}
