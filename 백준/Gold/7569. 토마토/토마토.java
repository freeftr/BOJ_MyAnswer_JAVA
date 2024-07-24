import java.util.*;
public class Main {

    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt(), N = sc.nextInt(), H = sc.nextInt();
        int[][][] box = new int[H][N][M];
        boolean[][][] visited = new boolean[H][N][M];
        for(int z = 0 ; z < H; z++) {
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < M; y++) {
                    box[z][x][y] = sc.nextInt();
                    if(box[z][x][y] == 1) {
                        q.add(new int[]{z, x, y});
                    }
                }
            }
        }
        for(int z = 0 ; z < H; z++) {
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < M; y++) {
                    if(!visited[z][x][y] && box[z][x][y] == 1) {
                        bfs(z, x, y, visited, box, H, N, M);
                    }
                }
            }
        }
        boolean zero = false;
        long max = 1;
        for(int z = 0 ; z < H; z++) {
            for(int x = 0; x < N; x++) {
                for(int y = 0; y < M; y++) {
                    if(box[z][x][y] == 0) {
                        zero = true;
                    }
                    else if(box[z][x][y] >= max) {
                        max = box[z][x][y];
                    }
                }
            }
        }
        if(zero) {
            System.out.println(-1);
        }
        else if(max == 1) {
            System.out.println(0);
        }
        else {
            System.out.println(max-1);
        }

    }
    public static void bfs(int z, int x, int y, boolean[][][] visited, int[][][] box, int H, int N, int M) {
        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1 ,-1};
        visited[z][x][y] = true;
        q.offer(new int[] {z,x,y});
        while(!q.isEmpty()) {
            int[] arr = q.poll();
            int Z = arr[0];
            int X = arr[1];
            int Y = arr[2];
            for(int i = 0; i < 6; i++) {
                int nz = Z + dz[i];
                int nx = X + dx[i];
                int ny = Y + dy[i];
                if(0 <= nz && nz < H && 0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if(!visited[nz][nx][ny] && box[nz][nx][ny] == 0) {
                        visited[nz][nx][ny] = true;
                        q.offer(new int[] {nz,nx,ny});
                        box[nz][nx][ny] = box[Z][X][Y] + 1;
                    }
                }
            }
        }

    }

}
