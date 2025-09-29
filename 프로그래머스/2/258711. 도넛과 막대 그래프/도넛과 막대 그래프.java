import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = {};
        int[] in = new int[1000001];
        int[] out = new int[1000001];
        HashSet<Integer> nodes = new HashSet<>();
        
        for (int[] edge : edges) {
            in[edge[1]]++;
            out[edge[0]]++;
            
            nodes.add(edge[0]);
            nodes.add(edge[1]);
        }
        
        answer = new int[4];
        for (int node : nodes) {
            if (in[node] == 0 && out[node] >= 2) {
                answer[0] = node;
            }
            if (in[node] >= 1 && out[node] == 0) {
                answer[2]++;
            }
            if (in[node] >= 2 && out[node] >= 2) {
                answer[3]++;
            }
        }
        
        answer[1] = out[answer[0]] - answer[2] - answer[3];
        return answer;
    }
}

/*
조건
- 도넛: 다시 원래 정점 돌아옴.
- 막대: 다시 안돌아옴.
- 팔자: 도넛 두개 연결.

요구
- 정점
- 도넛 수
- 막대 수
- 팔자 수

풀이
- 각 정점의 나가는 간선 수와 들어오는 간선 수 비교
- 정점: 나가는 간선만 존재.
- 도넛: 정점의 out - 막대 - 팔자
- 막대: out = 1 in = 1
- 팔자: out = 2 in = 2
*/