import java.util.*;

class Solution {
    static int N;
    static ArrayList<int[]> permuts = new ArrayList<>();
    static PriorityQueue<int[]> result = new PriorityQueue<>((a, b) -> {
        if (a[0] == b[0]) return b[1] - a[1];
        return b[0] - a[0];
    });
    static int[] discountRatio = {10, 20, 30, 40};
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        
        N = emoticons.length;
        
        permut(0, new int[N]);
        
        for (int[] ps : permuts) {
            int total = 0;
            int plus = 0;
            
            for (int[] u : users) {
                int r = u[0];
                int max = u[1];
                int sold = 0;
                
                for (int i = 0; i < N; i++) {
                    if (ps[i] >= r) {
                        sold += (int) (100 - ps[i]) * emoticons[i] / 100;
                    }
                    
                    if (sold >= max) {
                        plus++;
                        sold = 0;
                        break;
                    }
                }
                
                total += sold;
            }
            if (ps[0] == 30 && ps[1] == 40) {
                System.out.println(plus + " " + total);
            }
            
            result.add(new int[]{plus, total});
        }
        
        return result.poll();
    }
    
    static void permut (int depth, int[] cur) {
        if (depth == N) {
            permuts.add(cur.clone());
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            cur[depth] = discountRatio[i];
            permut(depth + 1, cur);
        }
    }
}

/*
조건
- 임티플 최대한, 그다음 판매액 최대한
- 자신의 기준에 따라 일정 비율 이상 할인하는 임티 구매함.
- 일정 가격 이상이면 모두 취소 후 임티플 가입

요구
- 최대 임티플, 최대 판매액 상황

풀이
1. 할인 비율을 순열
2. 비교해보면서 결과 구함
*/