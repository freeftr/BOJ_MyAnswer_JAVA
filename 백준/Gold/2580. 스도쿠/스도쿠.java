import java.io.*;
import java.util.*;
public class Main {
    static int[][] sudoku = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < 9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                sudoku[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,0);
    }
    //0찾기
    public static void dfs(int x, int y){
        if(y==9){
            dfs(x+1,0);
            return;
        }
        if(x==9){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    sb.append(sudoku[i][j]+" ");
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
            System.exit(0);
        }
        if(sudoku[x][y] == 0){
            for(int k=1; k <= 9; k++){
                if(!row(x,k)) continue;
                if(!col(y,k)) continue;
                if(!sq(x,y,k)) continue;
                sudoku[x][y] = k;
                dfs(x,y+1);
            }
            sudoku[x][y] = 0;
            return;
        }
        dfs(x, y+1);
    }
    //가로 확인
    public static boolean row(int i, int k){
        for(int j = 0; j < 9; j++){
            if(sudoku[i][j]==k){
                return false;
            }
        }
        return true;
    }
    //세로 확인
    public static boolean col(int j, int k){
        for(int i = 0; i < 9; i++){
            if(sudoku[i][j]==k){
                return false;
            }
        }
        return true;
    }
    //3*3 확인
    public static boolean sq(int i, int j, int k){
        for(int x = i/3*3; x < i/3*3+3; x++){
            for(int y = j/3*3; y < j/3*3+3; y++){
                if(sudoku[x][y]==k){
                    return false;
                }
            }
        }
        return true;
    }
}
