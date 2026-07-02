import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        
        HashSet<String> set = new HashSet<>();
        
        int idx = 1;
        String prev = "";
        
        for (String word : words) {
            if (set.contains(word) || (!prev.equals("") && prev.charAt(prev.length() - 1) != word.charAt(0))) {
                answer[0] = idx % n == 0 ? n : idx % n;
                answer[1] = idx % n == 0 ? idx / n : idx / n + 1;
                break;
            }
            
            idx++;
            prev = word;
            
            set.add(word);
        }
        
        return answer;
    }
}