import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        int[][] record = new int[n][n];
        int[] result = new int[n];
        HashMap<String, Integer> idMap = new HashMap<>();
        
        int idx = 0;
        for (String friend : friends) {
            idMap.put(friend, idx++);
        }
        
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            int giver = idMap.get(parts[0]);
            int receiver = idMap.get(parts[1]);
            record[giver][receiver]++;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (record[i][j] > record[j][i]) {
                    result[i]++;
                } else if (record[i][j] < record[j][i]) {
                    result[j]++;
                } else {
                    int A = 0, B = 0;
                    for (int a = 0; a < n; a++) {
                        A += record[i][a] - record[a][i];
                        B += record[j][a] - record[a][j];
                    }
                    if (A > B) result[i]++;
                    else if (A < B) result[j]++;
                }
            }
        }
        
        int answer = 0;
        for (int r : result) {
            answer = Math.max(answer, r);
        }
        return answer;
    }
}
