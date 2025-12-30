import java.util.*;

class Solution {
    
    static int n, m;
    static PriorityQueue<int[]> result = new PriorityQueue<>((a, b) -> {
        if (b[0] == a[0]) {
            return b[1] - a[1];
        }
        return b[0] - a[0];
    });
    static int[] discountRatios = {10, 20, 30, 40};
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        n = users.length;
        m = emoticons.length;
        
        makeComb(0, new int[m], users, emoticons);
        
        answer = result.poll();
        
        return answer;
    }
    
    static void makeComb(int depth, int[] selected, int[][] users, int[] emoticons) {
        if (depth == m) {
            calculate(selected, users, emoticons);
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            selected[depth] = discountRatios[i];
            makeComb(depth + 1, selected, users, emoticons);
        }
    }
    
    static void calculate(int[] ratio, int[][] users, int[] emoticons) {
        int emPlus = 0;
        int sum = 0;
        
        for (int[] user : users) {
            int minRatio = user[0];
            int limit = user[1];
            int bought = 0;
            boolean plus = false;
            
            for (int i = 0; i < m; i++) {
                if (ratio[i] >= minRatio) {
                    bought += emoticons[i] * (100 - ratio[i]) / 100;
                }
                
                if (bought >= limit) {
                    emPlus++;
                    plus = true;
                    break;
                }
            }
            
            if (!plus) {
                sum += bought;
            }
        }
        
        result.add(new int[]{emPlus, sum});
    }
}

/*
조건
- 이모티콘 할인 행사의 목표는 다음과 같다.
1. 임티플 가입자 최대한 늘리기
2. 임티 판매액 최대한 늘리기
- 할인율은 10, 20, 30, 40중 하나로 설정
- 사용자들은 자신의 기준 이상 할인하는 임티를 전부 구매한다.
- 합이 일정 기준이 이상이면 임티플에 가입한다.

요구
- 행사 목적을 최대한으로 달성했을 때 임티 플 가입자, 매출액 반환

풀이
- 구현
1. 이모티콘 할인율 순열로 전부 구하기
2. 유저별 기준치 확인해서 집계
*/