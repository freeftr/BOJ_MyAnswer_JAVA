import java.util.*;

class Solution {
    Set<Integer> set = new HashSet<>();

    public int solution(String numbers) {
        String[] temp = numbers.split("");
        for (int length = 1; length <= numbers.length(); length++) {
            dfs(0, new StringBuilder(), length, temp, new boolean[temp.length]);
        }
        return set.size();
    }

    void dfs(int depth, StringBuilder sb, int target, String[] temp, boolean[] visited) {
        if (depth == target) {
            int num = Integer.parseInt(sb.toString());
            if (check(num)) {
                set.add(num);
            }
            return;
        }

        for (int i = 0; i < temp.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            sb.append(temp[i]);
            dfs(depth + 1, sb, target, temp, visited);
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    boolean check(int num) {
        if (num <= 1) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
