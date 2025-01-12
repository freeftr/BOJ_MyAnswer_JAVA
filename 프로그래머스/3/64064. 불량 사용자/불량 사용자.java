import java.util.HashSet;
import java.util.ArrayList;

class Solution {
    private HashSet<HashSet<String>> resultSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        backtrack(user_id, banned_id, 0, new HashSet<>());
        return resultSet.size();
    }
    
    private void backtrack(String[] user_id, String[] banned_id, int index, HashSet<String> currentSet) {
        if (index == banned_id.length) {
            resultSet.add(new HashSet<>(currentSet));
            return;
        }
        
        for (String user : user_id) {
            if (currentSet.contains(user)) continue;
            if (match(user, banned_id[index])) {
                currentSet.add(user);
                backtrack(user_id, banned_id, index + 1, currentSet);
                currentSet.remove(user);
            }
        }
    }

    private boolean match(String a, String b) {
        if (a.length() != b.length()) return false;
        
        for (int i = 0; i < a.length(); i++) {
            if (b.charAt(i) == '*') continue;
            if (a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
    }
}
