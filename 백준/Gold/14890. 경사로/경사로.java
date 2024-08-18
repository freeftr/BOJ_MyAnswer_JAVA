import java.util.*;
import java.io.*;

public class Main {
    static int N, L;
    static int[][] map;
    static int row, col;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];
        row = N;
        col = N;
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <= N; i++){
            check(i);
        }
        System.out.println(row+col);
    }
    public static void check(int idx){
        int rcnt = 1;
        int ccnt = 1;
        for(int i = 1; i <N; i++){
            if(Math.abs(map[i][idx]-map[i+1][idx])>1){
                col--;
                break;
            }
            else if(map[i][idx]==map[i+1][idx]){
                ccnt++;
            }
            else if(map[i][idx]-map[i+1][idx]==1){
                if (i + L > N) {
                    col--;
                    break;
                }
                boolean valid = true;
                for (int j = i + 1; j <= i + L; j++) {
                    if (map[j][idx] != map[i + 1][idx]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    ccnt = 0;
                    i += L - 1;
                } else {
                    col--;
                    break;
                }
            }
            else if(map[i][idx]-map[i+1][idx]==-1){
                if(ccnt<L){
                    col--;
                    break;
                }
                else{
                    ccnt=1;
                }
            }
        }
        for(int i = 1; i < N; i++){
            if(Math.abs(map[idx][i]-map[idx][i+1])>1){
                row--;
                break;
            }
            else if(map[idx][i]==map[idx][i+1]){
                rcnt++;
            }
            else if(map[idx][i]-map[idx][i+1]==1) {
                if (i + L > N) {
                    row--;
                    break;
                }
                boolean valid = true;
                for (int j = i + 1; j <= i + L; j++) {
                    if (map[idx][j] != map[idx][i + 1]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    rcnt = 0;
                    i += L - 1;
                } else {
                    row--;
                    break;
                }
            }
            else if(map[idx][i]-map[idx][i+1]==-1){
                if(rcnt<L){
                    row--;
                    break;
                }
                else{
                    rcnt=1;
                }
            }
        }
    }
}