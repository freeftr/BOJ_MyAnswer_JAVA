import java.io.*;
import java.util.*;
public class Main {
    static int N, M, r, c, d;
    static int[][] room;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //방의 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        room = new int[N][M];
        st = new StringTokenizer(br.readLine());
        //시작좌표
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        //방향 0 = 북, 1 = 동, 2 = 남, 3 = 서
        d = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //애초에 끝에 구역이 전부 벽이라 배열 밖으로 나가는 것을 생각안해도된다.
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        while(true){
            boolean check = false;
            //현재 자리 청소 안되 있을시 청소
            if(room[r][c] == 0){
                room[r][c] = 2;
            }
            //주변에 청소 안된 칸있는지 확인
            if(room[r][c+1] == 0 || room[r][c-1] == 0 || room[r+1][c] == 0 || room[r-1][c] == 0){
                check = true;
            }
            //빈 칸 있는 경우
            if(check){
                d--;
                if(d==-1){
                    d=3;
                }
                r+=dx[d];
                c+=dy[d];
                if(room[r][c] != 0){
                    r-=dx[d];
                    c-=dy[d];
                }
            }
            //빈칸없는 경우
            else{
                r-=dx[d];
                c-=dy[d];
                //뒤쪽이 벽이면 멈추기
                if(room[r][c] == 1){
                    break;
                }
            }
        }
        //청소한 구역 개수 체크
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(room[i][j] == 2){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}