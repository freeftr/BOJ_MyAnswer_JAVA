import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[] dice = {0,0,0,0,0,0,0};
        int[] arr = new int[K];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            arr[i]  = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < K; i ++){
            int temp;
            boolean flag = false;
            if(arr[i] == 1 && y + 1 < M){
                y++;
                flag = true;
                temp = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[3];
                dice[3] = temp;
            }
            if(arr[i] == 2 && y - 1 >= 0){
                y--;
                flag = true;
                temp = dice[1];
                dice[1] = dice[3];
                dice[3] = dice[6];
                dice[6] = dice[4];
                dice[4] = temp;
            }
            if(arr[i] == 3 && x - 1 >= 0){
                x--;
                flag = true;
                temp = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[2];
                dice[2] = temp;
            }
            if(arr[i] == 4 && x + 1 < N){
                x++;
                flag = true;
                temp = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[5];
                dice[5] = temp;
            }
            int top = dice[1];
            int bot = dice[6];
            if(flag){
                if(map[x][y] == 0){
                    map[x][y] = bot;
                }
                else{
                    dice[6] = map[x][y];
                    map[x][y] = 0;
                }
                ans.append(top+"\n");
            }
            else{
                continue;
            }
        }
        bw.write(ans.toString());
        bw.close();
    }
}
