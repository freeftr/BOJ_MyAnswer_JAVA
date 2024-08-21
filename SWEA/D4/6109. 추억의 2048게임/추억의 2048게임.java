import java.util.*;
import java.io.*;
public class Solution {
    static int N;
    static int[][] map;
    static String command;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {-1,1,0,0};
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){

            //입력
            String[] s = br.readLine().split(" ");
            N = Integer.parseInt(s[0]);
            command = s[1];
            map = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            pull();
            add_block();
            pull();
            sb.append("#" + t + "\n");
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
    }
    //땡기기
    public static void pull(){
        if(command.equals("left")){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j] != 0 && j!=0){
                        int x = i;
                        int y = j;
                        while(true){
                            if(map[x][y-1]!=0)break;
                            if(map[x][y-1]==0){
                                map[x][y-1] = map[x][y];
                                map[x][y] = 0;
                                if(y==1)break;
                                else y--;
                            }
                        }
                    }
                }
            }
        }
        if(command.equals("right")) {
            for(int i = 0; i < N; i++) {
                for(int j = N - 1; j >= 0; j--) {
                    if(map[i][j] != 0 && j != N - 1) {
                        int x = i;
                        int y = j;
                        while(true) {
                            if(map[x][y + 1] != 0) break;
                            if(map[x][y + 1] == 0) {
                                map[x][y + 1] = map[x][y];
                                map[x][y] = 0;
                                if(y == N - 2) break;
                                else y++;
                            }
                        }
                    }
                }
            }
        }

        if(command.equals("up")) {
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[j][i] != 0 && j != 0) {
                        int x = j;
                        int y = i;
                        while(true) {
                            if(map[x - 1][y] != 0) break;
                            if(map[x - 1][y] == 0) {
                                map[x - 1][y] = map[x][y];
                                map[x][y] = 0;
                                if(x == 1) break;
                                else x--;
                            }
                        }
                    }
                }
            }
        }

        if(command.equals("down")) {
            for(int i = 0; i < N; i++) {
                for(int j = N - 1; j >= 0; j--) {
                    if(map[j][i] != 0 && j != N - 1) {
                        int x = j;
                        int y = i;
                        while(true) {
                            if(map[x + 1][y] != 0) break;
                            if(map[x + 1][y] == 0) {
                                map[x + 1][y] = map[x][y];
                                map[x][y] = 0;
                                if(x == N - 2) break;
                                else x++;
                            }
                        }
                    }
                }
            }
        }

    }

    //합치기
    public static void add_block(){
        if(command.equals("left")) {
            for(int i = 0; i < N; i++){
                for(int j = 1; j < N; j++){
                    //  0인 경우 스킵
                    if(map[i][j]==0)continue;
                    //  같으면 합쳐버리기
                    if(map[i][j] == map[i][j-1]){
                        map[i][j-1] = map[i][j] + map[i][j-1];
                        map[i][j] = 0;
                    }
                }
            }
        }
        if(command.equals("right")) {
            for(int i = 0; i < N; i++){
                for(int j = N-2; j >= 0; j--){
                    //  0인 경우 스킵
                    if(map[i][j]==0)continue;
                    //  같으면 합쳐버리기
                    if(map[i][j] == map[i][j+1]){
                        map[i][j+1] = map[i][j] + map[i][j+1];
                        map[i][j] = 0;
                    }
                }
            }
        }
        if(command.equals("up")) {
            for(int i = 0; i < N; i++){
                for(int j = 1; j < N; j++){
                    //  0인 경우 스킵
                    if(map[j][i]==0)continue;
                    //  같으면 합쳐버리기
                    if(map[j][i] == map[j-1][i]){
                        map[j-1][i] = map[j][i] + map[j-1][i];
                        map[j][i] = 0;
                    }
                }
            }
        }
        if(command.equals("down")) {
            for(int i = 0; i < N; i++){
                for(int j = N-2; j >= 0; j--){
                    //  0인 경우 스킵
                    if(map[j][i]==0)continue;
                    //  같으면 합쳐버리기
                    if(map[j][i] == map[j+1][i]){
                        map[j+1][i] = map[j][i] + map[j+1][i];
                        map[j][i] = 0;
                    }
                }
            }
        }
    }
}