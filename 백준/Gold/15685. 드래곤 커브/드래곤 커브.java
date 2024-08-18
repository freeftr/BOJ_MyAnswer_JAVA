import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static ArrayList<Dragon> curve = new ArrayList<>();
    static int[][] board = new int[101][101];
    static int[] dx={1,0,-1,0};
    static int[] dy={0,-1,0,1};
    static public class Dragon{
        int x;
        int y;
        int d;
        int g;
        Dragon(int x, int y, int d, int g){
            this.x=x;
            this.y=y;
            this.d=d;
            this.g=g;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        //  x,y는 시작점
        //  d는 시작방향
        //  0=(0,1) 1=(-1,0) 2=(0,-1) 3=(1,0)
        //  g는 세대
        //  세대 증가는 90도 회전 후 복붙
        //  드래곤 커브는 겹칠 수가 있음
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            curve.add(new Dragon(x,y,d,g));
        }
        //  커브 사이즈만큼 반복
        int size = curve.size();
        for(int i = 0; i < size; i++){
            Make_Dragon_Curve(curve.get(i));
//            for(int j = 0; j < 30; j++){
//                System.out.println();
//                for(int k = 0; k < 30; k++){
//                    System.out.printf("%d",board[j][k]);
//                }
//            }
//            System.out.println();
        }
        int ans = 0;
        for(int i = 1; i <=100;i++){
            for(int j = 1; j <= 100; j++){
                if(board[i][j]==1 && board[i][j-1]==1 && board[i-1][j]==1 && board[i-1][j-1]==1){
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
    public static void Make_Dragon_Curve(Dragon dragon){
        //현재
        Deque<Integer> dq = new ArrayDeque<>();
        //다음에 넣을 커브
        Deque<Integer> ndq = new ArrayDeque<>();
        int x = dragon.x;
        int y = dragon.y;
        int d = dragon.d;
        int g = dragon.g;
        board[y][x] = 1;
        dq.push(d);
        //현재세대
        int ng=0;
        while(ng<g){
            int size = dq.size();
            while(size!=0) {
                size--;
                int nd = dq.pollLast();
                dq.addFirst(nd);
                nd=(nd+1)%4;
                ndq.addLast(nd);
            }
            while(!ndq.isEmpty()){
                dq.addLast(ndq.pollFirst());
            }
            ng++;
        }
        while(!dq.isEmpty()){
            int nd = dq.pollFirst();
            x += dx[nd];
            y += dy[nd];
            board[y][x] = 1;
        }
    }
}