import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {

        long[] banIdx = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banIdx[i] = getIdx(bans[i]);
        }
        Arrays.sort(banIdx);

        long ansIdx = n;
        int i = 0;

        // ansIdx 이하의 ban이 있으면, 그만큼 ansIdx를 밀어야 함
        while (i < banIdx.length && banIdx[i] <= ansIdx) {
            ansIdx++;
            i++;
        }

        return getString(ansIdx);
    }

    static String getString(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            long r = n % 26;

            if (r == 0) {
                sb.append('z');
                n = n / 26 - 1;
            } else {
                sb.append((char) ('a' + r - 1));
                n /= 26;
            }
        }
        return sb.reverse().toString();
    }

    static long getIdx(String s) {
        long idx = 0;
        for (int i = 0; i < s.length(); i++) {
            idx = idx * 26 + (s.charAt(i) - 'a' + 1);
        }
        return idx;
    }
}
