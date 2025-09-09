class Solution {
    public String solution(String new_id) {
        new_id = new_id.toLowerCase();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < new_id.length(); i++) {
            char c = new_id.charAt(i);
            if (('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }

        String answer = sb.toString();
        answer = answer.replaceAll("\\.{2,}", ".");

        if (!answer.isEmpty() && answer.charAt(0) == '.') {
            answer = answer.substring(1);
        }
        if (!answer.isEmpty() && answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }

        if (answer.isEmpty()) {
            answer = "a";
        }

        if (answer.length() > 15) {
            answer = answer.substring(0, 15);
        }
        if (answer.charAt(answer.length() - 1) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }

        while (answer.length() < 3) {
            answer += answer.charAt(answer.length() - 1);
        }
        return answer;
    }
}
