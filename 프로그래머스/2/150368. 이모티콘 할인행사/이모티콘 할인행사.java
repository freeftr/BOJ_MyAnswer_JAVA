import java.util.*;

class Solution {
    
    static int n, m;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        if (a[0] == b[0]) return b[1] - a[1];
        return b[0] - a[0];
    });
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        n = users.length;
        m = emoticons.length;
        int[] ratio = {10, 20, 30, 40};
        dfs(0, emoticons, ratio, new int[m], users);
        return pq.poll();
    }
    
    static void dfs(int depth, int[] emoticons, int[] ratio, int[] result, int[][] users) {
        if (depth == m) {
            calculate(result, users, emoticons);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            result[depth] = ratio[i];
            dfs(depth + 1, emoticons, ratio, result, users);
        }
    }
    
    static void calculate(int[] result, int[][] users, int[] emoticons) {
        int plus = 0;
        int profit = 0;
        for (int[] user : users) {
            int min = user[0];
            int limit = user[1];
            int sum = 0;
            
            for (int i = 0; i < m; i++) {
                if (result[i] < min) continue;
                sum += emoticons[i] * (100 - result[i]) / 100;
                if (sum >= limit) break;
            }
            
            if (sum >= limit) {
                plus++;
            } else {
                profit += sum;
            }
        }
        
        pq.add(new int[]{plus, profit});
    }
}

/*
1. 이모티콘 플러스 최대
2. 판매액 최대
- 사용자 n명
- 이모티콘 m개
10% 20% 30% 40%
- 기준보다 이상 할인이면 모두 구매
- 일정 금액 넘을 시 모두 취소하고 플러스 가입

1. 반복문으로 모든 조합 구하기
2. 최대가 되는 거 구하기
*/