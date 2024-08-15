import java.util.*;
import java.io.*;
public class Main {
    static char[][] field = new char[12][6];
    static boolean[][] visited = new boolean[12][6];
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean check = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st ;
        //뿌요 위에서 아래로 이동
        //네개이상 연결시 같은 색 연결 뿌요 삭제 => 1연쇄
        //위에있는거 그대로 아래로
        //터질 수 있는 뿌요가 여러 그룹이다 => 동시에 터져야 함
        //R 빨강, G 초록, B 파랑, P 보라, Y 노랑
        for(int i = 0; i < 12; i++){
            String s = br.readLine();
            for(int j = 0; j < 6; j++){
                field[i][j] = s.charAt(j);
            }
        }
        int ans = 0;
        while(true){
            check = false;
            visited = new boolean[12][6];
            for(int i = 11; i >= 0 ; i--){
                for(int j = 0; j < 6; j++){
                    if(field[i][j]!='.' && !visited[i][j]){
                        bfs(i,j);
                    }
                }
            }
            newfield();
            if(!check)break;
            ans++;
        }
        System.out.println(ans);
    }
    public static void bfs(int x, int y){
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        ArrayList<int[]> removeList = new ArrayList<>();
        q.add(new int[]{x,y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            cnt++;
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];
            removeList.add(new int[]{X,Y});
            for(int i = 0; i < 4; i++){
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(nx>=12 || ny>=6 || nx<0 || ny<0)continue;
                if(visited[nx][ny])continue;
                if(field[nx][ny] == field[X][Y]){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
            }
        }
        //4이상일때 제거
        if(cnt>=4){
            check = true;
            for(int[] i : removeList){
                field[i[0]][i[1]] = '.';
            }
        }
    }
    public static void newfield() {
        for (int j = 0; j < 6; j++) {
            for (int i = 11; i > 0; i--) {
                if (field[i][j] == '.' && field[i-1][j] != '.') {
                    field[i][j] = field[i-1][j];
                    field[i-1][j] = '.';
                    i = 12;
                }
            }
        }
    }

}