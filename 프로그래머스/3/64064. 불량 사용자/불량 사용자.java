import java.util.*;

class Solution {
    static HashSet<HashSet<String>> result = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        dfs(0, new HashSet<String>(), user_id, banned_id);
        return result.size();
    }
    
    static void dfs(int depth, HashSet<String> banned, String[] user_id, String[] banned_id) {
        if (depth == banned_id.length) {
            result.add(new HashSet<>(banned));
            return;
        }
        
        for (String user : user_id) {
            if (banned.contains(user)) continue;
            if (check(banned_id[depth], user)) {
                banned.add(user);
                dfs(depth + 1, banned, user_id, banned_id);
                banned.remove(user);
            }
        }
    }
    
    
    // a = ban
    static boolean check(String a, String b) {
        if (a.length() != b.length()) return false;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '*') continue;
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }
}

/*
불량 사용자 당첨자에서 제외.
*로 아이디 일부 가림.

*/