import java.util.*;
import java.io.*;

public class Main {

    static int ans = 0;
    static int N, K, L;
    static int curD = 0 ;
    static int[][] board;
    static Deque<int[]> snake = new ArrayDeque<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N+1][N+1];
        K = Integer.parseInt(br.readLine());
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x-1][y-1] = 1;
        }
        L = Integer.parseInt(br.readLine());
        //머리, 꼬리 추가
        snake.addFirst(new int[]{0,0});

        for(int i = 0 ; i < L; i++){
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            move(sec-ans, dir);
        }
        move(100, "L");
    }
    //curD
    //0 = 우 1 = 하 2 = 좌 3 = 상
    //덱 앞쪽이 뱀머리 뒤가 꼬리
    public static void move(int sec, String dir){
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        for(int i = 0; i < sec; i++){
            ans++;
            int head_x = snake.peekFirst()[0]+dx[curD];
            int head_y = snake.peekFirst()[1]+dy[curD];
            //벽에 부딪히는 경우
            if(head_x>=N || head_y>=N || head_x<0 || head_y<0){
                //System.out.println("out");
                System.out.println(ans);
                System.exit(0);
            }
            //몸통에 부딪히는 경우
            for(int[] j : snake){
                if(j[0] == head_x && j[1] == head_y){
                    //System.out.println("body");
                    System.out.println(ans);
                    System.exit(0);
                }
            }
            //머리 위치 넣어주기
            snake.addFirst(new int[]{head_x, head_y});
//            System.out.printf("\nhead_x: %d   head_y: %d", head_x,head_y);
            //사과를 먹은 경우
            if(board[head_x][head_y] == 1){
                //사과 제거
                board[head_x][head_y] = 0;
            }
            //못 먹은 경우
            else{
                //꼬리 제거
                snake.removeLast();
            }
        }
        //방향 명령에 따른 curD 갱신
        if(dir.equals("L")){
            curD--;
            if(curD==-1){
                curD=3;
            }
        }
        if(dir.equals("D")){
            curD++;
            if(curD == 4){
                curD=0;
            }
        }
    }
}
