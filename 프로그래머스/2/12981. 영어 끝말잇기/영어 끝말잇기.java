import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> set = new HashSet<>();
        
        int idx = -1;
        for (int i = 0; i < words.length; i++) {
            if (set.contains(words[i])) {
                idx = i;
                break;
            }
            if (i != 0 && words[i].charAt(0) != words[i - 1].charAt(words[i - 1].length() - 1)) {
                idx = i;
                break;
            }
            set.add(words[i]);
        }
        
        if (idx == -1) return new int[]{0,0};
        
        answer[0] = idx % n + 1;
        answer[1] = idx / n + 1;

        return answer;
    }
}