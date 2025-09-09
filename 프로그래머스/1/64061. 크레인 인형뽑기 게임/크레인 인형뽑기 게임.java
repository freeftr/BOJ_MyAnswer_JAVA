import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int[] top = new int[board.length];
        int size = board.length;
        
        for (int move : moves) {
            for (int i = 0; i < size; i++) {
                if (board[i][move - 1] != 0) {
                    if (stack.isEmpty()) {
                        stack.push(board[i][move - 1]);
                    } else {
                        if (stack.peek() == board[i][move - 1]) {
                            answer += 2;
                            stack.pop();
                        } else {
                            stack.push(board[i][move - 1]);
                        }
                    }
                    
                    board[i][move - 1] = 0;
                    break;
                }
            }
        }
        return answer;
    }
}