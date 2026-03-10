import java.util.*;

class Solution {
    static HashMap<Integer, HashMap<String, ArrayList<Integer>>> map = new HashMap<>();

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        int robots = routes.length;

        int idx = 0;

        for (int[] route : routes) {
            int sx = points[route[0] - 1][0];
            int sy = points[route[0] - 1][1];

            idx++;
            int time = 0;

            map.computeIfAbsent(time, k -> new HashMap<>())
                    .computeIfAbsent(sx + " " + sy, k -> new ArrayList<>())
                    .add(idx);

            for (int i = 1; i < route.length; i++) {
                int ex = points[route[i] - 1][0];
                int ey = points[route[i] - 1][1];

                int x = sx;
                int y = sy;

                while (x != ex || y != ey) {

                    if (x < ex) x++;
                    else if (x > ex) x--;
                    else if (y < ey) y++;
                    else if (y > ey) y--;

                    time++;

                    map.computeIfAbsent(time, k -> new HashMap<>())
                            .computeIfAbsent(x + " " + y, k -> new ArrayList<>())
                            .add(idx);
                }

                sx = ex;
                sy = ey;
            }
        }

        for (int time : map.keySet()) {
            HashMap<String, ArrayList<Integer>> mmap = map.get(time);

            for (ArrayList<Integer> list : mmap.values()) {
                if (list.size() >= 2) answer++;
            }
        }

        return answer;
    }
}