import java.io.*;
import java.util.*;

public class Main {

    static int R, C, T;
    static int[][] map;
    static int upX = 0, upY = 0;
    static int downX = 0, downY = 0;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static ArrayList<int[]> dusts = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        boolean first = true;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) {
                    dusts.add(new int[]{i,j});
                }
                if( map[i][j]==-1 ) {
                    if(first) {
                        first = false;
                        upX = i;
                        upY = j;
                    } else {
                        downX = i;
                        downY = j;
                    }
                }
            }
        }
        for (int i = 0; i < T; i++) {
            expansion();
            airClean();
        }
        System.out.println(check());
    }

    static void expansion() {
        int[][] tempMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d], ny = j + dy[d];
                        if (nx >= 0 && ny >= 0 && nx < R && ny < C && map[nx][ny] != -1) {
                            tempMap[nx][ny] += map[i][j]/5;
                            cnt++;
                        }
                    }
                    tempMap[i][j] -= map[i][j]/5 * cnt;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += tempMap[i][j];
            }
        }
    }


    //위쪽은 시계방향으로 확인
    //아래쪽은 반시계방향으로 확인
    static void airClean() {
        for (int i = upX - 1; i > 0; i--){
            map[i][0] = map[i - 1][0];
        }
        for (int i = 0; i < C - 1; i++){
            map[0][i] = map[0][i + 1];
        }
        for (int i = 0; i < upX; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[upX][i] = map[upX][i - 1];
        }
        map[upX][1] = 0;

        for (int i = downX + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        for (int i = 0; i < C - 1; i++){
            map[R - 1][i] = map[R - 1][i + 1];
        }
        for (int i = R - 1; i > downX; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        for (int i = C - 1; i > 1; i--) {
            map[downX][i] = map[downX][i - 1];
        }
        map[downX][1] = 0;
    }



    static int check(){
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j]>0){
                    cnt+=map[i][j];
                }
            }
        }

        return cnt;
    }
}
