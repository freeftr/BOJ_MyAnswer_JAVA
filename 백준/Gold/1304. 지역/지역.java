import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;
    static boolean flag ;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            graph.get(i).add(i+1);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
        }

        for (int i = N; i >=1; i--) {
            // i==지역의 수  target== 지역에 포함된 도시의 수
            // 현재 지역 다음 도시부터 돌려서 현재 지역으로 들어오면 제외
            if(N%i==0){
                int target = N/i;
                flag = false;
                int left = 1;
                int right = target;
                visited = new boolean[N+1];

                while(right+1<=N){
                    //현재지역안으로 들어오는 경우 => 종료

                    for (int j = left; j <= right; j++) {
                        for(int nv : graph.get(j)){
                            if(nv<left){
                                flag = true;
                                break;
                            }
                        }
                    }
                    if(flag){
                        break;
                    }
                    dfs(right + 1, left, right,0,target);
                    if(flag){
                        break;
                    }
                    //현재지역안으로 못들어오는 경우 => 다음 지역으로 넘어가
                    right += target;
                    left += target;
                }

                if(!flag){
                    System.out.println(i);
                    System.exit(0);
                }
            }
        }
    }

    //left 지역의 시작
    //right 지역의 끝
    //start 지역의 다음 도시
    static void dfs(int v, int left, int right, int depth, int target){
        if(v>right+target){
            return;
        }
        if(v>=left && v<=right){
            flag = true;
            return;
        }
        for (int nv : graph.get(v)) {
            if(visited[nv])continue;
            visited[nv] = true;
            dfs(nv, left, right, depth+1, target);
        }
    }
}