import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int bus = 9 * 60;
        int idx = 0;
        int last = -1;
        
        int[] lines = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            lines[i] = strToInt(timetable[i]);
        }
        
        Arrays.sort(lines);
        
        for (int i = 0; i < n; i++) {
            int seats = m;
            
            while (seats > 0 && idx < lines.length && lines[idx] <= bus) {
                seats--;
                last = lines[idx++];
            }
            
            if (i == n - 1) {
                if (seats > 0) return intToStr(bus);
                else return intToStr(last - 1);
            }
            
            bus += t;
        }
        return intToStr(bus);
    }
    
    static int strToInt(String  s) {
        int h = Integer.parseInt(s.split(":")[0]);
        int m = Integer.parseInt(s.split(":")[1]);
        return h * 60 + m;
    }
    
    static String intToStr (int s) {
        int h = s / 60;
        int m = s % 60;
        
        return String.format("%02d:%02d", h, m);
    }
}

/*
조건
- 9시부터 n회 t분 간격으로 셔틀 운행
- 최대 m명 탑승

요구
- 콘이 셔틀을 타고 사무실로 갈 수 있는 도착 시각 중 제일 늦은 시각.

풀이
- 시분을 분으로 통일
- n번 반복문 돌리기.
- 각 버스마다 앞에서부터 탈 수 있는 사람 태우기.
- 자리가 남아있고, 줄 선 시각이 버스 시간 전
- 마지막으로 탄사람 기록
- 마지막에 자리가 남아있으면 버스 시간 기록
- 자리가 없으면 마지막 탄사람보다 1분빨리 기록
*/