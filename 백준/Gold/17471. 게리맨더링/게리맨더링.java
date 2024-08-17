import java.io.*;
import java.util.*;
import java.lang.*;
import java.util.concurrent.DelayQueue;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] population;
    static boolean[] visited;
    static int min,sum=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }
        //선거구를 두개로 나누고 모두 연결되어 있어야
        //두개로 나누는데 인구차이를 최소로 구하기
        population = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            population[i] = Integer.parseInt(st.nextToken());
            sum+=population[i];
        }
        min=sum;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for(int j = 0; j < M; j++){
                int a = Integer.parseInt(st.nextToken());
                graph.get(i).add(a);
            }
        }
        for(int i = 1; i <= N/2; i++){
            Deque<Integer> dq = new ArrayDeque<>();
            combinations(0,i,dq,1);
        }
        if(min==sum){
            System.out.println(-1);
            System.exit(0);
        }
        bw.write(min+"");
        bw.close();
    }
    //도시선택
    public static void combinations(int depth, int target, Deque<Integer> dq, int start){
        if(depth==target){
            //연결확인
            Deque<Integer> dq2 = new ArrayDeque<>();
            visited = new boolean[N+1];
            for(int i = 1; i <= N; i++){
                if(!dq.contains(i)){
                    dq2.add(i);
                }
            }
            int a = 0, b=0;
            if(bfs(dq) && bfs(dq2)){
                for(int i: dq){
                    a+=population[i];
                }
                for(int j: dq2){
                    b+=population[j];
                }
                if (Math.abs(a - b) < min) {
                    min = Math.abs(a - b);
                }
            }
            return;
        }
        for(int i = start; i <= N; i++){
            dq.addLast(i);
            combinations(depth+1,target,dq,start+1);
            dq.removeLast();
        }
    }
    public static boolean bfs(Deque<Integer> dq){
        Queue<Integer> q = new LinkedList<>();
        visited[dq.peekFirst()] = true;
        q.add(dq.peekFirst());
        int cnt = 1;
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int nv : graph.get(cur)){
                if(visited[nv])continue;
                if(!dq.contains(nv))continue;
                cnt++;
                visited[nv] = true;
                q.add(nv);
            }
        }
        if(cnt==dq.size()){
            return true;
        }
        else{
            return false;
        }
    }
}