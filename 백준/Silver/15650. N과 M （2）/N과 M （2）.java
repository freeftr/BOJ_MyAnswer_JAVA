import java.util.*;
import java.io.*;
public class Main {
    static int N, M;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder result = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        arr = new int[N+1];
        func(0);
        bw.write(sb.toString());
        bw.close();
    }
    public static void func(int depth){
        if(depth==M){
            for(int i = 0; i < M; i++){
                sb.append(arr[i]+" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 1; i <= N; i++){
            if(!visited[i] && depth>0 && arr[depth-1]<=i){
                visited[i] = true;
                arr[depth] = i;
                func(depth+1);
                visited[i] = false;
            }
            if(depth == 0){
                visited[i] = true;
                arr[depth] = i;
                func(depth+1);
                visited[i] = false;
            }
        }
    }
}
