import java.util.*;
class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        HashMap<String, Integer> timeMap = new HashMap<>();
        HashMap<String, Integer> inMap = new HashMap<>();
        
        for (String record : records) {
            String strTime = record.split(" ")[0];
            String num = record.split(" ")[1];
            String type = record.split(" ")[2];
            int time = strToint(strTime);
            
            // 새로 입차하는 경우
            if (type.equals("IN") && !inMap.containsKey(num)) {
                inMap.put(num, time);
            } else {
                int inTime = inMap.get(num);
                int outTime = time;
                
                int diff = outTime - inTime;
                
                timeMap.merge(num, diff, (a, b) -> a + b);
                
                inMap.remove(num);
            }
        }
        
        if (!inMap.isEmpty()) {
            for (String key : inMap.keySet()) {
                int inTime = inMap.get(key);
                int diff = 24 * 60 - 1 - inTime;
                
                timeMap.merge(key, diff, (a, b) -> a + b);
            }
        }
        
        ArrayList<String> result = new ArrayList<>(timeMap.keySet());
        
        Collections.sort(result);
        
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            int time = timeMap.get(result.get(i));
            if (time <= fees[0]) {
                answer[i] = fees[1];
            } else {
                time -= fees[0];
                answer[i] += fees[1];
                if (time % fees[2] != 0) {
                    time -= time % fees[2];
                    time += fees[2];
                }
                answer[i] += fees[3] * time / fees[2];
            }
        }
        
        return answer;
    }
    
    static int strToint(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}

/*
기본 시간 이하 -> 기본 요금
기본 시간 초과 -> 기본 요금 + 초과 시간 * 단위 요금
- 초과시간 안맞으면 올림

차량 번호가 작은 자동차부터 요금 출력

입차 맵 생성

출차 내용 들어오면 입차랑 비교해서 요금 맵에 기록
마지막에 남아있는 차들 정각 기준으로 계산해서 요금 기록
*/