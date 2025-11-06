import java.util.*;

class Solution {
    
    static int[][] map;
    static int N;
    static int idx = 1, x = 0, y = 0;
    
    public int[] solution(int n) {
        int[] answer;
        
        if (n == 1) return new int[]{1};
        
        map = new int[n][n];
        N = n;
        x = 0;
        y = 0;
        idx = 1;
        
        while (idx < n * (n + 1) / 2) {
            down();
            right();
            up();
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            // System.out.println();
            for (int j = 0; j < n; j++) {
                // System.out.print(map[i][j] + " ");
                if (map[i][j] == 0) continue;
                result.add(map[i][j]);
            }
        }
        
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
    
    static void down() {
        while (true) {
            map[x++][y] = idx++;
            
            if (x >= N || map[x][y] != 0) {
                x--;
                idx--;
                break;
            }
        }
    }
    
    static void right() {
        while (true) {
            map[x][y++] = idx++;
            
            if (y >= N || map[x][y] != 0) {
                y--;
                idx--;
                break;
            }
        }
    }
    
    static void up() {
        while (true) {
            map[x--][y--] = idx++;
            
            if (x < 0 || y < 0 || map[x][y] != 0) {
                x++;
                y++;
                idx--;
                break;
            }
        }
    }
}
/*
1
2   9
3       8
4   5   6   7   
*/