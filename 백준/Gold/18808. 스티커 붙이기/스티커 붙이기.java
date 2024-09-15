import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, R, C;
    static int[][] map;
    static int[][] sticker;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            sticker = new int[R][C];

            for(int r = 0; r < R; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < C; c++){
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 스티커를 위치마다 맞는지 비교
            boolean check = false;
            for(int turn = 0; turn < 4; turn++){

                for(int x = 0; x <= N - R; x++) {
                    if (check) break;
                    for (int y = 0; y <= M - C; y++) {
                        if (place(x, y)) {
                            check = true;
                            break;
                        }
                    }
                }

                if(check){
                    break;
                }
                else{
                    sticker = rotate();
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 1) ans++;
            }
        }
        System.out.println(ans);
    }

    // 위치마다 맞는지 비교
    static boolean place(int x, int y){
        for(int i = x; i < x + R; i++){
            for(int j = y; j < y + C; j++){
                if(map[i][j] == 1 && sticker[i - x][j - y] == 1) return false;
            }
        }

        for(int i = x; i < x + R; i++) {
            for (int j = y; j < y + C; j++) {
                if (sticker[i - x][j - y] == 1) map[i][j] = 1;
            }
        }
        return true;
    }

    static int[][] rotate() {
        int[][] temp = new int[C][R];
        for(int x = 0; x < R; x++){
            for(int y = 0; y < C; y++){
                temp[y][R - 1 - x] = sticker[x][y];
            }
        }
        int tmp = R;
        R = C;
        C = tmp;
        return temp;
    }
}