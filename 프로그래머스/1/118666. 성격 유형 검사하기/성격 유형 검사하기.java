import java.util.*;
class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        int[] scores = {0, 3, 2, 1, 0, 1, 2, 3};
        HashMap<String, Integer> result = new HashMap<>();
        result.put("R", 0);
        result.put("T", 0);
        result.put("C", 0);
        result.put("F", 0);
        result.put("J", 0);
        result.put("M", 0);
        result.put("A", 0);
        result.put("N", 0);
        
        
        for (int i = 0; i < survey.length; i++) {
            String apeach = survey[i].split("")[0];
            String neo = survey[i].split("")[1];

            if (choices[i] <= 3) {
                result.merge(apeach, scores[choices[i]], (a, b) -> a + b);
            } else {
                result.merge(neo, scores[choices[i]], (a, b) -> a + b);
            }
        }
        
        int R = 0, T = 0;
        int C = 0, F = 0;
        int J = 0, M = 0;
        int A = 0, N = 0;
        
        R = result.get("R");
        T = result.get("T");
        C = result.get("C");
        F = result.get("F");
        J = result.get("J");
        M = result.get("M");
        A = result.get("A");
        N = result.get("N");
        
        answer += R >= T ? "R" : "T";
        answer += C >= F ? "C" : "F";
        answer += J >= M ? "J" : "M";
        answer += A >= N ? "A" : "N";
        
        return answer;
    }
}

/*
조건
- 1 -> R, T
- 2 -> C, F
- 3 -> J, M
- 4 -> A, N
- 점수표 -> 3 2 1 0 1 2 3
- 결과 = 모든 질문의 성격 유형 점수를 더해 각 지표에서 더 높은 점수를 받은 것.
- 같으면 사전순

요구
- 검사자의 성격 유형 검사 지표 번호 순대로.
*/