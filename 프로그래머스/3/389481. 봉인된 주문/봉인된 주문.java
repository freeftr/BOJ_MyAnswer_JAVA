import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        
        long[] banIdx = new long[bans.length];
        
        for (int i = 0; i < bans.length; i++) {
            banIdx[i] = getIdx(bans[i]);
        }
        
        Arrays.sort(banIdx);
        
        for (long idx : banIdx) {
            if (idx <= n) n++;
        }
        
        return getSpell(n);
    }
    
    static String getSpell(long idx) {
        StringBuilder sb = new StringBuilder();
        
        while (idx > 0) {
            idx--;
            long mod = idx % 26;
            sb.append((char)('a' + mod));
            idx /= 26;
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