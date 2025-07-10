import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = 0;
        
        for (int mat : mats) {
            for (int i = 0; i < park.length; i++) {
                for (int j = 0; j < park[0].length; j++) {
                    if (park[i][j].equals("-1")) {
                        if (check(mat, i, j, park)) {
                            answer = Math.max(answer, mat);
                        }
                    }
                }
            }
        }
        
        return answer == 0 ? -1 : answer;
    }
    
    static boolean check(int mat, int a, int b, String[][] park) {
        if (a + mat > park.length || b + mat > park[0].length) return false;

        for (int i = a; i < a + mat; i++) {
            for (int j = b; j < b + mat; j++) {
                if (!park[i][j].equals("-1")) return false;
            }
        }
        return true;
    }
}
