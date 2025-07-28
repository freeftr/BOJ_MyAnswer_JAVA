import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(2);
        s = s.substring(0, s.length() - 2);
        String[] tuples = s.split("},\\{");
        Arrays.sort(tuples, (a, b) -> a.length() - b.length());
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for (String tuple : tuples) {
            String[] temps = tuple.split(",");
            for (String temp : temps) {
                int a = Integer.parseInt(temp);
                
                if (list.contains(a)) {
                    continue;
                } else {
                    list.add(a);
                }
            }
        }
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}