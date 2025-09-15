import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int[] score = {0, 3, 2, 1, 0, 1, 2, 3};
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < choices.length; i++) {
            String type = survey[i];
            String one = type.split("")[0];
            String two = type.split("")[1];
            
            if (choices[i] < 4) {
                map.merge(one, score[choices[i]], (a, b) -> a + b);   
            }
            if (choices[i] > 4) { 
                map.merge(two, score[choices[i]], (a, b) -> a + b);   
            }
        }
        int R = map.getOrDefault("R", 0);
        int T = map.getOrDefault("T", 0);
        int C = map.getOrDefault("C", 0);
        int F = map.getOrDefault("F", 0);
        int J = map.getOrDefault("J", 0);
        int M = map.getOrDefault("M", 0);
        int A = map.getOrDefault("A", 0);
        int N = map.getOrDefault("N", 0);
        
        if (R >= T) {
            answer += "R";
        } else {
            answer += "T";
        }
        
        if (C >= F) {
            answer += "C";
        } else {
            answer += "F";
        }
        
        if (J >= M) {
            answer += "J";
        } else {
            answer += "M";
        }
        
        if (A >= N) {
            answer += "A";
        } else {
            answer += "N";
        }
        
        return answer;
    }
}

/*
1 -> R, T
2 -> C, F
3 -> J, M
4 -> A, N
n개의 질문
3 2 1 0 1 2 3
각 지표에서 높은 점수 선택, 같으면 사전순
*/