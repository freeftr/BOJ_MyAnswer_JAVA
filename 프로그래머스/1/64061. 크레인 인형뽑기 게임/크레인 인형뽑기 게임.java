import java.util.*;
class Solution {
    static int[][] map;
    static int N;
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        N = board.length;
        
        Queue[] q = new LinkedList[N];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            q[i] = new LinkedList<Integer>();
            for (int j = 0; j < N; j++) {
                if (board[j][i] == 0) continue;
                q[i].add(board[j][i]);
            }
        }
        
        for (int move : moves) {
            if (q[move - 1].isEmpty()) continue;
            int cur = (int) q[move - 1].poll();
            if (stack.isEmpty()) {
                stack.push(cur);
            } else {
                if (stack.peek() == cur) {
                    stack.pop();
                    answer += 2;
                } else {
                    stack.push(cur);
                }
            }
        }
        return answer;
    }
}

/*
N * N 맵

하나씩 뽑아서 장바구니에 넣음.
장바구니 두개 연속이면 제거 => 스택
*/