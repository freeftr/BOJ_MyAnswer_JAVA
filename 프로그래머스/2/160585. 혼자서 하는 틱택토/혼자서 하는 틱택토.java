class Solution {
    public int solution(String[] board) {
        int o = 0, x = 0;
        char[][] map = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'O') o++;
                if (map[i][j] == 'X') x++;
            }
        }

        if (!(o == x || o == x + 1)) return 0;

        boolean oWin = check(map, 'O');
        boolean xWin = check(map, 'X');

        if (oWin && xWin) return 0; 
        if (oWin && o != x + 1) return 0; 
        if (xWin && o != x) return 0;     

        return 1;
    }

    private boolean check(char[][] map, char c) {
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == c && map[i][1] == c && map[i][2] == c) return true;
            if (map[0][i] == c && map[1][i] == c && map[2][i] == c) return true;
        }
        if (map[0][0] == c && map[1][1] == c && map[2][2] == c) return true;
        if (map[0][2] == c && map[1][1] == c && map[2][0] == c) return true;
        return false;
    }
}
