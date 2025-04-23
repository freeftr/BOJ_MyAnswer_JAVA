import java.util.*;

class Solution {

    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int max = 0;

        m = normalizeMelody(m);

        for (String info : musicinfos) {
            String[] parts = info.split(",");
            String start = parts[0];
            String end = parts[1];
            String title = parts[2];
            String melody = parts[3];

            int startHour = Integer.parseInt(start.split(":")[0]);
            int startMinute = Integer.parseInt(start.split(":")[1]);
            int endHour = Integer.parseInt(end.split(":")[0]);
            int endMinute = Integer.parseInt(end.split(":")[1]);

            int playTime = (endHour * 60 + endMinute) - (startHour * 60 + startMinute);

            String normalizedMelody = normalizeMelody(melody);
            StringBuilder played = new StringBuilder();

            for (int i = 0; i < playTime; i++) {
                played.append(normalizedMelody.charAt(i % normalizedMelody.length()));
            }

            if (played.toString().contains(m)) {
                if (playTime > max) {
                    max = playTime;
                    answer = title;
                }
            }
        }

        return answer;
    }

    private String normalizeMelody(String melody) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < melody.length(); i++) {
            char c = melody.charAt(i);
            if (c == '#') {
                int lastIndex = sb.length() - 1;
                sb.setCharAt(lastIndex, Character.toLowerCase(sb.charAt(lastIndex)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
