import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static int A,B;
    static long C;
    static long max =0;
    static int start, end;
    static boolean[] visited;
    static boolean check = false;
    static ArrayList<ArrayList<Bridge>> island = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        for(int i = 0; i <= N; i++){
            island.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Long.parseLong(st.nextToken());
            island.get(A).add(new Bridge(B,C));
            island.get(B).add(new Bridge(A,C));
            max = Math.max(max, C);
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        visited[start] = true;
        end = Integer.parseInt(st.nextToken());
        BinarySearch();
    }

    public static void BinarySearch(){

        long low = 0;
        long high = max;
        while(low<=high){
            visited = new boolean[N+1];
            check = false;
            long mid = (low+high)/2;
            dfs(start,mid);
            if(check){
                low = mid+1;
            }
            else{
                high = mid-1;
            }
        }
        System.out.println(high);
    }
    //경로별 가중치 저장
    public static void dfs(int v, long mid){
        if(v == end){
            check = true;
            return;
        }
        for(Bridge i : island.get(v)){
            if(visited[i.nv])continue;
            if(i.weight<mid)continue;
            visited[i.nv] = true;
            dfs(i.nv, mid);
        }
    }
    //다리 노드
    public static class Bridge{
        int nv;
        long weight;
        Bridge(int nv, long weight){
            this.nv = nv;
            this.weight = weight;
        }
    }
}