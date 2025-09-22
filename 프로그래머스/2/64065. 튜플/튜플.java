import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.substring(2, s.length() - 2);
        String[] a = s.split("\\},\\{");
        Arrays.sort(a, (b, c) -> b.length() - c.length());
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < a.length; i++) {
            String[] temp = a[i].split(",");
            for (String t : temp) {
                if (!result.contains(Integer.parseInt(t))) {
                    result.add(Integer.parseInt(t));
                }
            }
        }
        
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}

/*
튜플은 중복된 원소가 있을 수 있음.
순서가 다르면 다른 튜플
*/