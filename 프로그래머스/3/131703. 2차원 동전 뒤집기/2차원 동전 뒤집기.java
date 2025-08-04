import java.util.*;

class Solution {

    static int row, col;
    static boolean flag;
    static int min = Integer.MAX_VALUE;

    public int solution(int[][] beginning, int[][] target) {
        row = beginning.length;
        col = beginning[0].length;

        ArrayList<Integer> candidate = new ArrayList<>();
        dfs(0, candidate, beginning, target);

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    static void dfs(int depth, ArrayList<Integer> candidate, int[][] beginning, int[][] target) {
        if (depth == row + col) {
            int[][] copy = copyMatrix(beginning);
            flag = true;

            for (int i = 0; i < row; i++) {
                if (candidate.get(i) == 1) {
                    for (int j = 0; j < col; j++) {
                        copy[i][j] = copy[i][j] == 1 ? 0 : 1;
                    }
                }
            }

            for (int i = 0; i < col; i++) {
                if (candidate.get(i + row) == 1) {
                    for (int j = 0; j < row; j++) {
                        copy[j][i] = copy[j][i] == 1 ? 0 : 1;
                    }
                }
            }

            for (int i = 0; i < row && flag; i++) {
                for (int j = 0; j < col; j++) {
                    if (copy[i][j] != target[i][j]) {
                        flag = false;
                        break;
                    }
                }
            }

            if (flag) {
                int cnt = 0;
                for (int x : candidate) cnt += x;
                min = Math.min(min, cnt);
            }

            return;
        }

        candidate.add(0);
        dfs(depth + 1, candidate, beginning, target);
        candidate.remove(candidate.size() - 1);

        candidate.add(1);
        dfs(depth + 1, candidate, beginning, target);
        candidate.remove(candidate.size() - 1);
    }

    static int[][] copyMatrix(int[][] src) {
        int[][] result = new int[row][col];
        for (int i = 0; i < row; i++) {
            result[i] = Arrays.copyOf(src[i], col);
        }
        return result;
    }
}
