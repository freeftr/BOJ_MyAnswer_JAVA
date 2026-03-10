import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int max = 0;
        for (int v : nodes) max = Math.max(max, v);

        int[] deg = new int[max + 1];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int v : nodes) graph.put(v, new ArrayList<>());

        for (int[] e : edges) {
            deg[e[0]]++;
            deg[e[1]]++;
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[max + 1];
        int ord = 0, rev = 0;

        for (int root : nodes) {
            if (visited[root]) continue;

            List<Integer> component = new ArrayList<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.add(root);
            visited[root] = true;

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                component.add(cur);
                for (int nb : graph.get(cur)) {
                    if (!visited[nb]) {
                        visited[nb] = true;
                        queue.add(nb);
                    }
                }
            }

            int sameCount = 0;
            for (int v : component) {
                if (v % 2 == deg[v] % 2) sameCount++;
            }
            int diffCount = component.size() - sameCount;

            if (sameCount == 1) ord++;
            if (diffCount == 1) rev++;
        }

        return new int[]{ord, rev};
    }
}