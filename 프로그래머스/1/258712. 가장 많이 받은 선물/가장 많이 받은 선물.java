import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        
        HashMap<String, Integer> indexMap = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            indexMap.put(friends[i], i);
        }
        
        int[][] record = new int[n][n];
        int[] result = new int[n];
        
        for (String gift : gifts) {
            String give = gift.split(" ")[0];
            String receive = gift.split(" ")[1];
            
            int gIdx = indexMap.get(give);
            int rIdx = indexMap.get(receive);
            
            record[gIdx][rIdx]++;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int A = record[i][j];
                int B = record[j][i];
                
                if (A > B) {
                    result[i]++;
                } else if (A == B) {
                    int aScore = getScore(record, i);
                    int bScore = getScore(record, j);
                    
                    if (aScore > bScore) {
                        result[i]++;
                    }
                    
                    if (bScore > aScore) {
                        result[j]++;
                    }
                } else {
                    result[j]++;
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, result[i]);
        }
        
        return answer /= 2;
    }
    
    static int getScore(int[][] record, int idx) {
        int give = 0;
        int receive = 0;
        
        for (int i = 0; i < record.length; i++) {
            give += record[idx][i];
            receive += record[i][idx];
        }
        
        return give - receive;
    }
}

/*
조건
- 선물 기록을 통해 다음달에 누가 가장 많이 받을지 찾기
- 주고받은 기록 있으면 -> 더 많이 준 사람이 다음 달에 하나
- 같거나 없으면 -> 선물 지수가 더 큰사람이 다음달에
- 선물지수 = 여태까지 준거 - 받은거

요구
- 가장 많이 받는 친구의 선물 수

풀이
- <이름, 내가 준 사람>
- <이름, 나한테 준 사람>
- 이렇게 두 개 만들고 구하기
- 이차원 배열 만들어서 기록해야 할듯?
*/