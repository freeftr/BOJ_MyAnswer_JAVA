import java.io.*;
import java.util.*;

class Solution {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static HashSet<String> set = new HashSet<>();
    public int solution(String dirs) {
        int answer = 0;
        String[] coms = dirs.split("");
        int x = 0, y = 0;
        for (String com : coms) {
            int px = x;
            int py = y;
            if (com.equals("U")) {
                x += dx[0];
                y += dy[0];
            } else if (com.equals("D")) {
                x += dx[1];
                y += dy[1];
            } else if (com.equals("L")) {
                x += dx[2];
                y += dy[2];
            } else if (com.equals("R")) {
                x += dx[3];
                y += dy[3];
            }
            
            if (x < -5 || y < -5 || x > 5 || y > 5) {
                x = px;
                y = py;
                continue;
            }
            double vx = (x + px) / 2.0;
            double vy = (y + py) / 2.0;
            String s = vx + "-" + vy;
            
            if (!set.contains(s)) {
                answer++;
                set.add(s);
            }
        }
        return answer;
    }
}