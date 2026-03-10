class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        int left = 1;
        int right = 100000;
        
        while (left < right) {
            int mid = (left + right) / 2;
            
            // 이 숙련도로 가능하면
            if (check(diffs, times, limit, mid)) {
                right = mid;
            } else {
                // 불가능 하면
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    static boolean check(int[] diffs, int[] times, long limit, int level) {
        long sum = 0;

        for (int i = 0; i < diffs.length; i++) {

            if (diffs[i] <= level) {
                sum += times[i];
            } else {
                int prev = (i == 0 ? 0 : times[i - 1]);
                int fail = diffs[i] - level;

                sum += fail * (times[i] + prev) + times[i];
            }

            if (sum > limit) return false;
        }

        return true;
    }
}

/*
조건
- diff : 퍼즐의 난이도
- time_cur : 퍼즐의 소요 시간
- time_prev : 이전 퍼즐의 소요 시간
- level : 숙련도

게임 진행 방법
- diff <= level : time_cur만큼 소요
- diff > level : (diff - level + 1) * time_cur + time_prev

요구
- limit 내에 퍼즐을 모두 수행하기 위한 숙련도의 최솟값 구하기

풀이
- 이분탐색?
*/