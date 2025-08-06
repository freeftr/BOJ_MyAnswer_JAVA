import java.util.*;

class Solution {
    static int answer = 0;
    static String[] friends = {"A", "C", "F", "J", "M", "N", "R", "T"};

    public int solution(int n, String[] data) {
        answer = 0;
        dfs(0, new StringBuilder(), data, new boolean[8]);
        return answer;
    }

    static void dfs(int depth, StringBuilder s, String[] data, boolean[] visited) {
        if (depth == 8) {
            for (String cond : data) {
                char f1 = cond.charAt(0);
                char f2 = cond.charAt(2);
                char op = cond.charAt(3);
                int dist = cond.charAt(4) - '0';

                int pos1 = s.indexOf(String.valueOf(f1));
                int pos2 = s.indexOf(String.valueOf(f2));
                int gap = Math.abs(pos1 - pos2) - 1;

                if (op == '=' && gap != dist) return;
                if (op == '<' && gap >= dist) return;
                if (op == '>' && gap <= dist) return;
            }
            answer++;
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            s.append(friends[i]);
            dfs(depth + 1, s, data, visited);
            s.deleteCharAt(s.length() - 1);
            visited[i] = false;
        }
    }
}
