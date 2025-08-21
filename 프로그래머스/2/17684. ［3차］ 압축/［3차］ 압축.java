import java.util.*;
class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        ArrayList<String> alpha = new ArrayList<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            alpha.add((char)('A' + i) + "");
        }
        
        for (int i = 0; i < msg.length(); i++) {
            String temp = msg.charAt(i) + "";
            String w = temp;
            String c = "";

            for (int j = i + 1; j < msg.length(); j++) {
                temp += msg.charAt(j);
                if (!alpha.contains(temp)) {
                    break;
                } else {
                    w = temp;
                }
            }
            c = temp;
            result.add(alpha.indexOf(w) + 1);
            alpha.add(c);
            
            // System.out.println(i + " " + w + " " + c + " " + (alpha.indexOf(w) + 1));
            if (w.length() > 1) {
                i += w.length() - 1;
            }
        }
        
        answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
}