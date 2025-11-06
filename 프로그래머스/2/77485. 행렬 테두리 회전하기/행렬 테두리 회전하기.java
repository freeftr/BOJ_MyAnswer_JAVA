class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        int[][] map = new int[rows][columns];
        int[][] result = new int[rows][columns];
        int idx = 1;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = idx++;
                result[i][j] = map[i][j];
            }
        }
        
        idx = 0;
        for (int[] query : queries) {
            int sx = query[0] - 1;
            int sy = query[1] - 1;
            int ex = query[2] - 1;
            int ey = query[3] - 1;
            
            int rt = map[sx][ey];
            int lt = map[sx][sy];
            int rd = map[ex][ey];
            int ld = map[ex][sy];
            
            int min = Integer.MAX_VALUE;
            min = Math.min(min, rt);
            min = Math.min(min, lt);
            min = Math.min(min, rd);
            min = Math.min(min, ld);
            
            for (int i = ey; i >= sy + 1; i--) {
                map[sx][i] = map[sx][i - 1];
                min = Math.min(min, map[sx][i]);
            }
            
            for (int i = ex; i >= sx + 1; i--) {
                map[i][ey] = map[i - 1][ey];
                min = Math.min(min, map[i][ey]);
            }
            
            map[sx + 1][ey] = rt;
            
            for (int i = sy; i <= ey - 1; i++) {
                map[ex][i] = map[ex][i + 1];
                min = Math.min(min, map[ex][i]);
            }
            
            map[ex][ey - 1] = rd;
            
            for (int i = sx; i <= ex - 1; i++) {
                map[i][sy] = map[i + 1][sy];
                min = Math.min(min, map[i][sy]);
            }
            
            map[ex - 1][sy] = ld;
            
            answer[idx++] = min;
            
            // System.out.println();
            // for (int i = 0; i < rows; i++) {
            //     System.out.println();
            //     for (int j = 0; j < columns; j++) {
            //         System.out.printf("%3d", map[i][j]);
            //     }
            // }
        }
        
        return answer;
    }
}

/*
조건
0   8   9
0   0   10
0   0   16
0   0   22
*/