import java.util.*;

class Solution {
    
    static ArrayList<int[]> result = new ArrayList<>();
    
    public int[][] solution(int n) {
        int[][] answer = {};
        
        dfs(1, 2, 3, n);
        
        answer = new int[result.size()][2];
        
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < 2; j++) {
                answer[i][j] = result.get(i)[j];
            }
        }
        
        return answer;
    }
    
    static void dfs(int start, int next, int target, int n) {
        if (n == 1) {
            result.add(new int[]{start, target});
            return;
        }
        
        dfs(start, target, next, n - 1);
        
        result.add(new int[]{start, target});
        
        dfs(next, start, target, n - 1);
    }
}