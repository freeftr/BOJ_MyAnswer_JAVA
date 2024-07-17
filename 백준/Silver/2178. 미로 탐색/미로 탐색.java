import java.util.*;
public class Main {
    static int N, M, cnt = 0;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        boolean[][] visited = new boolean[N][M];
        int[][] maze = new int[N][M];
        for(int i = 0; i < N; i++){
            String[] strArr = sc.next().split("");
            for(int j = 0; j < M; j++){
                maze[i][j] = Integer.parseInt(strArr[j]);
            }
        }
        bfs(0,0, maze, visited);
        System.out.println(maze[N-1][M-1]);

    }

    public static void bfs(int x, int y, int[][] maze, boolean[][] visited){
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        visited[x][y] = true;
        while(!q.isEmpty()){
            int[] arr = q.poll();
            int X = arr[0];
            int Y = arr[1];

            for(int i = 0; i < 4; i++){
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(0 <= nx && nx < N && 0 <= ny && ny < M){
                    if(!visited[nx][ny] && maze[nx][ny] == 1){
                        visited[nx][ny] = true;
                        q.offer(new int[] {nx, ny});
                        maze[nx][ny] = maze[X][Y] + 1;
                    }
                }
            }
        }
    }
}
