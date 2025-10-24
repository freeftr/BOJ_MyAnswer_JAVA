class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int total = strToInt(play_time);
        int adv = strToInt(adv_time);
        
        int[] timeLine = new int[total + 1];
        
        for (String log : logs) {
            int s = strToInt(log.split("-")[0]);
            int e = strToInt(log.split("-")[1]);
            
            for (int i = s; i < e; i++) {
                timeLine[i]++;
            }
        }
        
        long[] preSum = new long[total + 1];
        
        preSum[0] = timeLine[0];
        
        for (int i = 1; i <= total; i++) {
            preSum[i] = preSum[i - 1] + timeLine[i - 1];
        }
        
        int bestStart = 0;
        long best = preSum[adv] - preSum[0];
        long window = best;

        for (int start = 1; start + adv <= total; start++) {
            long curr = preSum[start + adv] - preSum[start];
            if (curr > best) {
                best = curr;
                bestStart = start;
            }
        }

        return intToStr(bestStart);
    }
    
    static int strToInt(String s) {
        int h = Integer.parseInt(s.split(":")[0]);
        int m = Integer.parseInt(s.split(":")[1]);
        int c = Integer.parseInt(s.split(":")[2]);
        
        return h * 60 * 60 + m * 60 + c;
    }
    
    static String intToStr(int a) {
        int s = a % 60;
        a /= 60;
        int m = a % 60;
        a /= 60;
        int h = a;
        return String.format("%02d:%02d:%02d", h, m, s);
    }
}

/*
조건
- 사람들이 가장 많이 보는 구간에 광고 넣음.
- 재생구간 기록 있음.
- 최적 위치 찾기.

요구
- 누적 재생시간이 가장 많이 나오는 곳에 공익 광고 넣음.

풀이
- 투포인터? 누적합?
*/