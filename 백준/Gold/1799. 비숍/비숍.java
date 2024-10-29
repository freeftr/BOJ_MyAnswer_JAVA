import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static ArrayList<int[]> black = new ArrayList<>();
    static ArrayList<int[]> white = new ArrayList<>();
    static int maxBlack = 0;
    static int maxWhite = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==1){
                    if(i%2==0 && j%2==0){
                        black.add(new int[]{i,j});
                    }
                    else if(i%2==0 && j%2==1){
                        white.add(new int[]{i,j});
                    }
                    else if(i%2==1 && j%2==1){
                        black.add(new int[]{i,j});
                    }
                    else{
                        white.add(new int[]{i,j});
                    }
                }
            }
        }

        boolean[] check1 = new boolean[30];
        boolean[] check2 = new boolean[30];
        int wSize = white.size();
        int bSize = black.size();
        dfs1(0,check1,check2,white,wSize,0);
        dfs2(0,check1,check2,black,bSize,0);
        System.out.println(maxBlack+maxWhite);
    }

    static void dfs1(int depth, boolean[] check1, boolean[] check2, ArrayList<int[]> list, int size, int cnt) {
        if(depth==size){
            maxWhite = Math.max(maxWhite,cnt);
            return;
        }
        int[] now = list.get(depth);
        dfs1(depth+1,check1, check2, list,size, cnt);
        if(!check1[now[0]+now[1]] && !check2[now[0]-now[1]+N-1]){
            check1[now[0] + now[1]] = true;
            check2[now[0]-now[1]+N-1] = true;
            dfs1(depth+1,check1,check2,list,size, cnt+1);
            check1[now[0] + now[1]] = false;
            check2[now[0]-now[1]+N-1] = false;
        }
    }
    static void dfs2(int depth, boolean[] check1, boolean[] check2, ArrayList<int[]> list, int size, int cnt) {
        if(depth==size){
            maxBlack = Math.max(maxBlack,cnt);
            return;
        }
        int[] now = list.get(depth);
        dfs2(depth+1,check1, check2, list,size, cnt);
        if(!check1[now[0]+now[1]] && !check2[now[0]-now[1]+N-1]){
            check1[now[0] + now[1]] = true;
            check2[now[0]-now[1]+N-1] = true;
            dfs2(depth+1,check1,check2,list,size, cnt+1);
            check1[now[0] + now[1]] = false;
            check2[now[0]-now[1]+N-1] = false;
        }
    }
}