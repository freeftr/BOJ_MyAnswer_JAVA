import java.io.*;
import java.util.*;

public class Main {

    static class Word {
        int idx;
        String s;

        Word(int idx, String s) {
            this.idx = idx;
            this.s = s;
        }
    }

    static int lcp(String a, String b) {
        int lim = Math.min(a.length(), b.length());
        int i = 0;
        while (i < lim && a.charAt(i) == b.charAt(i)) i++;
        return i;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<Word> words = new ArrayList<>();
        String[] org = new String[N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            org[i] = str;
            words.add(new Word(i, str));
        }

        Collections.sort(words, (a, b) -> a.s.compareTo(b.s));

        int maxLen = 0;
        for (int i = 0; i < N - 1; i++) {
            int cur = lcp(words.get(i).s, words.get(i + 1).s);
            if (cur > maxLen) maxLen = cur;
        }

        if (maxLen == 0) {
            System.out.println(org[0]);
            System.out.println(org[1]);
            return;
        }

        HashMap<String, int[]> bestTwo = new HashMap<>();

        for (Word w : words) {
            if (w.s.length() < maxLen) continue;
            String key = w.s.substring(0, maxLen);

            int[] v = bestTwo.get(key);
            if (v == null) {
                bestTwo.put(key, new int[]{w.idx, Integer.MAX_VALUE});
            } else {
                int idx = w.idx;
                if (idx < v[0]) {
                    v[1] = v[0];
                    v[0] = idx;
                } else if (idx < v[1] && idx != v[0]) {
                    v[1] = idx;
                }
            }
        }

        int s = Integer.MAX_VALUE;
        int t = Integer.MAX_VALUE;

        for (int[] v : bestTwo.values()) {
            if (v[1] == Integer.MAX_VALUE) continue;
            int first = v[0];
            int second = v[1];

            if (first < s || (first == s && second < t)) {
                s = first;
                t = second;
            }
        }

        System.out.println(org[s]);
        System.out.println(org[t]);
    }
}
