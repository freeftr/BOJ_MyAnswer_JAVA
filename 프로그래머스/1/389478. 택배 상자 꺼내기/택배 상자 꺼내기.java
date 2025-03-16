import java.io.*;
import java.util.*;

class Solution {
    static int[][] map;
    static int N, W;
    static int numCol, numRow;

    public int solution(int n, int w, int num) {
        N = n;
        W = w;
        int rows = (N + W - 1) / W;
        map = new int[rows][W];
        
        int col = 0, row = 0, idx = 1;
        
        while (idx <= N) {
            if (idx == num) {
                numCol = col;
                numRow = row;
            }
            
            map[row][col] = idx;
            
            if (row % 2 == 0) {
                col++;
            } else {
                col--;
            }
            
            if (col >= W) {
                row++;
                col--;
            } else if (col < 0) {
                row++;
                col++;
            }
            
            idx++;
        }
        
        int cnt = 1;
        while (numRow < rows - 1 && map[numRow + 1][numCol] != 0) {
            cnt++;
            numRow++;
        }
        
        return cnt;
    }
}
