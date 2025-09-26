import java.util.*;
class Solution {
    
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        
        HashMap<String, Integer> inTimes = new HashMap<>();
        HashMap<String, Integer> totalTime = new HashMap<>();
        
        for (String record : records) {
            int time = strToInt(record.split(" ")[0]);
            String carNum = record.split(" ")[1];
            String type = record.split(" ")[2];
            
            if (type.equals("IN")) {
                inTimes.put(carNum, time);
            }
            
            if (type.equals("OUT")) {
                int in = inTimes.get(carNum);
                int out = time;
                int diff = out - in;
                
                totalTime.merge(carNum, diff, (a, b) -> a + b);
                inTimes.remove(carNum);
            }
        }
        
        for (String carNum : inTimes.keySet()) {
            int in = inTimes.get(carNum);
            int out = 23 * 60 + 59;
            int diff = out - in;
            
            totalTime.merge(carNum, diff, (a, b) -> a + b);
        }
        
        ArrayList<Price> result = new ArrayList<>();
        
        for (String carNum : totalTime.keySet()) {
            int time = totalTime.get(carNum);
            int baseTime = fees[0];
            int basePrice = fees[1];
            int car = Integer.parseInt(carNum);
            
            //기본 시간 이하
            if (time <= baseTime) {
                result.add(new Price(car, basePrice));
                continue;
            }
            
            //기본 시간 초과
            int totalPrice = 0;
            totalPrice += basePrice;
            time -= baseTime;
            
            //나누어떨어지는 경우 & 안되는 경우
            if (time % fees[2] == 0) {
                totalPrice += time / fees[2] * fees[3];
            } else {
                totalPrice += (time / fees[2] + 1) * fees[3];
            }
            
            result.add(new Price(car, totalPrice));
        }
        
        Collections.sort(result, (a, b) -> a.carNum - b.carNum);
        answer = new int[result.size()];
        
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i).fee;
        }
        
        return answer;
    }
    
    static class Price {
        int carNum;
        int fee;
        Price (int carNum, int fee) {
            this.carNum = carNum;
            this.fee = fee;
        }
    }
    
    static int strToInt(String s) {
        int hour = Integer.parseInt(s.split(":")[0]);
        int min = Integer.parseInt(s.split(":")[1]);
        
        return hour * 60 + min;
    }
}

/*
입차했다가 나간 경우
입차하고 안나간 경우

누적 시간 구해놓고 마지막에 계산
*/