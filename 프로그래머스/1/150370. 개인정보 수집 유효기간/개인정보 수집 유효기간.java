import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String t : terms) {
            String[] sp = t.split(" ");
            termMap.put(sp[0], Integer.parseInt(sp[1]));
        }

        int todayDays = toDays(today);
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] sp = privacies[i].split(" ");
            String collected = sp[0];
            String termKey = sp[1];
            int periodMonths = termMap.get(termKey);

            int collectedDays = toDays(collected);
            int expireDays = collectedDays + periodMonths * 28 - 1;

            if (todayDays > expireDays) {
                list.add(i + 1);
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) answer[i] = list.get(i);
        return answer;
    }

    static int toDays(String date) {
        String[] sp = date.split("\\.");
        int y = Integer.parseInt(sp[0]);
        int m = Integer.parseInt(sp[1]);
        int d = Integer.parseInt(sp[2]);
        return (y * 12 + (m - 1)) * 28 + d;
    }
}
