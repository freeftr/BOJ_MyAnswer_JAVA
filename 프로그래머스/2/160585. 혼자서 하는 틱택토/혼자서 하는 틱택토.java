class Solution {
    public int solution(String[] board) {
        int o = 0, x = 0;

        // 개수 세기
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') o++;
                if (c == 'X') x++;
            }
        }

        // 개수 조건
        if (x > o || o > x + 1) return 0;

        int O = 0, X = 0;

        // 가로
        for (int i = 0; i < 3; i++) {
            if (board[i].equals("OOO")) O++;
            if (board[i].equals("XXX")) X++;
        }

        // 세로
        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == 'O' &&
                board[1].charAt(j) == 'O' &&
                board[2].charAt(j) == 'O') O++;

            if (board[0].charAt(j) == 'X' &&
                board[1].charAt(j) == 'X' &&
                board[2].charAt(j) == 'X') X++;
        }

        // 대각선
        if (board[0].charAt(0) == 'O' &&
            board[1].charAt(1) == 'O' &&
            board[2].charAt(2) == 'O') O++;

        if (board[0].charAt(2) == 'O' &&
            board[1].charAt(1) == 'O' &&
            board[2].charAt(0) == 'O') O++;

        if (board[0].charAt(0) == 'X' &&
            board[1].charAt(1) == 'X' &&
            board[2].charAt(2) == 'X') X++;

        if (board[0].charAt(2) == 'X' &&
            board[1].charAt(1) == 'X' &&
            board[2].charAt(0) == 'X') X++;

        // 둘 다 이김
        if (O > 0 && X > 0) return 0;

        // O가 이겼는데 턴이 맞지 않음
        if (O > 0 && o == x) return 0;

        // X가 이겼는데 턴이 맞지 않음
        if (X > 0 && o > x) return 0;

        return 1;
    }
}