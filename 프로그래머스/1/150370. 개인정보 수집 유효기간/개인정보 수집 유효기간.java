import java.util.*;

class Solution {

    static HashMap<String, Integer> termsMap = new HashMap<>();

    public int[] solution(String today, String[] terms, String[] privacies) {
        String[] todayArr = today.split("\\.");
        int curYear = Integer.parseInt(todayArr[0]);
        int curMonth = Integer.parseInt(todayArr[1]);
        int curDay = Integer.parseInt(todayArr[2]);
        int todayTotal = curYear * 12 * 28 + curMonth * 28 + curDay;

        for (String term : terms) {
            String[] t = term.split(" ");
            termsMap.put(t[0], Integer.parseInt(t[1]));
        }

        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            String[] date = parts[0].split("\\.");
            String type = parts[1];

            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);

            int total = year * 12 * 28 + month * 28 + day + (termsMap.get(type) * 28) - 1;

            if (total < todayTotal) {
                result.add(i + 1);
            }
        }

        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}
