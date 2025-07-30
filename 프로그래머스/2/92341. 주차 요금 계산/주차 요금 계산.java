import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> inMap = new HashMap<>();
        Map<String, Integer> timeMap = new HashMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            String[] timeParts = parts[0].split(":");
            int minutes = Integer.parseInt(timeParts[0]) * 60 + Integer.parseInt(timeParts[1]);
            String car = parts[1];
            String type = parts[2];

            if (type.equals("IN")) {
                inMap.put(car, minutes);
            } else {
                int inTime = inMap.remove(car);
                int duration = minutes - inTime;
                timeMap.put(car, timeMap.getOrDefault(car, 0) + duration);
            }
        }

        for (String car : inMap.keySet()) {
            int inTime = inMap.get(car);
            int duration = (23 * 60 + 59) - inTime;
            timeMap.put(car, timeMap.getOrDefault(car, 0) + duration);
        }

        List<String> carList = new ArrayList<>(timeMap.keySet());
        Collections.sort(carList);

        int[] answer = new int[carList.size()];
        for (int i = 0; i < carList.size(); i++) {
            String car = carList.get(i);
            int totalTime = timeMap.get(car);
            int totalFee = 0;

            if (totalTime <= fees[0]) {
                totalFee = fees[1];
            } else {
                totalFee = fees[1] + 
                    (int) Math.ceil((totalTime - fees[0]) / (double) fees[2]) * fees[3];
            }

            answer[i] = totalFee;
        }

        return answer;
    }
}
