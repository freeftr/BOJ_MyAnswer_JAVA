import java.io.*;
import java.util.*;

public class Main {

    static int N, M, island_idx = 0;
    static int[][] map;
    static boolean[][] union;
    static int[] parents;
    static int[] dx = {0,0,1,-1}, dy= {1,-1,0,0};
    static ArrayList<Edges> list = new ArrayList<>();
    public static class Edges implements Comparable<Edges>{
        int from;
        int to;
        int cost;
        Edges(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edges o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        union = new boolean[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //  섬을 구성하고 인덱스 주기
        //  다리 놓고 섬간 최소 거리 구하기(다리길이는 2이상)
        //  크루스칼 알고리즘으로 최소 거리 구하기

        // 섬 인덱싱
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j]==1 && !union[i][j]){
                    island_idx++;
                    island_idxing(i,j);
                }
            }
        }
        //다리 만들기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j]!=0){
                    Make_bridge(i,j);
                }
            }
        }

        //쿠르스칼 알고리즘
        Collections.sort(list);
        parents = new int[island_idx+1];
        for(int i = 1; i <= island_idx; i++){
            parents[i] = i;
        }
        int sum = 0;
        int cnt = 0;
        for(Edges edge: list){
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;
            if(find(to)!=find(from)) {
                cnt++;
                union(to, from);
                sum+=cost;
            }
        }
        if(cnt!=island_idx-1){
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(sum);

    }
    // 섬 인덱스 주기
    public static void island_idxing(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});
        map[x][y] = island_idx;
        union[x][y] = true;
        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];
            for (int i = 0; i < 4; i++) {
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M)continue;
                if(union[nx][ny])continue;
                if(map[nx][ny]!=0){
                    map[nx][ny] = island_idx;
                    union[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
            }
        }
    }
    //다리 만들고 최소거리 구해서 Edges에 추가
    public static void Make_bridge(int x, int y){
        for(int i =0 ; i < 4; i++){
            int nx = x;
            int ny = y;
            int dist = 0;
            while(true){
                dist++;
                nx += dx[i];
                ny += dy[i];
                if(nx<0 || ny<0 || nx>=N || ny>=M)break;
                if(map[nx][ny]==map[x][y])break;
                if(map[nx][ny]!=0 && dist<=2)break;
                if(map[nx][ny]!=0 && map[nx][ny]!=map[x][y] && dist>2){
                    list.add(new Edges(map[x][y],map[nx][ny],dist-1));
                    break;
                }
            }
        }
    }
    public static int find(int v){
        if(parents[v]==v)return v;
        return parents[v] = find(parents[v]);
    }
    public static void union(int a, int b){
        a = find(a);
        b = find(b);
        if(a<b)parents[b] = a;
        else parents[a] = b;
    }
}