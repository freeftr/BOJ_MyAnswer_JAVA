class Solution {
    static int[] answer;
    static int result = 0;
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        dfs(0, info, new int[11], 0, n);
        if (result == 0) return new int[]{-1};
        return answer;
    }
    
    static void dfs(int depth, int[] info, int[] shots, int used, int n) {
        if (depth == 11) {
            shots[10] = n - used;
            calculate(shots, info);
            return;
        }
        
        // 안쏘고 넘어가는 경우
        dfs(depth + 1, info, shots, used, n);
        
        // 점수를 획득하는 데 필요한 최소 개수
        int need = info[depth] + 1;
        if (n - used >= need) {
            shots[depth] = need;
            dfs(depth + 1, info, shots, used + need, n);
            shots[depth] = 0;
        }
    }
    
    static void calculate(int[] shots, int[] info) {
        int ryan = 0;
        int apeach = 0;
        
        for (int i = 0; i < 11; i++) {
            if (shots[i] == 0 && info[i] == 0) continue;

            if (shots[i] > info[i]) {
                ryan += 10 - i;
            } else {
                apeach += 10 - i;
            }
        }
        
        if (apeach >= ryan) return;
        int diff = ryan - apeach;
        if (diff > result) {
            result = diff;
            answer = shots.clone();
        } else if (diff == result) {
            for (int i = 10; i >= 0; i--) {
                if (shots[i] == answer[i]) continue;
                if (shots[i] > answer[i]) {
                    answer = shots.clone();
                }
                break;
            }
        }
    }
}