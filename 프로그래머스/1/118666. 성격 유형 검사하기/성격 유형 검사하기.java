import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int size = survey.length;
        int[] score = {-1, 3, 2, 1, 0, 1, 2, 3};
        HashMap<String, Integer> map = new HashMap<>();
        map.put("R", 0);
        map.put("T", 0);
        
        map.put("C", 0);
        map.put("F", 0);
       
        map.put("J", 0);
        map.put("M", 0);
        
        map.put("A", 0);
        map.put("N", 0);
        
        for (int i = 0; i < size; i++) {
            String left = survey[i].charAt(0) + "";
            String right = survey[i].charAt(1) + "";
            
            if (choices[i] < 4) {
                map.put(left, map.get(left) + score[choices[i]]);
            } else if (choices[i] > 4) {
                map.put(right, map.get(right) + score[choices[i]]);
            }
        }
        
        int R = map.get("R");
        int T = map.get("T");
        
        if (R >= T) {
            answer += "R";
        } else {
            answer += "T";
        }
        
        int C = map.get("C");
        int F = map.get("F");
        
        if (C >= F) {
            answer += "C";
        } else {
            answer += "F";
        }
        
        int J = map.get("J");
        int M = map.get("M");
        
        if (J >= M) {
            answer += "J";
        } else {
            answer += "M";
        }
        
        int A = map.get("A");
        int N = map.get("N");
        
        if (A >= N) {
            answer += "A";
        } else {
            answer += "N";
        }
        return answer;
    }
}

/*
1 - R,T
2 - C,F
3 - J,M
4 - A,N

3 2 1 0 1 2 3
점수 같으면 사전 순
*/