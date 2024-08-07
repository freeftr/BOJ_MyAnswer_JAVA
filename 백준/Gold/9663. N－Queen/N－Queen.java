import java.io.*;
import java.util.*;
public class Main {
    static int N;
    static int[][] chess;
    static int cnt = 0;
    static boolean[] rvisited;
    static boolean[] cvisited;
    static boolean[] xvisited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        chess = new int[N][N];
        rvisited = new boolean[N*N];
        cvisited = new boolean[N*N];
        xvisited = new boolean[N*N];

        func(0);
        System.out.println(cnt);
    }
    public static void func(int depth){
        if(depth == N){
            cnt++;
            return;
        }
        for(int i = 0; i < N; i++){
            if(!rvisited[i] && !cvisited[i+depth] && !xvisited[depth-1+N-i]){
                rvisited[i]=true;
                cvisited[i+depth]=true;
                xvisited[depth-1+N-i]=true;
                func(depth+1);
                rvisited[i]=false;
                cvisited[i+depth]=false;
                xvisited[depth-1+N-i]=false;
            }
        }
    }
}
