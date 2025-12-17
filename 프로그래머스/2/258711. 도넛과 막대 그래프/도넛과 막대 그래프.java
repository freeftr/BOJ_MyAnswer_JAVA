class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        // 정점 개수 구하기
        int N = 0;
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            N = Math.max(N, from);
            N = Math.max(N, to);
        }
        
        int[] indep = new int[N + 1];
        int[] outdep = new int[N + 1];
        
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            
            indep[to]++;
            outdep[from]++;
        }
        
        // for (int i = 1; i <= N; i++) {
        //     System.out.println("idx:" + i + " in:" + indep[i] + " out:" + outdep[i]);
        // }
        
        int line = 0;
        int donut = 0;
        int eight = 0;
        int v = 0;
        int max = 0;
        
        for (int i = 0; i <= N; i++) {
            int in = indep[i];
            int out = outdep[i];
            
            if (in >= 1 && out == 0) {
                line++;
                continue;
            }
            
            if (in == 0) {
                if (max < out) {
                    max = out;
                    v = i;
                }
                continue;
            }
            
            if (in >= 2 && out >= 2) {
                eight++;
                continue;
            }
        }
        
        donut = max - line - eight;
        
        answer[0] = v;
        answer[1] = donut;
        answer[2] = line;
        answer[3] = eight;
        
        return answer;
    }
}

/*
조건
- 도넛, 막대, 8자
- 단방향 그래프
- 도넛 = n개의 정점 + n개의 간선

요구
- 정점 번호, 도넛, 막대, 8자 개수 구하기

풀이
- 정점번호 = indep == 0 && outdep > 0
- 막대 = indep == 1 && outdep == 1
- 8자 = indep >= 2 && outdep >= 2
- 도넛 = 정점에서 나가는 간선 - 막대 - 8자
*/