import java.io.*;
import java.util.*;

class Solution {

    static boolean[] visited;
    static ArrayList<String> results = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        
        dfs("ICN", "ICN", 0, tickets);

        Collections.sort(results);
        return results.get(0).split(" ");
    }

    static void dfs(String current, String path, int depth, String[][] tickets) {
        if (depth == tickets.length) {
            results.add(path);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {
                visited[i] = true;
                dfs(tickets[i][1], path + " " + tickets[i][1], depth + 1, tickets);
                visited[i] = false;
            }
        }
    }
}
