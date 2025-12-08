import java.util.*;

class Solution {
    
    static int N;
    static ArrayList<int[]>[] pathes;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        N = routes.length;
        pathes = new ArrayList[N];
        
        // 로봇별 경로 전처리.
        for (int i = 0; i < N; i++) {
            pathes[i] = findPath(points, routes[i]);
        }
        
        // 움직임 진행.
        int time = 0;
        
        while (true) {
            HashMap<String, Integer> counter = new HashMap<>();
            int alive = 0;
            
            for (int i = 0; i < N; i++) {
                ArrayList<int[]> path = pathes[i];
                
                if (time < path.size()) {
                    alive++;
                    
                    int[] cur = path.get(time);
                    
                    String key = cur[0] + " " + cur[1];
                    
                    counter.merge(key, 1, (a, b) -> a + b);
                }
            }
            
            if (alive == 0) break;
            
            for (int cnt : counter.values()) {
                if (cnt > 1) answer++;
            }
            
            time++;
        }
        
        return answer;
    }
    
    static ArrayList<int[]> findPath(int[][] points, int[] route) {
        ArrayList<int[]> path = new ArrayList<>();

        int x = points[route[0] - 1][0];
        int y = points[route[0] - 1][1];

        path.add(new int[]{x, y});

        for (int i = 1; i < route.length; i++) {
            int nx = points[route[i] - 1][0];
            int ny = points[route[i] - 1][1];

            while (x != nx) {
                x += (nx > x ? 1 : -1);
                path.add(new int[]{x, y});
            }

            while (y != ny) {
                y += (ny > y ? 1 : -1);
                path.add(new int[]{x, y});
            }
        }

        return path;
    }
}