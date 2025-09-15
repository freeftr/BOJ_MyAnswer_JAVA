import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        HashSet<Integer> gateSet = new HashSet<>();
        HashSet<Integer> sumSet = new HashSet<>();
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        int minSum = Integer.MAX_VALUE;
        int minInt = Integer.MAX_VALUE;
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int gate : gates) {
            gateSet.add(gate);
        }
        
        for (int summit : summits) {
            sumSet.add(summit);
        }
        
        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int dist = path[2];
            graph.get(from).add(new int[]{to, dist});
            graph.get(to).add(new int[]{from, dist});
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for (int gate : gates) {
            pq.add(new int[]{gate, 0});
            dist[gate] = 0;
        }
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int w = cur[1];
            
            if (w > dist[v]) continue;
            if (sumSet.contains(v)) continue;
            
            for (int[] na : graph.get(v)) {
                int nv = na[0];
                int nd = na[1];
                
                if (gateSet.contains(nv)) continue;
                
                int nw = Math.max(nd, w);
                if (dist[nv] > nw) {
                    dist[nv] = nw;
                    pq.add(new int[]{nv, nw});
                }
            }
        }
        
        Arrays.sort(summits);
        for (int s : summits) {
            if (dist[s] < minInt) {
                minInt = dist[s];
                minSum = s;
            }
        }

        answer[0] = minSum;
        answer[1] = minInt;
        
        return answer;
    }
}

/*
n개의 지점
쉼터 혹은 산봉우리에서 휴식 가능
휴식 없이 이동해야 하는 시간 중 가장 긴 시간 = intensity
산봉우리 중 한 곳 방문해 원래의 출임구 귀환
출입구 -> 산봉우리 -> 출입구

intensity가 최소가 되는 등산코스 봉우리 번호 + intensity

gate 별로 봉우리 가는 다익돌리기.
*/