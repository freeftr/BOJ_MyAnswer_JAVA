import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int N = friends.length;
        int[][] record = new int[N][N];
        HashMap<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < N; i++) {
            map.put(friends[i], i);
        }
        
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            String give = parts[0];
            String get = parts[1];
            
            int gi = map.get(give);
            int ge = map.get(get);
            record[gi][ge]++;
        }
        
        // 선물 지수: (준 수 - 받은 수)
        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            int s = 0;
            for (int k = 0; k < N; k++) {
                s += record[i][k];   // i가 k에게 준 수
                s -= record[k][i];   // k가 i에게 준 수
            }
            score[i] = s;
        }

        int[] result = new int[N];
        
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int A = record[i][j];
                int B = record[j][i];

                if (A > B) {
                    result[i]++;
                } else if (B > A) {
                    result[j]++;
                } else { // A == B 이면 선물 지수 비교
                    if (score[i] > score[j]) result[i]++;
                    else if (score[j] > score[i]) result[j]++;
                }
            }
        }

        for (int x : result) {
            if (x > answer) answer = x;
        }
        return answer;
    }
}

/*
조건
- 주고받은 기록 있음 -> 더 많은 선물을 준 사람이 다음달에 수령
- 주고받은 기록이 같음(0 포함) -> 선물 지수가 큰 사람이 수령
- 선물 지수 = (준 수 - 받은 수)
*/
