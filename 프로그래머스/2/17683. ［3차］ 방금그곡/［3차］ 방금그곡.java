import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String target = erase(m);

        List<Music> result = new ArrayList<>();
        int order = 0;

        for (String info : musicinfos) {
            String[] parts = info.split(",");
            int start = strToInt(parts[0]);
            int end   = strToInt(parts[1]);
            String title  = parts[2];
            String melody = parts[3];

            int duration = end - start;

            String base = erase(melody);

            StringBuilder sb = new StringBuilder();
            while (sb.length() < duration) sb.append(base);
            String played = sb.substring(0, duration);

            if (played.contains(target)) {
                result.add(new Music(title, duration, order));
            }
            order++;
        }

        if (result.isEmpty()) return "(None)";

        result.sort((a, b) -> {
            if (a.dur != b.dur) return b.dur - a.dur;
            return a.idx - b.idx;
        });

        return result.get(0).title;
    }

    static class Music {
        String title;
        int dur;
        int idx;
        Music(String title, int dur, int idx) {
            this.title = title;
            this.dur = dur;
            this.idx = idx;
        }
    }

    public int strToInt(String s) {
        String[] t = s.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    public String erase(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (i + 1 < s.length() && s.charAt(i + 1) == '#') {
                sb.append(Character.toLowerCase(ch));
                i++;
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
