import java.io.*;
import java.util.*;
class Solution {
    static HashMap<String, int[]> d = new HashMap<>();
    static HashSet<String> visited = new HashSet<>();
    
    public int solution(String dirs) {
        int answer = 0;
        
        String[] cmds = dirs.split("");
        
        d.put("U", new int[]{-1, 0});
        d.put("D", new int[]{1, 0});
        d.put("L", new int[]{0, -1});
        d.put("R", new int[]{0, 1});
        
        double x = 0;
        double y = 0;
        
        for (String cmd : cmds) {
            int[] di = d.get(cmd);
            
            double nx = x + di[0];
            double ny = y + di[1];
            
            if (nx < -5 || ny < -5 || nx > 5 || ny > 5) continue;
            
            String temp = (double)((nx + x) / 2) + " " + (double)((ny + y) / 2);
            if (!visited.contains(temp)) {
                answer++;
                visited.add(temp);
            }
            
            x = nx;
            y = ny;
        }
        return answer;
    }
}

/*
조건
- 지나간 길 중 캐릭터가 처음 걸어본 길의 길이 구하기

풀이
- Set으로 좌표 중간 찍어서 길 갔는지 확인.
*/