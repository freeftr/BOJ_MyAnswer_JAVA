class Solution {
    public int solution(int [][]board) {
        int answer = 0;
        int r = board.length;
        int c = board[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == 1) answer = 1;
            }
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(board[i][j-1],
                                           Math.min(board[i-1][j], board[i-1][j-1])) + 1;
                    answer = Math.max(board[i][j], answer);
                }
            }
        }

        return answer * answer;
    }
}
