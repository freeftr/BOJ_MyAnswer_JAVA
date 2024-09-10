import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDeg = new int[N+1];

        for(int i = 0; i <= N; i++){
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            inDeg[b]++;
        }

        Queue<int[]> q = new ArrayDeque<>();
        int[] ans = new int[N+1];

        for(int i = 1; i <= N; i++){
            if(inDeg[i]==0)
                q.add(new int[]{i,1});
        }

        while(!q.isEmpty()){
            int[] now = q.poll();

            ans[now[0]] = now[1];

            for(int next : graph.get(now[0])){
                inDeg[next]--;
                if(inDeg[next]==0){
                    q.add(new int[]{next,now[1]+1});
                }
            }
        }
        for(int i : ans){
            if(i==0)continue;
            System.out.printf("%d ", i);
        }
    }
}