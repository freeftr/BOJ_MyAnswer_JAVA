class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        
        boolean space = true;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            
            if (cur == ' ') {
                sb.append(' ');
                space = true;
                continue;
            }
            
            cur = Character.toLowerCase(cur);
            
            if (space && 'a' <= cur && cur <= 'z') {
                sb.append(Character.toUpperCase(cur));
            } else {
                sb.append(cur);
            }
            
            space = false;
        }
        return sb.toString();
    }
}
